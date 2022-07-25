package com.paofuheshu.spring.bean.domain.methodbean.applicationcontextaware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 19:14
 * @des
 */
public class ServiceB implements ApplicationContextAware {

    private ApplicationContext context;

    public void say() {
        ServiceA serviceA = this.getServiceA();
        System.out.println("this:"+ this +", serviceA: "+ serviceA);
    }

    public ServiceA getServiceA() {
        return this.context.getBean(ServiceA.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
