package com.chang.chill.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlus配置
 * Created by ChangHeXiang on 2019/7/24.
 */
@Configuration
@MapperScan("com.chang.chill.mapper")
public class MyBatisPlusConfig {
}
