package com.ezhiyang.approval.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.common.RedisConstans;
import com.ezhiyang.approval.model.msg.ImageMsg;
import com.ezhiyang.approval.model.msg.MsgVO;
import com.ezhiyang.approval.model.msg.NewsMsg;
import com.ezhiyang.approval.model.msg.TextMsg;
import com.ezhiyang.approval.service.IMessageService;
import com.ezhiyang.approval.util.OkHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
}
