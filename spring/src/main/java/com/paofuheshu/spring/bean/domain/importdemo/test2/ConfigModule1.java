package com.paofuheshu.spring.bean.domain.importdemo.test2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 21:58
 * @des 模块1配置类
 */
@Configuration
public class ConfigModule1 {

    @Bean
    public String module1() {
        return "我是模块1配置类！";
    }
}
