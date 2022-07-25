package com.paofuheshu.spring.bean.domain.annotationinjection.test14;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 22:26
 * @des
 */
public class InjectService {

    @Autowired
    private IService service1;

    @Override
    public String toString() {
        return "InjectService{" +
                "service1=" + service1 +
                '}';
    }
}
