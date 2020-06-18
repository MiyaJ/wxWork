package com.ezhiyang.approval.controller;


import com.ezhiyang.approval.common.Result;
import com.ezhiyang.approval.entity.Template;
import com.ezhiyang.approval.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/approval")
public class TemplateController {

    @Autowired
    private ITemplateService templateService;

    @GetMapping("/templateList")
    public Result list() {
        List<Template> list = templateService.list();
        return Result.successOfData(list);
    }

    @GetMapping("/getTemplateDetail")
    public Result getTemplateDetail(String templateId) {
        return Result.successOfData(templateService.getTemplateDetail(templateId));
    }

    @PostMapping("/applyEvent")
    public Result applyEvent() {
        return Result.sucess();
    }
}
