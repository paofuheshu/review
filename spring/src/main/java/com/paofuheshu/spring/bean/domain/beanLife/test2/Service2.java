package com.paofuheshu.spring.bean.domain.beanLife.test2;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 17:30
 * @des
 */
public class Service2 {

    @Autowired
    private Service1 service1;

    @Override
    public String toString() {
        return "Service2{" +
                "service1=" + service1 +
                '}';
    }
}
