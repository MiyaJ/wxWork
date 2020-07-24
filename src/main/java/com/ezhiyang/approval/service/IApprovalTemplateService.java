package com.ezhiyang.approval.service;

import com.ezhiyang.approval.entity.ApprovalTemplate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 审批模板  服务类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-07-24
 */
public interface IApprovalTemplateService extends IService<ApprovalTemplate> {

    /**
     * 获取审批模板详情
     * @description
     * @author Caixiaowei
     * @param templateId: 模板id
     * @updateTime 2020/7/24 14:23
     * @return
     */
    Object getDetail(String templateId);
}
