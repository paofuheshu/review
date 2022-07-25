package com.paofuheshu.spring.bean.domain.proxy.cglib;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 18:48
 * @des
 */
public class Service2 {

    public void m1() {
        System.out.println("我是m1方法");
        this.m2();
    }

    public void m2() {
        System.out.println("我是m2方法");
    }
}
