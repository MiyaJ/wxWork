package com.ezhiyang.approval.model.template.control.config;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Caixiaowei
 * @ClassName ControlSelector.java
 * @Description TODO
 * @createTime 2020年07月08日 15:39:00
 */
@Data
public class ControlSelector implements Serializable {
    private static final long serialVersionUID = 5165719321376700796L;

    private String type;

    private List<ControlSelectorOption> options;
}
