package com.paofuheshu.spring.bean.domain.conditional.test4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:13
 * @des
 */
@Configuration
@EnvConditional(EnvConditional.Env.TEST)
public class TestBeanConfig {

    @Bean
    public String name() { return "我是测试环境!"; }
}
