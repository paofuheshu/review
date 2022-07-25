package com.paofuheshu.spring.bean.test.beanlife;

import com.paofuheshu.spring.bean.domain.beanLife.test4.Person;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 15:21
 * @des
 */
public class BeanLifeStage6 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 创建一个SmartInstantiationAwareBeanPostProcessor,将其添加到容器中
        factory.addBeanPostProcessor(new MySmartInstantiationAwareBeanPostProcessor());
        factory.registerBeanDefinition("name", BeanDefinitionBuilder.genericBeanDefinition(String.class).addConstructorArgValue("泡芙和树").getBeanDefinition());
        factory.registerBeanDefinition("age", BeanDefinitionBuilder.genericBeanDefinition(Integer.class).addConstructorArgValue(23).getBeanDefinition());
        factory.registerBeanDefinition("person", BeanDefinitionBuilder.genericBeanDefinition(Person.class).getBeanDefinition());
        Person person = factory.getBean("person", Person.class);
        System.out.println(person);
    }
}
