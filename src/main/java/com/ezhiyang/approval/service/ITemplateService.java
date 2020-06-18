package com.ezhiyang.approval.service;

import cn.hutool.json.JSONObject;
import com.ezhiyang.approval.entity.Template;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
