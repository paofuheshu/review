package com.paofuheshu.spring.bean.domain.proxy.cglib;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 19:24
 * @des
 */
public class Service4 {

    public void insert1() { System.out.println("我是insert1"); }

    public void insert2() { System.out.println("我是insert2"); }

    public String get1() { System.out.println("我是get1"); return "get1"; }

    public String get2() { System.out.println("我是get2"); return "get2"; }
}
