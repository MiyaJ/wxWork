package com.ezhiyang.approval.api;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.ezhiyang.approval.common.enums.MsgTypeEnum;
import com.ezhiyang.approval.model.dto.chat.GroupChatCreateDTO;
import com.ezhiyang.approval.model.msg.ImageMsg;
import com.ezhiyang.approval.model.msg.MsgVO;
import com.ezhiyang.approval.model.msg.NewsMsg;
import com.ezhiyang.approval.model.msg.TextMsg;
import com.ezhiyang.approval.model.msg.group.TextChat;
import com.ezhiyang.approval.service.IApprovalService;
import com.ezhiyang.approval.service.IMessageService;
import jdk.nashorn.internal.runtime.options.LoggingOption;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
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
@Slf4j
public class MessageTest {

    @Autowired
    private IMessageService messageService;

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
        textMsg.setText(new TextMsg.TextBean("加薪~  " + DateUtil.now() + "  \n<a href=\"https://www.baidu.com" +
                ".com\">百度一下, 你就知道</a>"));
        textMsg.setSafe(0);
        textMsg.setEnable_id_trans(0);
        textMsg.setEnable_duplicate_check(0);
        textMsg.setDuplicate_check_interval(1800);

        MsgVO msgVO = this.messageService.sendTextMsg(textMsg);
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

        MsgVO msgVO = this.messageService.sendImage(imageMsg);
        int errcode = msgVO.getErrcode();
        Assert.isTrue(0 == errcode, "发送失败.");
    }

    /**
     * 发送图文消息
     */
    @Test
    public void test_sendNews() {
        NewsMsg newsMsg = new NewsMsg();
        newsMsg.setTouser("cxw0615|zcl0615");
        newsMsg.setToparty(StringUtils.EMPTY);
        newsMsg.setTotag(StringUtils.EMPTY);
        newsMsg.setMsgtype(MsgTypeEnum.NEWS.getValue());
        newsMsg.setAgentid(1000002);
        newsMsg.setSafe(0);
        newsMsg.setEnable_duplicate_check(0);
        newsMsg.setDuplicate_check_interval(1800);

        NewsMsg.NewsBean newsBean = new NewsMsg.NewsBean();

        NewsMsg.NewsBean.ArticlesBean article = new NewsMsg.NewsBean.ArticlesBean();
        article.setTitle("hello world");
        article.setDescription("世界, 你好");
        article.setUrl("https://www.baidu.com");
        article.setPicurl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1592564473272&di=6b6f6ca4166b46aaa234f4f017c2e3ca&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F01%2F90%2F7b96b74a0a2b615089859de45551ecc0.jpg");

        newsBean.setArticles(Lists.newArrayList(article));

        newsMsg.setNews(newsBean);
        MsgVO msgVO = this.messageService.sendNews(newsMsg);
        int errcode = msgVO.getErrcode();
        Assert.isTrue(0 == errcode, "发送失败.");
    }

    /**
     * 创建群聊
     *
     * @param
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 15:42
     */
    @Test
    public void test_createGroupChat() {
        GroupChatCreateDTO dto = new GroupChatCreateDTO();
        dto.setName("测试群1");
        dto.setUserlist(Lists.newArrayList("cxw0615", "zcl0615"));
        String chatid = this.messageService.createGroupChat(dto);
        log.info("测试群1 : {}", chatid);
    }

    /**
     * 发送群聊文本信息
     *
     * @param
     * @return
     * @description
     * @author Caixiaowei
     * @updateTime 2020/6/28 16:12
     */
    @Test
    public void test_groupChat_text() {
        String chatid = "wrDFYVCgAA-6fSrWUQnc5lmg4Oa8piIQ";

        TextChat textChat = new TextChat();
        textChat.setChatid(chatid);
        textChat.setMsgtype("text");
        textChat.setText(new TextChat.TextBean("@郑重林 涨薪升职"));
        textChat.setSafe(0);

        JSONObject data = (JSONObject) JSONObject.toJSON(textChat);
        this.messageService.sendGroupChat(data);

    }
}
