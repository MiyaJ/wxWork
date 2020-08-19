package com.miya.wx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 审批抄送人信息
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_approval_sp_notifyer")
public class ApprovalSpNotifyer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 审批单号
     */
    private String spNo;

    /**
     * 企业微信用户id
     */
    private String userId;

    /**
     * 员工id
     */
    private Long empId;

    /**
     * 员工姓名
     */
    private String empName;


}
