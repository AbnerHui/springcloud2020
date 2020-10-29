package com.abner.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.abner.springcloud.dao"})
public class MyBatisConfig {
}
