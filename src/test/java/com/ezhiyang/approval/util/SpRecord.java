package com.ezhiyang.approval.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Caixiaowei
 * @ClassName SpRecord.java
 * @Description TODO
 * @createTime 2020年07月07日 16:37:00
 */
@Data
@XStreamAlias("SpRecord")
public class SpRecord implements Serializable {
    private static final long serialVersionUID = 1257179914272641381L;

    @XStreamAlias("SpStatus")
    private Integer SpStatus;

    @XStreamAlias("ApproverAttr")
    private Integer ApproverAttr;
}
