package com.miya.wx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *  审批申请记录
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_approval_apply")
public class ApprovalApply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 审批编号
     */
    private String spNo;

    /**
     * 业务标识
     */
    private String systemCode;

    /**
     * 审批模板id
     */
    private String templateId;

    /**
     * 审批申请类型(审批模板名称)
     */
    private String spName;

    /**
     * 审批状态 1-审批中；2-已通过；3-已驳回；4-已撤销；6-通过后撤销；7-已删除；10-已支付
     */
    private Integer status;

    /**
     * 审批人模式 0指定模式;1默认模式
     */
    private Integer useTemplateApprover;

    /**
     * 申请提交时间 审批申请提交时间,Unix时间戳
     */
    private Integer applyTime;

    /**
     * 申请人员工id 申请人员工id
     */
    private Long empId;

    /**
     * 申请人微信用户id 申请人userid
     */
    private String wxUserId;

    /**
     * 申请人wx部门id
     */
    private String wxPartyId;

    /**
     * 审批数据
     */
    private String applyData;

    /**
     * 申请失败原因
     */
    private String errorReason;

    /**
     * 调用方回调结果
     */
    private String ack;

    /**
     * 调用方回调url
     */
    private String callbackUrl;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
