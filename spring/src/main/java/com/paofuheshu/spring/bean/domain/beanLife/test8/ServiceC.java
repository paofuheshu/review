package com.paofuheshu.spring.bean.domain.beanLife.test8;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 17:10
 * @des
 */
public class ServiceC implements DisposableBean {

    public ServiceC() { System.out.println("创建ServiceC实例"); }

    @PreDestroy
    public void preDestroy1() { System.out.println("preDestroy1()"); }

    @PreDestroy
    public void preDestroy2() { System.out.println("preDestroy2()"); }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean接口中的destroy()");
    }

    /**
     * 自定义的销毁方法
     */
    public void customDestroyMethod() {
        System.out.println("我是自定义的销毁方法:customDestroyMethod()");
    }
}
