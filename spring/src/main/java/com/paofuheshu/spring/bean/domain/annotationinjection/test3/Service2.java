package com.paofuheshu.spring.bean.domain.annotationinjection.test3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 19:12
 * @des
 */
@Component
public class Service2 {

    private Service1 service1;

    @Autowired
    public void setService1(Service1 service1) {
        System.out.println(this.getClass().getName() + ".setService1方法");
        this.service1 = service1;
    }

    @Override
    public String toString() {
        return "Service2{" +
                "service1=" + service1 +
                '}';
    }
}
