package com.paofuheshu.spring.bean.domain.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 18:34
 * @des
 */
public class ConfigureBean3 {

    @Bean
    public ServiceA serviceA() {
        System.out.println("调用serviceA()方法");
        return new ServiceA();
    }

    @Bean ServiceB serviceB1() {
        System.out.println("调用serviceB1()方法");
        ServiceA serviceA = this.serviceA();
        return new ServiceB(serviceA);
    }

    @Bean ServiceB serviceB2() {
        System.out.println("调用serviceB2()方法");
        ServiceA serviceA = this.serviceA();
        return new ServiceB(serviceA);
    }
}
