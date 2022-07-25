package com.paofuheshu.spring.bean.domain.annotationinjection.test14;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 22:26
 * @des
 */
@ComponentScan
public class MainConfig14 {

    @Bean
    public IService service1() { return new Service1(); }

    @Bean
    @Primary
    public IService service2() { return new Service2(); }

    @Bean
    public InjectService injectService() { return new InjectService(); }
}
