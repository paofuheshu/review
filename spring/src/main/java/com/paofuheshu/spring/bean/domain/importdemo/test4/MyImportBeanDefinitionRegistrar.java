package com.paofuheshu.spring.bean.domain.importdemo.test4;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 22:19
 * @des
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * registerBeanDefinitions方法，内部注册了2个bean，Service1和Service2。
     上面使用了BeanDefinitionBuilder这个类，这个是BeanDefinition的构造器，内部提供了很多静
     态方法方便构建BeanDefinition对象。
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 定义一个bean：Service1
        BeanDefinition service1 = BeanDefinitionBuilder.genericBeanDefinition(Service1.class).getBeanDefinition();
        // 注册bean
        registry.registerBeanDefinition("service1", service1);
        // 定义一个bean：Service2，通过addPropertyReference注入service1
        BeanDefinition service2 = BeanDefinitionBuilder.genericBeanDefinition(Service2.class)
                .addPropertyReference("service1", "service1")
                .getBeanDefinition();
        // 注册bean
        registry.registerBeanDefinition("service2", service2);
    }
}
