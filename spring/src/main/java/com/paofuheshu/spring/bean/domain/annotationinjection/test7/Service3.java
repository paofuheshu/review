package com.paofuheshu.spring.bean.domain.annotationinjection.test7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 21:41
 * @des
 */
@Component
public class Service3 {

    @Autowired
    private List<IService> services;

    @Autowired
    private Map<String, IService> serviceMap;

    @Override
    public String toString() {
        return "Service3{" +
                "services=" + services +
                ", serviceMap=" + serviceMap +
                '}';
    }
}
