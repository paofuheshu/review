package com.paofuheshu.spring.bean.domain.annotationinjection.test15;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 13:36
 * @des
 */
@Configuration
public class MainConfig15 {

    @Bean
    public Service1 service1() { return new Service1(); }

    @Bean public Service2 service2() { return new Service2(); }

    /**
     * 通过@Bean的方式创建上面对象
     * Service3中需要用到Service1和Service2，直接调用当前方法获取另外2个bean，注入到service3中
     */
    @Bean
    public Service3 service3() {
        Service3 service3 = new Service3();
        service3.setService1(this.service1());
        service3.setService2(this.service2());
        return service3;
    }

    /**
     * 通过@Bean标注的方法使用参数来进行注入
     * 方法上标注了@Bean，并且方法中是有参数的，spring调用这个方法创建
     * bean的时候，会将参数中的两个参数注入进来。
     */
    @Bean public Service3 service4(Service1 s1, Service2 s2) {
        Service3 service3 = new Service3();
        service3.setService1(s1);
        service3.setService2(s2);
        return service3;
    }
}
