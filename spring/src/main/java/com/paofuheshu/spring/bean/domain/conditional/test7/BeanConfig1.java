package com.paofuheshu.spring.bean.domain.conditional.test7;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:30
 * @des
 */
@Configuration
public class BeanConfig1 {

    @Bean
    public Service service() { return new Service(); }
}
