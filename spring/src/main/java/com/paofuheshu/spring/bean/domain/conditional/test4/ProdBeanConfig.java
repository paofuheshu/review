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
@EnvConditional(EnvConditional.Env.PROD)
public class ProdBeanConfig {

    @Bean
    public String name() { return "我是生产环境!"; }
}
