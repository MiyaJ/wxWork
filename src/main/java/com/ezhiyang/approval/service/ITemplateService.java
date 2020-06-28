package com.ezhiyang.approval.service;

import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.entity.Template;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ezhiyang.approval.model.Result;
import com.ezhiyang.approval.model.dto.TemplateDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-16
 */
public interface ITemplateService extends IService<Template> {

    JSONObject getTemplateDetail(String templateId);

    Result addTemplate(TemplateDTO templateDTO);
}
