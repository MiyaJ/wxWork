package com.ezhiyang.approval.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ezhiyang.approval.common.RedisConstans;
import com.ezhiyang.approval.service.IQywxService;
import com.ezhiyang.approval.util.OkHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Caixiaowei
 * @ClassName QywxServiceImpl.java
 * @Description 企业微信业务实现
 * @createTime 2020年06月16日 17:05:00
 */
@Slf4j
@Service
public class QywxServiceImpl implements IQywxService {

    @Value("${qywx.corpid}")
    private String CORPID;



    private String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    @Autowired
    private RedisService redisService;
    /**
     * @param corpsecret 应用的凭证密钥
     * @title 获取access_token
     * @description 每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取
     * @author Caixiaowei
     * @updateTime 2020/6/16 17:08
     */
    @Override
    public String getToken(String corpsecret) {
        String accessToken = String.valueOf(redisService.get(RedisConstans.QYWX_ACCESS_TOKEN_KEY));
        if (StringUtils.isBlank(accessToken) || "null".equalsIgnoreCase(accessToken)) {
            Map<String, String> params = MapUtil.newHashMap();
            params.put("corpid", this.CORPID);
            params.put("corpsecret", corpsecret);
            try {
                String result = OkHttpClientUtil.doGet(ACCESS_TOKEN_URL, null, params);
                JSONObject json = JSONUtil.parseObj(result);
                if (json.getInt("errcode") != null && json.getInt("errcode") == 0) {
                    accessToken = json.getStr("access_token");
                    redisService.set(RedisConstans.QYWX_ACCESS_TOKEN_KEY, accessToken, RedisConstans.QYWX_ACCESS_TOKEN_EXPIRATION);
                }
            } catch (Exception e) {
                log.error("获取审批应用access_token 异常--->{}", e);
            }

        }
        return accessToken;
    }

}
