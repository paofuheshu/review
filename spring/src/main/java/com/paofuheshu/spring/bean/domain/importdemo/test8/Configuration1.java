package com.paofuheshu.spring.bean.domain.importdemo.test8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:23
 * @des
 */
@Configuration
public class Configuration1 {

    @Bean
    public String name1() { System.out.println("name1"); return "name1"; }
}
