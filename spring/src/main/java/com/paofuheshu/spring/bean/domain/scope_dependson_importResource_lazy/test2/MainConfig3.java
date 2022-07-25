package com.paofuheshu.spring.bean.domain.scope_dependson_importResource_lazy.test2;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 14:11
 * @des
 */
@Configurable
public class MainConfig3 {

    @Bean
    @DependsOn({"service2", "service3"})
    public Service1 service1() { return new Service1(); }

    @Bean public Service2 service2() { return new Service2(); }

    @Bean
    public Service3 service3() { return new Service3(); }
}
