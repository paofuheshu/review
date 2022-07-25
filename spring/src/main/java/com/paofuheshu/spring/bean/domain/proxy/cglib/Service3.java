package com.paofuheshu.spring.bean.domain.proxy.cglib;

import lombok.Data;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 19:16
 * @des
 */
public class Service3 {

    public String m1() {
        System.out.println("我是m1方法");
        return "hello:m1";
    }

    public String m2() {
        System.out.println("我是m2方法");
        return "hello:m2";
    }


}
