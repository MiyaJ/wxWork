package com.ezhiyang.approval.controller;


import com.ezhiyang.approval.entity.Template;
import com.ezhiyang.approval.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-16
 */
@RestController
@RequestMapping("/approval/template")
public class TemplateController {

    @Autowired
    private ITemplateService templateService;

    @GetMapping
    public Object list() {
        List<Template> list = templateService.list();
        return list;
    }
}
