package com.ezhiyang.approval.service;

import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.model.dto.chat.GroupChatCreateDTO;
import com.ezhiyang.approval.model.dto.chat.GroupChatUpdateDTO;
import com.ezhiyang.approval.model.msg.ImageMsg;
import com.ezhiyang.approval.model.msg.MsgVO;
import com.ezhiyang.approval.model.msg.NewsMsg;
import com.ezhiyang.approval.model.msg.TextMsg;
import com.ezhiyang.approval.model.vo.ChatInfoVO;

/**
 * @author Caixiaowei
 * @ClassName IMsgService.java
 * @Description 企业微信消息推送接口
 * @createTime 2020年06月24日 16:44:00
 */
public interface IMessageService {

    String getAccessToken();

    /**
     * @title 发送消息
     * @description
     * @author Caixiaowei
     * @param
     * @updateTime 2020/6/19 11:00
     */
    MsgVO sendMsg(JSONObject msg);

    /**
     * @title fasong 文本消息
     * @description
     * @author Caixiaowei
     * @param msg TextMsg 文本消息对象
     * @updateTime 2020/6/19 15:26
     */
    MsgVO sendTextMsg(TextMsg msg);

    /**
     * @title 发送图片消息
     * @description
     * @author Caixiaowei
     * @param msg ImageMsg 图片消息对象
     * @updateTime 2020/6/19 15:49
     */
    MsgVO sendImage(ImageMsg msg);

    /**
     * @title 发送图文消息
     * @description
     * @author Caixiaowei
     * @param msg NewsMsg 图文消息对象
     * @updateTime 2020/6/19 16:10
     */
    MsgVO sendNews(NewsMsg msg);

    /**
     * 创建群聊
     * @description
     * @author Caixiaowei
     * @param groupChatCreateDTO
     * @updateTime 2020/6/28 14:30
     * @return chatid 群id
     */
    String createGroupChat(GroupChatCreateDTO groupChatCreateDTO);

    /**
     * 修改群聊
     * @description
     * @author Caixiaowei
     * @param groupChatUpdateDTO
     * @updateTime 2020/6/28 15:20
     * @return
     */
    void updateGroupChat(GroupChatUpdateDTO groupChatUpdateDTO);

    /**
     * 获取群聊
     * @description
     * @author Caixiaowei
     * @param chatid 群聊id
     * @updateTime 2020/6/28 15:24
     * @return ChatInfoVO
     */
    ChatInfoVO getGroupChat(String chatid);

    /**
     * 发送群聊消息
     * @description
     * @author Caixiaowei
     * @param msg 消息体
     * @updateTime 2020/6/28 15:35
     * @return
     */
    void sendGroupChat(JSONObject msg);
}
