package com.paofuheshu.spring.bean.domain.annotationinjection.test5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 19:23
 * @des
 */
@Component
public class Service3 {

    @Autowired
    private Service1 service1;

    @Autowired
    private Service2 service2;

    @Override
    public String toString() {
        return "Service3{" +
                "service1=" + service1 +
                ", service2=" + service2 +
                '}';
    }
}
