package com.miya.wx.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Caixiaowei
 * @ClassName Xml.java
 * @Description TODO
 * @createTime 2020年07月07日 16:33:00
 */
@Data
@XStreamAlias("xml")
public class TestXml implements Serializable {
    private static final long serialVersionUID = -2780799480805808419L;

    @XStreamImplicit(itemFieldName="SpRecord")
    private List<SpRecord> SpRecords;
}
