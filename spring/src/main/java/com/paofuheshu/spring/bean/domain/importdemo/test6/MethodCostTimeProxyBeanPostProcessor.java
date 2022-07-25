package com.paofuheshu.spring.bean.domain.importdemo.test6;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Locale;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:06
 * @des
 */
public class MethodCostTimeProxyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getName().toLowerCase().contains("service")) {
            return CostTimeProxy.createProxy(bean);
        } else {
            return bean;
        }
    }
}
