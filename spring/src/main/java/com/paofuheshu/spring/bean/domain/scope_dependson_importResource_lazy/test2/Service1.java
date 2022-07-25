package com.paofuheshu.spring.bean.domain.scope_dependson_importResource_lazy.test2;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;


/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 14:06
 * @des
 */
@Component
@DependsOn({"service2", "service3"})
public class Service1 {

    public Service1() {
        System.out.println("create Service1");
    }
}
