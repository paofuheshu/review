package com.paofuheshu.spring.bean.domain.annotationinjection.test10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 22:06
 * @des
 */
@Component
public class InjectService {

    /**
     * 这里限定符的值为service2，容器中IService类型的bean有2个[service1和service2]，当类上没
     * 有标注@Qualifier注解的时候，可以理解为：bean的名称就是限定符的值，所以这里会匹配到
     * service2
     */
    @Autowired
    @Qualifier("service2")
    private IService service;

    @Override
    public String toString() {
        return "InjectService{" +
                "service1=" + service +
                '}';
    }
}
