package com.paofuheshu.spring.bean.domain.annotationinjection.test9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 22:02
 * @des
 */
@Component
public class InjectService {

    /**
     * 限定符的值为tag1，此时会将类上限定符为tag1的所有bean注入进来
     */
    @Autowired
    @Qualifier("tag1")
    private Map<String, IService> serviceMap1;

    /**
     * 限定符的值为tag2，此时会将类上限定符为tag2的所有bean注入进来
     */
    @Autowired
    @Qualifier("tag2")
    private Map<String, IService> serviceMap2;

    @Override
    public String toString() {
        return "InjectService{" +
                "serviceMap1=" + serviceMap1 +
                ", serviceMap2=" + serviceMap2 +
                '}';
    }
}
