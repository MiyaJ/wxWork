package com.miya.wx.controller;


import com.alibaba.fastjson.JSONObject;
import com.miya.wx.model.Result;
import com.miya.wx.service.IApprovalService;
import com.miya.wx.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName TestController.java
 * @Description 测试api
 * @createTime 2020年06月18日 13:23:00
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IApprovalService approvalService;
    @Autowired
    private IMessageService messageService;

    @GetMapping("/getAccessToken")
    public Result getAccessToken() {
        return Result.success("success", approvalService.getAccessToken());
    }

    @PostMapping("/applyEvent")
    public Result applyEvent(@RequestBody Map<String, Object> params) {

        JSONObject jsonObject = approvalService.applyEvent(params);
        return Result.success("success", jsonObject);
    }

    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody JSONObject msg) {
        return Result.success("success", messageService.sendMsg(msg));
    }
}
