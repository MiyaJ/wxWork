package com.ezhiyang.approval;

import cn.hutool.core.date.DateUtil;
import com.ezhiyang.approval.common.enums.MsgTypeEnum;
import com.ezhiyang.approval.model.msg.ImageMsg;
import com.ezhiyang.approval.model.msg.MsgVO;
import com.ezhiyang.approval.model.msg.TextMsg;
import com.ezhiyang.approval.service.IQywxService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author Caixiaowei
 * @ClassName MsgTest.java
 * @Description 消息测试
 * @createTime 2020年06月19日 15:19:00
 */
@SpringBootTest
public class MsgTest {

    @Autowired
    private IQywxService qywxService;

    /**
     * 发送文本消息
     */
    @Test
    public void test_sendText() {
        TextMsg textMsg = new TextMsg();
        textMsg.setTouser("cxw0615|zcl0615");
        textMsg.setToparty(StringUtils.EMPTY);
        textMsg.setTotag(StringUtils.EMPTY);
        textMsg.setMsgtype(MsgTypeEnum.TEXT.getValue());
        textMsg.setAgentid(1000002);
        textMsg.setText(new TextMsg.TextBean("经验+3  "+ DateUtil.now() + "  \n<a href=\"https://www.baidu.com" +
                ".com\">百度一下, 你就知道</a>"));
        textMsg.setSafe(0);
        textMsg.setEnable_id_trans(0);
        textMsg.setEnable_duplicate_check(0);
        textMsg.setDuplicate_check_interval(1800);

        MsgVO msgVO = this.qywxService.sendTextMsg(textMsg);
        int errcode = msgVO.getErrcode();
        Assert.isTrue(0 == errcode, "发送失败.");
    }

    /**
     * 发送图片消息
     */
    @Test
    public void test_sendImage() {
        ImageMsg imageMsg = new ImageMsg();
        imageMsg.setTouser("cxw0615|zcl0615");
        imageMsg.setToparty(StringUtils.EMPTY);
        imageMsg.setTotag(StringUtils.EMPTY);
        imageMsg.setMsgtype(MsgTypeEnum.IMAGE.getValue());
        imageMsg.setAgentid(1000002);
        imageMsg.setImage(new ImageMsg.ImageBean("33YyGv4sSGHobWvWqwba-LyaBaVJz7FNjsDpKlkkjPLXRhSyKUWf63zZdelahAAbH"));
        imageMsg.setSafe(0);
        imageMsg.setEnable_duplicate_check(0);
        imageMsg.setDuplicate_check_interval(1800);

        MsgVO msgVO = this.qywxService.sendImage(imageMsg);
        int errcode = msgVO.getErrcode();
        Assert.isTrue(0 == errcode, "发送失败.");
    }

}
