package com.paofuheshu.spring.bean.domain.annotationinjection.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 19:08
 * @des
 */
@Component
public class Service2 {

    private Service1 service1;

    @Autowired
    public void injectService1(Service1 service1) {
        System.out.println(this.getClass().getName() + ".injectService1()");
        this.service1 = service1;
    }

    @Override
    public String toString() {
        return "Service2{" +
                "service1=" + service1 +
                '}';
    }
}
