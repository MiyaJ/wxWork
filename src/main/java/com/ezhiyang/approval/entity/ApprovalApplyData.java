package com.ezhiyang.approval.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 审批申请数据
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_approval_apply_data")
public class ApprovalApplyData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 审批编号
     */
    private String spNo;

    /**
     * 控件类型
     */
    private String control;

    /**
     * 控件id
     */
    private String controlId;

    /**
     * 控件名称
     */
    private String title;

    /**
     * 控件值
     */
    private String value;


}
