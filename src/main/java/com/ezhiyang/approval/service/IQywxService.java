package com.ezhiyang.approval.service;


import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.model.msg.BaseMsg;
import com.ezhiyang.approval.model.msg.ImageMsg;
import com.ezhiyang.approval.model.msg.MsgVO;
import com.ezhiyang.approval.model.msg.TextMsg;

import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName IQywxService.java
 * @Description 企业微信业务接口
 * @createTime 2020年06月16日 17:04:00
 */
public interface IQywxService {

    /**
     * @title 获取access_token
     * @description 每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     * @author Caixiaowei
     * @updateTime 2020/6/16 17:08
     * @return String
     */
    String getToken();

    /**
     * @title 审批申请
     * @description
     * @author Caixiaowei
     * @param
     * @updateTime 2020/6/18 17:27
     */
    JSONObject applyEvent(Map<String, Object> params);

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
}
