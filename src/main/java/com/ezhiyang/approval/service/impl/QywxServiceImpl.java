package com.ezhiyang.approval.service.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.common.RedisConstans;
import com.ezhiyang.approval.common.enums.MsgTypeEnum;
import com.ezhiyang.approval.entity.ApplyLog;
import com.ezhiyang.approval.model.msg.*;
import com.ezhiyang.approval.service.IApplyLogService;
import com.ezhiyang.approval.service.IQywxService;
import com.ezhiyang.approval.util.OkHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName QywxServiceImpl.java
 * @Description 企业微信业务实现
 * @createTime 2020年06月16日 17:05:00
 */
@Slf4j
@Service
public class QywxServiceImpl implements IQywxService {

    @Value("${qywx.corpid}")
    private String CORPID;
    @Value("${qywx.approval-corpsecret}")
    private String APPROVAL_SECRET;
    @Value("${qywx.msg-corpsecret}")
    private String MSG_SECRET;

    private String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    @Autowired
    private RedisService redisService;
    @Autowired
    private IApplyLogService applyLogService;
    /**
     * @title 获取access_token
     * @description 每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     * @author Caixiaowei
     * @updateTime 2020/6/16 17:08
     */
    @Override
    public String getToken() {
        String accessToken = String.valueOf(redisService.get(RedisConstans.QYWX_ACCESS_TOKEN_KEY));
        if (StringUtils.isBlank(accessToken) || "null".equalsIgnoreCase(accessToken)) {
            Map<String, String> params = MapUtil.newHashMap();
            params.put("corpid", this.CORPID);
            params.put("corpsecret", this.MSG_SECRET);
            try {
                String result = OkHttpClientUtil.doGet(ACCESS_TOKEN_URL, null, params);
                JSONObject json = JSONObject.parseObject(result);
                if (json.getInteger("errcode") != null && json.getInteger("errcode") == 0) {
                    accessToken = json.getString("access_token");
                    redisService.set(RedisConstans.QYWX_ACCESS_TOKEN_KEY, accessToken, RedisConstans.QYWX_ACCESS_TOKEN_EXPIRATION);
                }
            } catch (Exception e) {
                log.error("获取审批应用access_token 异常--->{}", e);
            }

        }
        return accessToken;
    }

    /**
     * @param params
     * @title 审批申请
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/18 17:27
     */
    @Override
    public JSONObject applyEvent(Map<String, Object> params) {
        String userid = String.valueOf(params.get("creator_userid"));
        String templateId = String.valueOf(params.get("template_id"));


        String url = "https://qyapi.weixin.qq.com/cgi-bin/oa/applyevent?access_token=" + getToken();
        String result = OkHttpClientUtil.doPost(url, null, params);
        JSONObject wxResult = JSONObject.parseObject(result);
        String spNo = wxResult.getString("sp_no");

        // 保存申请记录
        ApplyLog applyLog = new ApplyLog();
        applyLog.setSpNo(spNo);
        applyLog.setTemplateId(templateId);
        applyLog.setUserid(userid);
        applyLog.setCreateTime(LocalDateTime.now());
        applyLogService.save(applyLog);

        return wxResult;
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
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + getToken();

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
