package com.ezhiyang.approval.callback;

import com.ezhiyang.approval.model.callback.approval.*;
import com.ezhiyang.approval.util.crypto.WxCryptUtil;
import com.thoughtworks.xstream.XStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Caixiaowei
 * @ClassName ApprovalCallback.java
 * @Description 审批应用事件回调
 * @createTime 2020年07月01日 09:43:00
 */
@Controller
public class ApprovalCallback {

    private String token;
    private String encodingAesKey;
    private String corpid;


    @RequestMapping("/approval/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) {
        // 加密签名
        String msgSignature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        // 加密的字符串。需要解密得到消息内容明文，解密后有random、msg_len、msg、receiveid四个字段，其中msg即为消息内容明文
        String echostr = request.getParameter("echostr");

        try {

            WxCryptUtil wxCryptUtil = new WxCryptUtil(token, encodingAesKey, corpid);
            String msgXmlStr = wxCryptUtil.decrypt(msgSignature, timestamp, nonce, echostr);
            //必须要返回解密之后的明文
            response.getWriter().write(msgXmlStr);

            XStream xstream = new XStream();
            //使用注解修改对象名称
            xstream.processAnnotations(new Class[]{AppravalCallbackMessage.class, ApprovalInfo.class, ApprovalNode.class,
                    ApprovalNodeItem.class, ApprovalNotifyNode.class});
            //将字符串类型的xml转换为对象
            AppravalCallbackMessage callbackMessage = (AppravalCallbackMessage) xstream.fromXML(msgXmlStr);

            // TODO: 2020/7/1 回调信息返回调用方: mq?

        } catch (Exception e) {

        }

    }
}
