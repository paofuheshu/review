package com.paofuheshu.spring.bean.domain.importdemo.test5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 19:51
 * @des
 */
@Configuration
public class Module1Config {

    @Bean
    public String name() {
        return "泡芙和树";
    }

    @Bean
    public String address() {
        return "池州市";
    }
}
