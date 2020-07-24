package com.ezhiyang.approval.model.template;

import com.ezhiyang.approval.model.template.control.Control;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Caixiaowei
 * @ClassName TemplateContent.java
 * @Description 模板内容
 * @createTime 2020年07月08日 15:49:00
 */
@Data
public class TemplateContent implements Serializable {
    private static final long serialVersionUID = -2318489069264066433L;

    private List<Control> controls;
}
