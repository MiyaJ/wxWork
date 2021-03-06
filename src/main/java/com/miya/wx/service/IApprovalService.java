package com.miya.wx.service;


import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName IApprovalService.java
 * @Description 企业微信审批业务接口
 * @createTime 2020年06月16日 17:04:00
 */
public interface IApprovalService {

    /**
     * @return String
     * @title 获取access_token
     * @description 每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     * @author Caixiaowei
     * @updateTime 2020/6/16 17:08
     */
    String getAccessToken();

    /**
     * 审批申请
     *
     * @param params Map<String, Object> 申请内容, 详情见模板详情
     * @return JSONObject
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/29 13:56
     */
    JSONObject applyEvent(Map<String, Object> params);

    /**
     * 获取审批详情
     *
     * @param spNo 审批单号
     * @return JSONObject
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/24 15:11
     */
    JSONObject getApprovalDetail(String spNo);

    /**
     * 批量获取审批单号
     *
     * @param
     * @return List<String> 审批单号集合
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/24 16:02
     */
    List<String> getApprovalInfo(Map<String, Object> params);

}
