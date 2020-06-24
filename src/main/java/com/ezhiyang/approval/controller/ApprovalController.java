package com.ezhiyang.approval.controller;

import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.model.Result;
import com.ezhiyang.approval.service.IApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getApprovalDetail")
    public Result getApprovalDetail(String spNo) {
        JSONObject result = approvalService.getApprovalDetail(spNo);
        return Result.successOfData(result);
    }
}
