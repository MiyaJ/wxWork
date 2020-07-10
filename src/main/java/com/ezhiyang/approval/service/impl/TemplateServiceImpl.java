package com.ezhiyang.approval.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.entity.Template;
import com.ezhiyang.approval.mapper.TemplateMapper;
import com.ezhiyang.approval.model.Result;
import com.ezhiyang.approval.model.dto.TemplateDTO;
import com.ezhiyang.approval.service.IApprovalService;
import com.ezhiyang.approval.service.ITemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezhiyang.approval.util.OkHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-16
 */
@Service
@Slf4j
public class TemplateServiceImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateService {

    @Autowired
    private IApprovalService approvalService;

    @Override
    public JSONObject getTemplateDetail(String templateId) {

        String url = "https://qyapi.weixin.qq.com/cgi-bin/oa/gettemplatedetail?access_token=" + approvalService.getAccessToken();
        Map<String, Object> params = new HashMap<>();
        params.put("template_id", templateId);
        String result = OkHttpClientUtil.doPost(url, null, params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        int errcode = jsonObject.getIntValue("errcode");
        JSONArray templateNames = jsonObject.getJSONArray("template_names");
        JSONObject zhTemplateName = templateNames.getJSONObject(0);
        return JSONObject.parseObject(result);
    }

    @Override
    public Result addTemplate(TemplateDTO templateDTO) {
        String templateId = templateDTO.getTemplateId();
        JSONObject templateDetail = this.getTemplateDetail(templateId);
        JSONArray templateNames = templateDetail.getJSONArray("template_names");
        JSONObject templateContent = templateDetail.getJSONObject("template_content");

        JSONObject zhTemplateName = templateNames.getJSONObject(0);
        String templateName = zhTemplateName.getString("text");

        log.info("templateContent 模板内容--->{}", templateContent);

        JSONArray controls = templateContent.getJSONArray("controls");
        for (Object controlObject : controls) {
            JSONObject controlJson = (JSONObject) controlObject;
            JSONObject property = controlJson.getJSONObject("property");
            JSONObject config = controlJson.getJSONObject("config");
            
            if (property != null) {
                String control = property.getString("control");
                String id = property.getString("id");
                JSONObject title = property.getJSONArray("title").getJSONObject(0);
                String titleText = title.getString("text");
                JSONObject placeholder = property.getJSONArray("placeholder").getJSONObject(0);
                String placeholderText = placeholder.getString("text");
                Integer require = property.getInteger("require");
                Integer unPrint = property.getInteger("un_print");

                if ("Selector".equalsIgnoreCase(control)) {
                    // 选择控件
                    JSONObject selector = config.getJSONObject("selector");
                    JSONArray options = config.getJSONArray("options");
                    for (Object optionObject : options) {
                        JSONObject option = (JSONObject) optionObject;
                        String key = option.getString("key");
                        JSONObject value = option.getJSONArray("value").getJSONObject(0);
                        String text = value.getString("text");
                    }

                    // single 单选; multi 多选
                    String type = selector.getString("type");
                } else if ("Date".equalsIgnoreCase(control)) {
                    // 时间
                }
            }
        }


//        Template template = BeanUtil.copyProperties(templateDTO, Template.class);
//        template.setCreateTime(LocalDateTime.now());
//        template.setUpdateTime(LocalDateTime.now());
//        boolean save = this.save(template);
//        if (save) {
//            return Result.sucess("新增模板成功!");
//        } else {
//            return Result.error("新增模板失败!");
//        }
        return Result.sucess("新增模板成功!");
    }
}
