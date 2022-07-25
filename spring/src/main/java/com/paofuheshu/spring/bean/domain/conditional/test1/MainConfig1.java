package com.paofuheshu.spring.bean.domain.conditional.test1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:50
 * @des
 */
@Configuration
@Conditional(MyCondition1.class)
public class MainConfig1 {

    @Bean
    public String name() {
        return "泡芙和树";
    }
}
