package com.paofuheshu.spring.bean.domain.importdemo.test8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:24
 * @des
 */
@Configuration
public class Configuration2 {

    @Bean
    public String name2() { System.out.println("name2"); return "name2"; }
}
