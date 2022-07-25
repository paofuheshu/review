package com.paofuheshu.spring.bean.domain.methodbean.replcaemethod;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 19:33
 * @des
 */
public class ServiceB {

    public void say() {
        ServiceA serviceA = this.getServiceA();
        System.out.println("this:" + this + ",serviceA:" + serviceA);
    }

    public ServiceA getServiceA() {
        return null;
    }
}
