package com.ezhiyang.approval.model.template.control.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Caixiaowei
 * @ClassName ControlDateRange.java
 * @Description 时长控件配置
 * @createTime 2020年07月09日 10:16:00
 */
@Data
public class ControlDateRange implements Serializable {
    private static final long serialVersionUID = -6813316414282706437L;

    private Integer perdayDuration;

    private Integer officialHoliday;

    private String type;
}
