package com.ezhiyang.approval.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 审批模板与调用系统关系表
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_approval_template_system")
public class ApprovalTemplateSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 业务标识, 调用方唯一标识
     */
    private String systemCode;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 调用方回调url
     */
    private String callbackUrl;


}
