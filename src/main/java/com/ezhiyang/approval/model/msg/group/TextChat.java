package com.ezhiyang.approval.model.msg.group;

import lombok.Data;

/**
 * @author Caixiaowei
 * @ClassName TextChat.java
 * @Description 群里文本消息
 * @createTime 2020年06月28日 16:42:00
 */
@Data
public class TextChat {


    /**
     * chatid : CHATID
     * msgtype : text
     * text : {"content":"你的快递已到\n请携带工卡前往邮件中心领取"}
     * safe : 0
     */

    private String chatid;
    private String msgtype;
    private TextBean text;
    private int safe;


    @Data
    public static class TextBean {

        public TextBean(String content) {
            this.content = content;
        }

        private String content;

    }
}
