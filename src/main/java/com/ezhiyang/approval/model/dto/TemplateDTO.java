package com.ezhiyang.approval.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Caixiaowei
 * @ClassName TemplateDTO.java
 * @Description 模板dto
 * @createTime 2020年06月28日 13:53:00
 */
@Data
public class TemplateDTO implements Serializable {

    private static final long serialVersionUID = 5383925854671018989L;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 英文模板名称
     */
    private String templateNameEn;

    /**
     * 中文模板名称
     */
    private String templateNameZh;
}
