package com.paofuheshu.spring.bean.domain.conditional.test3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:04
 * @des
 */
@Configuration
public class BeanConfig1 {

    @Conditional(OnMissingBeanCondition.class)
    @Bean
    public IService service1() { return new Service1(); }
}
