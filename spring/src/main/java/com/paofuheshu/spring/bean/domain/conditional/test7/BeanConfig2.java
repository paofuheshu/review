package com.paofuheshu.spring.bean.domain.conditional.test7;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:31
 * @des
 */
@Configuration
//@Conditional(MyCondition1.class)
@Conditional(MyConfigurationCondition1.class)
public class BeanConfig2 {

    @Bean
    public String name() { return "泡芙和树"; }
}
