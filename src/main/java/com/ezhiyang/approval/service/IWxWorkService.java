package com.ezhiyang.approval.service;

/**
 * @author Caixiaowei
 * @ClassName IWXWorkService.java
 * @Description 企业微信接口
 * @createTime 2020年06月24日 16:29:00
 */
public interface IWxWorkService {

    /**
     * 获取应用接口token
     * @description 
     * @author Caixiaowei
     * @param corpsecret 应用的凭证密钥
     * @updateTime 2020/6/24 16:37
     * @return String 接口凭证token
     */
    String getAccessToken(String corpsecret);
}
