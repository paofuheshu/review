package com.paofuheshu.spring.bean.domain.annotationinjection.test13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 22:20
 * @des
 */
@Component
public class InjectService {

    /**
     * 容器中IService类型的bean有2个，但是service2为主要的候选者，所以此处会注入service2
     */
    @Autowired
    private IService service1;

    @Override
    public String toString() {
        return "InjectService{" +
                "service1=" + service1 +
                '}';
    }
}
