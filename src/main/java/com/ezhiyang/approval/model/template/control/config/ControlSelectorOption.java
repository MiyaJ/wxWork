package com.ezhiyang.approval.model.template.control.config;

import com.ezhiyang.approval.model.template.TextProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Caixiaowei
 * @ClassName ControlSelectorOption.java
 * @Description TODO
 * @createTime 2020年07月08日 15:41:00
 */
@Data
public class ControlSelectorOption implements Serializable {
    private static final long serialVersionUID = 9006410757033177451L;

    private String key;

    private List<TextProperty> value;
}
