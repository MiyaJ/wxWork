package com.miya.wx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.miya.wx.common.RedisConstans;
import com.miya.wx.entity.GroupChat;
import com.miya.wx.entity.GroupChatUser;
import com.miya.wx.model.dto.chat.GroupChatCreateDTO;
import com.miya.wx.model.dto.chat.GroupChatUpdateDTO;
import com.miya.wx.model.msg.ImageMsg;
import com.miya.wx.model.msg.MsgVO;
import com.miya.wx.model.msg.NewsMsg;
import com.miya.wx.model.msg.TextMsg;
import com.miya.wx.model.vo.ChatInfoVO;
import com.miya.wx.service.IGroupChatService;
import com.miya.wx.service.IGroupChatUserService;
import com.miya.wx.service.IMessageService;
import com.miya.wx.util.OkHttpClientUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName MsgServiceImpl.java
 * @Description 企业微信消息推送接口实现
 * @createTime 2020年06月24日 16:45:00
 */
@Service
@Slf4j
public class MessageServiceImpl extends WxWorkServiceImpl implements IMessageService {

    @Value("${qywx.msg-corpsecret}")
    private String MSG_SECRET;

    private String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    @Autowired
    private RedisService redisService;
    @Autowired
    private IGroupChatService groupChatService;
    @Autowired
    private IGroupChatUserService groupChatUserService;

    /**
     * @title 获取access_token
     * @description 每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     * @author Caixiaowei
     * @updateTime 2020/6/16 17:08
     */
    @Override
    public String getAccessToken() {
        String accessToken = String.valueOf(redisService.get(RedisConstans.QYWX_ACCESS_TOKEN_KEY_MESSAGE));
        if (StringUtils.isBlank(accessToken) || "null".equalsIgnoreCase(accessToken)) {
            try {
                accessToken = super.getAccessToken(this.MSG_SECRET);
                if (StringUtils.isNotBlank(accessToken)) {
                    redisService.set(RedisConstans.QYWX_ACCESS_TOKEN_KEY_MESSAGE, accessToken, RedisConstans.QYWX_ACCESS_TOKEN_EXPIRATION);
                }
            } catch (Exception e) {
                log.error("获取审批应用access_token 异常--->{}", e);
            }

        }
        return accessToken;
    }

    /**
     * @param msg
     * @title 发送消息
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/19 11:00
     */
    @Override
    public MsgVO sendMsg(JSONObject msg) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + getAccessToken();

        log.info("sendMsg --->{}", msg.toJSONString());
        String resultStr = OkHttpClientUtil.doPost(url, null, msg);
        log.info("resultStr ---》 data : {}", resultStr);
        MsgVO msgVO = JSONObject.parseObject(resultStr, MsgVO.class);

        return msgVO;
    }

    /**
     * @param msg TextMsg 文本消息对象
     * @title fasong 文本消息
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/19 15:26
     */
    @Override
    public MsgVO sendTextMsg(TextMsg msg) {
        JSONObject data = (JSONObject) JSONObject.toJSON(msg);
        MsgVO msgVO = this.sendMsg(data);
        return msgVO;
    }

    /**
     * @param msg ImageMsg 图片消息对象
     * @title 发送图片消息
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/19 15:49
     */
    @Override
    public MsgVO sendImage(ImageMsg msg) {
        JSONObject data = (JSONObject) JSONObject.toJSON(msg);
        MsgVO msgVO = this.sendMsg(data);
        return msgVO;
    }

    /**
     * @param msg NewsMsg 图文消息对象
     * @title 发送图文消息
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/19 16:10
     */
    @Override
    public MsgVO sendNews(NewsMsg msg) {
        JSONObject data = (JSONObject) JSONObject.toJSON(msg);
        MsgVO msgVO = this.sendMsg(data);
        return msgVO;
    }

    /**
     * 创建群聊
     *
     * @param groupChatCreateDTO
     * @return chatid 群id
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 14:30
     */
    @Override
    public String createGroupChat(GroupChatCreateDTO groupChatCreateDTO) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/appchat/create?access_token=" + getAccessToken();
        List<String> userlist = groupChatCreateDTO.getUserlist();
        if (CollectionUtils.isEmpty(userlist) || userlist.size() < 2) {
            log.error("群聊成员补鞥呢少于2个");
            return StringUtils.EMPTY;
        }
        JSONObject data = (JSONObject) JSONObject.toJSON(groupChatCreateDTO);
        String resultStr = OkHttpClientUtil.doPost(url, null, data);
        JSONObject result = JSONObject.parseObject(resultStr);
        if (result.getIntValue("errcode") == 0) {
            String chatid = result.getString("chatid");

            // db 群聊入库
            GroupChat groupChat = new GroupChat();
            groupChat.setChatid(chatid);
            groupChat.setName(groupChatCreateDTO.getName());
            groupChat.setOwner(groupChatCreateDTO.getOwner());
            groupChat.setCreateTime(LocalDateTime.now());
            groupChat.setUpdateTime(LocalDateTime.now());
            this.groupChatService.save(groupChat);

            // 聊成员入库
            List<GroupChatUser> groupChatUserList = Lists.newArrayList();
            for (String userid : userlist) {
                GroupChatUser groupChatUser = new GroupChatUser();
                groupChatUser.setChatid(chatid);
                groupChatUser.setUserid(userid);

                groupChatUserList.add(groupChatUser);
            }
            this.groupChatUserService.saveBatch(groupChatUserList);

            return chatid;
        } else {
            log.error("创建群聊异常: {}", result.getString("errmsg"));
        }
        return null;
    }

    /**
     * 修改群聊
     *
     * @param groupChatUpdateDTO
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 15:20
     */
    @Override
    public void updateGroupChat(GroupChatUpdateDTO groupChatUpdateDTO) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/appchat/update?access_token=" + getAccessToken();
        JSONObject data = (JSONObject) JSONObject.toJSON(groupChatUpdateDTO);
        String resultStr = OkHttpClientUtil.doPost(url, null, data);
        JSONObject result = JSONObject.parseObject(resultStr);
        if (result.getIntValue("errcode") == 0) {
            log.info("修改群聊 success");
        } else {
            log.error("修改群聊异常: {}", result.getString("errmsg"));
        }
    }

    /**
     * 获取群聊
     *
     * @param chatid 群聊id
     * @return ChatInfoVO
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 15:24
     */
    @Override
    public ChatInfoVO getGroupChat(String chatid) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/appchat/get?access_token=" + getAccessToken();
        Map<String, String> params = Maps.newHashMap();
        params.put("chatid", chatid);
        String resultStr = null;
        try {
            resultStr = OkHttpClientUtil.doGet(url, null, params);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取群聊异常: {}", resultStr);
        }
        JSONObject result = JSONObject.parseObject(resultStr);
        if (result.getIntValue("errcode") == 0) {
            String chatInfo = result.getString("chat_info");
            return JSONObject.parseObject(chatInfo, ChatInfoVO.class);
        } else {
            log.error("获取群聊异常: {}", result.getString("errmsg"));
        }
        return new ChatInfoVO();
    }

    /**
     * 发送群聊消息
     *
     * @param msg 消息体
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 15:35
     */
    @Override
    public void sendGroupChat(JSONObject msg) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/appchat/send?access_token=" + getAccessToken();
        String resultStr = OkHttpClientUtil.doPost(url, null, msg);
        log.info("sendGroupChat : {}", resultStr);

    }
}
