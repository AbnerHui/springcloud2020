package com.abner.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置类不能放在@ComponentScan所扫描得当前包以及子包下
 * 否则我们自定义的这个配置类会被Ribbon客户端共享,达不到特殊化定制的目的
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        /**
         * 定义为随机
         */
        return new RandomRule();
    }
}
