package com.miya.wx.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 审批申请记录
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplyLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 申请人userid
     */
    private String userid;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 审批单号
     */
    private String spNo;

    /**
     * 状态
     */
    private Boolean status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
