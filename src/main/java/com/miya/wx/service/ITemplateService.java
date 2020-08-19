package com.miya.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.miya.wx.entity.Template;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miya.wx.model.Result;
import com.miya.wx.model.dto.TemplateDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-16
 */
public interface ITemplateService extends IService<Template> {

    /**
     * 获取模板详情
     *
     * @param templateId 模板id
     * @return JSONObject 模板详情
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/29 13:54
     */
    JSONObject getTemplateDetail(String templateId);

    Result addTemplate(TemplateDTO templateDTO);
}
