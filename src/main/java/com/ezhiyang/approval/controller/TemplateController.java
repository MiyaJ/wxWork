package com.ezhiyang.approval.controller;


import com.ezhiyang.approval.model.Result;
import com.ezhiyang.approval.entity.Template;
import com.ezhiyang.approval.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
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
        return Result.success("查询模板成功", list);
    }

    @GetMapping("/getTemplateDetail")
    public Result getTemplateDetail(String templateId) {
        return Result.success("查询模板详情成功!", templateService.getTemplateDetail(templateId));
    }

}
