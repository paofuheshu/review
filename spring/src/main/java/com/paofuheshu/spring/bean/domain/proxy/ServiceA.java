package com.paofuheshu.spring.bean.domain.proxy;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 21:26
 * @des
 */
public class ServiceA implements IService {
    @Override
    public void m1() {
        System.out.println("我是ServiceA中的m1方法!");
    }

    @Override
    public void m2() {
        System.out.println("我是ServiceA中的m2方法!");
    }

    @Override
    public void m3() {
        System.out.println("我是ServiceA中的m3方法!");
    }
}
