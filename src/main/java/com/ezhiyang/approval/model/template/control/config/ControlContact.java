package com.ezhiyang.approval.model.template.control.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Caixiaowei
 * @ClassName ControlContact.java
 * @Description 成员/部门控件
 * @createTime 2020年07月09日 10:23:00
 */
@Data
public class ControlContact implements Serializable {
    private static final long serialVersionUID = 6522081651298960202L;

    /**
     * 选择方式：single-单选；multi-多选
     */
    private String type;

    /**
     * 选择对象：user-成员；department-部门
     */
    private String mode;


}
