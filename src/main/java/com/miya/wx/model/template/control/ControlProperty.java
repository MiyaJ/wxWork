package com.miya.wx.model.template.control;

import com.miya.wx.model.template.TextProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Caixiaowei
 * @ClassName ControlProperty.java
 * @Description 模板控件属性
 * @createTime 2020年07月08日 15:28:00
 */
@Data
public class ControlProperty implements Serializable {
    private static final long serialVersionUID = 1620988926992820626L;

    /**
     * 使用打印
     */
    private Integer unPrint;

    /**
     * 控件名称
     */
    private String control;

    /**
     * 是否必填
     */
    private Integer require;

    /**
     * 控件id
     */
    private Integer id;

    /**
     * 说明
     */
    private List<TextProperty> placeholder;

    /**
     * 标题名称
     */
    private List<TextProperty> title;
}
