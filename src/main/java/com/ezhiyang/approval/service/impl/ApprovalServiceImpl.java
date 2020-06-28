package com.ezhiyang.approval.service.impl;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.common.RedisConstans;
import com.ezhiyang.approval.entity.ApplyLog;
import com.ezhiyang.approval.model.msg.ImageMsg;
import com.ezhiyang.approval.model.msg.MsgVO;
import com.ezhiyang.approval.model.msg.NewsMsg;
import com.ezhiyang.approval.model.msg.TextMsg;
import com.ezhiyang.approval.service.IApplyLogService;
import com.ezhiyang.approval.service.IApprovalService;
import com.ezhiyang.approval.util.OkHttpClientUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName ApprovalServiceImpl.java
 * @Description 企业微信审批业务实现
 * @createTime 2020年06月16日 17:05:00
 */
@Slf4j
@Service
public class ApprovalServiceImpl extends WxWorkServiceImpl implements IApprovalService {

    @Value("${qywx.approval-corpsecret}")
    private String APPROVAL_SECRET;

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
    public String getAccessToken() {
        String accessToken = String.valueOf(redisService.get(RedisConstans.QYWX_ACCESS_TOKEN_KEY_APPROVAL));
        if (StringUtils.isBlank(accessToken) || "null".equalsIgnoreCase(accessToken)) {
            try {
                accessToken = super.getAccessToken(this.APPROVAL_SECRET);
                if (StringUtils.isNotBlank(accessToken)) {
                    redisService.set(RedisConstans.QYWX_ACCESS_TOKEN_KEY_APPROVAL, accessToken, RedisConstans.QYWX_ACCESS_TOKEN_EXPIRATION);
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


        String url = "https://qyapi.weixin.qq.com/cgi-bin/oa/applyevent?access_token=" + getAccessToken();
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
     * 获取审批详情
     *
     * @param spNo 审批单号
     * @return JSONObject
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/24 15:11
     */
    @Override
    public JSONObject getApprovalDetail(String spNo) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovaldetail?access_token=" + getAccessToken();
        Map<String, Object> params = Maps.newHashMap();
        params.put("sp_no", spNo);
        String result = OkHttpClientUtil.doPost(url, null, params);
        JSONObject wxResult = JSONObject.parseObject(result);

        return wxResult;
    }

    /**
     * 批量获取审批单号
     *
     * @param params
     * @return List<String> 审批单号集合
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/24 16:02
     */
    @Override
    public List<String> getApprovalInfo(Map<String, Object> params) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovalinfo?access_token=" + getAccessToken();


        String result = OkHttpClientUtil.doPost(url, null, params);
        JSONObject wxResult = JSONObject.parseObject(result);
        log.info("getApprovalInfo --->{}", wxResult.toJSONString());
        Integer errcode = wxResult.getInteger("errcode");
        if (errcode == 0) {
            JSONArray sp_no_list = wxResult.getJSONArray("sp_no_list");
            List<String> list = sp_no_list.toJavaList(String.class);
            return list;
        } else {
            return Lists.newArrayList();
        }
    }
}
