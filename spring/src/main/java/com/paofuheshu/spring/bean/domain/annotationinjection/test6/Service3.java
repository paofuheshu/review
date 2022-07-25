package com.paofuheshu.spring.bean.domain.annotationinjection.test6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 19:27
 * @des
 */
@Component
public class Service3 {

    @Autowired
    private IService service1;

    @Override
    public String toString() {
        return "Service3{" +
                "service1=" + service1 +
                '}';
    }
}
