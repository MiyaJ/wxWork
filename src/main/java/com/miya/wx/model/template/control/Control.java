package com.miya.wx.model.template.control;

import com.miya.wx.model.template.control.config.ControlConfig;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Caixiaowei
 * @ClassName Control.java
 * @Description 模板控件
 * @createTime 2020年07月08日 15:27:00
 */
@Data
public class Control implements Serializable {
    private static final long serialVersionUID = 7856091128201100088L;

    /**
     * 模板控件属性
     */
    private ControlProperty property;

    /**
     * 模板控件配置
     */
    private ControlConfig config;
}
