package com.paofuheshu.spring.bean.domain.lazyinit;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 18:22
 * @des
 */
public class LazyInitBean {

    public LazyInitBean() {
        System.out.println("我是延迟初始化的bean!");
    }
}
