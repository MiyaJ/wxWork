package com.ezhiyang.approval.controller;

import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.model.Result;
import com.ezhiyang.approval.model.dto.TemplateDTO;
import com.ezhiyang.approval.service.IApprovalService;
import com.ezhiyang.approval.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName ApprovalController.java
 * @Description 审批api
 * @createTime 2020年06月24日 14:52:00
 */
@RestController
public class ApprovalController {

    @Autowired
    private IApprovalService approvalService;
    @Autowired
    private ITemplateService templateService;

    /**
     * 查询审批申请详情
     *
     * @param spNo 审批单号
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 14:12
     */
    @GetMapping("/getApprovalDetail")
    public Result getApprovalDetail(String spNo) {
        JSONObject result = approvalService.getApprovalDetail(spNo);
        return Result.success("查询审批详情成功!", result);
    }

    /**
     * 新增审批模板
     *
     * @param templateDTO
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 14:12
     */
    @PostMapping("/addTemplate")
    public Result addTemplate(@RequestBody TemplateDTO templateDTO) {
        return templateService.addTemplate(templateDTO);
    }

    /**
     * 获取审批模板详情
     *
     * @param templateId 模板id
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 14:11
     */
    @GetMapping("/getTemplateDetail")
    public Result getTemplateDetail(String templateId) {
        JSONObject detail = templateService.getTemplateDetail(templateId);
        return Result.success("获取模板详情成功!", detail);
    }

    /**
     * 审批申请
     *
     * @param params
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 14:11
     */
    @PostMapping("/applyEvent")
    public Result applyEvent(@RequestBody Map<String, Object> params) {

        JSONObject jsonObject = approvalService.applyEvent(params);
        return Result.success("success", jsonObject);
    }


}
