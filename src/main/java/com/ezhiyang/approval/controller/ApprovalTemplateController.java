package com.ezhiyang.approval.controller;


import com.ezhiyang.approval.entity.ApprovalTemplate;
import com.ezhiyang.approval.entity.Template;
import com.ezhiyang.approval.model.Result;
import com.ezhiyang.approval.service.IApprovalTemplateService;
import com.ezhiyang.approval.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 审批模板  前端控制器
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-07-24
 */
@RestController
@RequestMapping("/v1/approval-template")
public class ApprovalTemplateController {

    @Autowired
    private IApprovalTemplateService approvalTemplateService;

    @GetMapping("/getDetail")
    public Result getDetail(String templateId) {
        return Result.success("查询模板详情成功!", approvalTemplateService.getDetail(templateId));
    }
}
