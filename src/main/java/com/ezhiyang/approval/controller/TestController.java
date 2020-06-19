package com.ezhiyang.approval.controller;


import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.model.Result;
import com.ezhiyang.approval.model.msg.BaseMsg;
import com.ezhiyang.approval.model.msg.TextMsg;
import com.ezhiyang.approval.service.IQywxService;
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
    private IQywxService qywxService;

    @GetMapping("/getAccessToken")
    public Result getAccessToken() {
        return Result.successOfData(qywxService.getToken());
    }

    @PostMapping("/applyEvent")
    public Result applyEvent(@RequestBody Map<String, Object> params) {

        JSONObject jsonObject = qywxService.applyEvent(params);
        return Result.sucessOfData("sucess", jsonObject);
    }

    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody JSONObject msg) {
        return Result.successOfData(qywxService.sendMsg(msg));
    }
}
