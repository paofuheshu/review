package com.paofuheshu.spring.bean.domain.value.test3;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

import javax.servlet.http.HttpSession;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 18:46
 * @des
 */
public class BeanMyScope implements Scope {

    public static final String SCOPE_MY = "my";



    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("BeanMyScope >>>>>>>>> get:" + name);
        return objectFactory.getObject();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
