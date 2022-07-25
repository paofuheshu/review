package com.paofuheshu.spring.bean.domain.importdemo.test7;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:18
 * @des
 */
@Configuration
public class Configuration3 {

    @Bean
    public String name3() { System.out.println("name3"); return "name3"; }
}
