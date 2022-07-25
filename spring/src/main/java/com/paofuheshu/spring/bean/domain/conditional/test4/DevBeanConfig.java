package com.paofuheshu.spring.bean.domain.conditional.test4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:14
 * @des
 */
@Configuration
@EnvConditional(EnvConditional.Env.DEV)
public class DevBeanConfig {

    @Bean
    public String name() { return "我是开发环境!"; }
}
