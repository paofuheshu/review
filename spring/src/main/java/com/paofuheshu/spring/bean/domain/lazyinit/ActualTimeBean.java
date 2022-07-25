package com.paofuheshu.spring.bean.domain.lazyinit;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 18:17
 * @des
 */
public class ActualTimeBean {

    public ActualTimeBean() {
        System.out.println("我是实时初始化的bean!");
    }
}
