package com.ezhiyang.approval.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ezhiyang.approval.entity.Template;
import com.ezhiyang.approval.mapper.TemplateMapper;
import com.ezhiyang.approval.service.IApprovalService;
import com.ezhiyang.approval.service.ITemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezhiyang.approval.util.OkHttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-16
 */
@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateService {

    @Autowired
    private IApprovalService approvalService;

    @Override
    public JSONObject getTemplateDetail(String templateId) {

        String url = "https://qyapi.weixin.qq.com/cgi-bin/oa/gettemplatedetail?access_token=" + approvalService.getAccessToken();
        Map<String, Object> params = new HashMap<>();
        params.put("template_id", templateId);
        String result = OkHttpClientUtil.doPost(url, null, params);
        return JSONUtil.parseObj(result);
    }
}
