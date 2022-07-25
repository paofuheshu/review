package com.paofuheshu.spring.bean.domain.annotationinjection.test1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 19:02
 * @des
 */
@Component
public class Service2 {

    private Service1 service1;

    public Service2() {
        System.out.println(this.getClass() + "无参构造器");
    }

    @Autowired
    public Service2(Service1 service1) {
        System.out.println(this.getClass() + "有参构造器");
        this.service1 = service1;
    }

    @Override
    public String toString() {
        return "Service2{" +
                "service1=" + service1 +
                '}';
    }
}
