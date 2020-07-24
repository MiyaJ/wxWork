package com.ezhiyang.approval.service.impl;

import com.ezhiyang.approval.entity.ApprovalTemplate;
import com.ezhiyang.approval.mapper.ApprovalTemplateMapper;
import com.ezhiyang.approval.service.IApprovalTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审批模板  服务实现类
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-07-24
 */
@Service
public class ApprovalTemplateServiceImpl extends ServiceImpl<ApprovalTemplateMapper, ApprovalTemplate> implements IApprovalTemplateService {

    /**
     * 获取审批模板详情
     *
     * @param templateId : 模板id
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/7/24 14:23
     */
    @Override
    public Object getDetail(String templateId) {
        return null;
    }
}
