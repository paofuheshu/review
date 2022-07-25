package com.paofuheshu.spring.bean.domain.beanLife.test6;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 16:29
 * @des
 */
public class Service implements InitializingBean {

    public void init() {
        System.out.println("调用init()方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用afterPropertiesSet()");
    }
}
