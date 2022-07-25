package com.paofuheshu.spring.bean.test.beanfactory.scope;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/4 21:56
 * @des
 */
@Component
public class ScopeConfig implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        // 向容器中注册自定义的scope
        beanFactory.registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());
    }
}
