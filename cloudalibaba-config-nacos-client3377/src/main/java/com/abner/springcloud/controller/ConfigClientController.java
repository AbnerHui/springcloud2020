package com.abner.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abner
 *  <dependency>
 *    <groupId>com.alibaba.cloud</groupId>
 *    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *  </dependency>
 * @RefreshScope Nacos的动态刷新功能。
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
