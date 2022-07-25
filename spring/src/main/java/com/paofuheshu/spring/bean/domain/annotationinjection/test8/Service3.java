package com.paofuheshu.spring.bean.domain.annotationinjection.test8;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 21:53
 * @des
 */
@Component
public class Service3 {

    @Resource
    private IService service1;

    @Override
    public String toString() {
        return "Service3{" +
                "service1=" + service1 +
                '}';
    }
}
