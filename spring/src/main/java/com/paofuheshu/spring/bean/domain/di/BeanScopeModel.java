package com.paofuheshu.spring.bean.domain.di;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/4 21:48
 * @des
 */
@Component("threadBean")
@Scope("thread")
public class BeanScopeModel {

    public BeanScopeModel() {
//        System.out.printf("线程:%s,create BeanScopeModel, {scope=%s},{this=%s}%n",
//                Thread.currentThread(), "thread", this);
        System.out.println(Thread.currentThread() + ":" + this);
    }
}
