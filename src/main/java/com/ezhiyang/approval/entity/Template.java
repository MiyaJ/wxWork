package com.ezhiyang.approval.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Caixiaowei
 * @since 2020-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Template implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String templateId;

    /**
     * 英文模板名称
     */
    private String templateNameEn;

    /**
     * 中文模板名称
     */
    private String templateNameZh;

    private LocalDateTime createTime;

    private Long createBy;

    private LocalDateTime updateTime;

    private Long updateBy;


}
