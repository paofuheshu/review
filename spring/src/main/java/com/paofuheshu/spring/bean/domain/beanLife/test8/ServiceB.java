package com.paofuheshu.spring.bean.domain.beanLife.test8;

import javax.annotation.PreDestroy;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 17:05
 * @des
 */
public class ServiceB {

    public ServiceB() { System.out.println("create " + this.getClass()); }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy()");
    }
}
