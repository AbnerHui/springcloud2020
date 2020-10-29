package com.abner.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    /**
     * RestTemplate是Spring提供的用于访问Rest服务的客户端，
     * RestTemplate提供了多种便捷访问远程Http服务的方法,能够大大提高客户端的编写效率。
     * @LoadBalanced(集群时使用)
     * 开启RestTemplate负载均衡功能
     */
    @Bean
    //@LoadBalanced
    /**
     * 使用我们自己写的负载均衡算法
     */
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
