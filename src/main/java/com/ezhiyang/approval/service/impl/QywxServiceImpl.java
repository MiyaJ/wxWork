package com.ezhiyang.approval.service.impl;

import com.ezhiyang.approval.service.IQywxService;
import org.springframework.stereotype.Service;

/**
 * @author Caixiaowei
 * @ClassName QywxServiceImpl.java
 * @Description 企业微信业务实现
 * @createTime 2020年06月16日 17:05:00
 */
@Service
public class QywxServiceImpl implements IQywxService {

    /**
     * @param corpsecret 应用的凭证密钥
     * @title 获取access_token
     * @description 每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     * @author Caixiaowei
     * @updateTime 2020/6/16 17:08
     */
    @Override
    public String getToken(String corpsecret) {

        return null;
    }
}
