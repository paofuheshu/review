package com.paofuheshu.spring.bean.domain.annotationinjection.test4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 19:16
 * @des
 */
@Component
public class Service2 {

    private Service1 service1;

    /**
     * 方法上标注了@Autowired，表示会将这个方法作为注入方法，这个方法有2个参数，spring
     * 查找这2个参数对应的bean，然后注入。
     * 第一个参数对应的bean是存在的，第二个是一个String类型的，我们并没有定义String类型bean
     */
    @Autowired
    public void injectService1(Service1 service1, @Autowired(required = false) String name) {
        System.out.printf("%s.injectService1(), {service1=%s,name=%s}%n", this.getClass().getName(), service1, name);
        this.service1 = service1;
    }
}
