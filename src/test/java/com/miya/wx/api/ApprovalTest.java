package com.miya.wx.api;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.miya.wx.service.IApprovalService;
import com.miya.wx.service.ITemplateService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName ApprovalTest.java
 * @Description 审批测试用例
 * @createTime 2020年06月24日 15:23:00
 */
@SpringBootTest
@Slf4j
public class ApprovalTest {

    @Autowired
    private IApprovalService approvalService;
    @Autowired
    private ITemplateService templateService;


    /**
     * 获取审批详情
     *
     * @param
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/24 15:25
     */
    @Test
    public void test_getAapprovalDetail() {
        String spNo = "202007060001";
        JSONObject approvalDetail = approvalService.getApprovalDetail(spNo);
        log.info("approvalDetail --->{}", approvalDetail.toJSONString());
    }

    /**
     * 批量获取审批单号
     *
     * @param
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/24 15:59
     */
    @Test
    public void test_getApprovalInfo() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("starttime", DateUtil.beginOfMonth(new Date()).getTime() / 1000);
        params.put("endtime", DateUtil.endOfMonth(new Date()).getTime() / 1000);
        params.put("cursor", 0);
        params.put("size", 100);
        List<String> spNoList = approvalService.getApprovalInfo(params);
        log.info("审批单号 --->{}", spNoList);
    }

    /**
     * 获取模板详情
     * @description
     * @author Caixiaowei
     * @param
     * @updateTime 2020/7/6 11:28
     * @return 
     */
    @Test
    public void test_getTemplate() {
        String templateId = "Bs7wRUBt1HhUXNvP3hVCmxBUqZrEyo2XnLakE3XFJ";
        JSONObject detail = templateService.getTemplateDetail(templateId);
        log.info("template detail --->{}", detail.toJSONString());
    }
}
