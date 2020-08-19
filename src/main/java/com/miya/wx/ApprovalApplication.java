package com.miya.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Caixiaowei-zy
 */
@SpringBootApplication
@MapperScan("com.ezhiyang.approval.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class ApprovalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApprovalApplication.class, args);
    }

}
