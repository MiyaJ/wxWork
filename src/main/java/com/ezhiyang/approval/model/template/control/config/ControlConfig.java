package com.ezhiyang.approval.model.template.control.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Caixiaowei
 * @ClassName ControlConfig.java
 * @Description 模板控件配置
 * @createTime 2020年07月08日 15:31:00
 */
@Data
public class ControlConfig implements Serializable {
    private static final long serialVersionUID = -1889160811699078739L;

    private String control;

    private String type;
}
