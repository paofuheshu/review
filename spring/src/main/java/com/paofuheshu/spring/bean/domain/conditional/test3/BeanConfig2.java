package com.paofuheshu.spring.bean.domain.conditional.test3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:05
 * @des
 */
public class BeanConfig2 {

    @Conditional(OnMissingBeanCondition.class)
    @Bean
    public IService service2() { return new Service2(); }
}
