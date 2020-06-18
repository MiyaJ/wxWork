package com.ezhiyang.approval.controller;

import com.ezhiyang.approval.service.IQywxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Caixiaowei
 * @ClassName TestController.java
 * @Description 测试api
 * @createTime 2020年06月18日 13:23:00
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${qywx.approval-corpsecret}")
    private String SECRET;

    @Autowired
    private IQywxService qywxService;

    @GetMapping("/getAccessToken")
    public Object getAccessToken() {
        return qywxService.getToken(SECRET);
    }
}
