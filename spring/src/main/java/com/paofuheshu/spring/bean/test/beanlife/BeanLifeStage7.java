package com.paofuheshu.spring.bean.test.beanlife;

import com.paofuheshu.spring.bean.domain.beanLife.test5.UserModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 15:52
 * @des
 */
public class BeanLifeStage7 {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    /**
     * 定义注册两个简单的bean
     */
    public static void test1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("user1", BeanDefinitionBuilder
                .genericBeanDefinition(UserModel.class)
                .addPropertyValue("name", "泡芙和树")
                .addPropertyValue("age", 23)
                .getBeanDefinition());
        factory.registerBeanDefinition("user2", BeanDefinitionBuilder
                .genericBeanDefinition(UserModel.class)
                .addPropertyValue("name", "酒精泡芙")
                .addPropertyValue("age", 24)
                .getBeanDefinition());
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, factory.getBean(beanName));
        }
    }

    /**
     * 返回false，可以阻止bean属性的赋值
     */
    public static void test2() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
                if ("user1".equals(beanName)) {
                    return false;
                } else { return true; }
            }
        });
        factory.registerBeanDefinition("user1", BeanDefinitionBuilder
                .genericBeanDefinition(UserModel.class)
                .addPropertyValue("name", "泡芙和树")
                .addPropertyValue("age", 23)
                .getBeanDefinition());
        factory.registerBeanDefinition("user2", BeanDefinitionBuilder
                .genericBeanDefinition(UserModel.class)
                .addPropertyValue("name", "酒精泡芙")
                .addPropertyValue("age", 24)
                .getBeanDefinition());
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, factory.getBean(beanName));
        }
    }

    public static void test3() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
                if ("user1".equals(beanName)) {
                    if (pvs == null) {
                        pvs = new MutablePropertyValues();
                    }
                    if (pvs instanceof MutablePropertyValues) {
                        MutablePropertyValues mpvs = (MutablePropertyValues) pvs;
                        mpvs.add("name", "张强");
                        mpvs.add("age", 18);
                    }
                }
                return null;
            }
        });
        factory.registerBeanDefinition("user1", BeanDefinitionBuilder
                .genericBeanDefinition(UserModel.class)
                .addPropertyValue("name", "泡芙和树")
                .addPropertyValue("age", 23)
                .getBeanDefinition());
        factory.registerBeanDefinition("user2", BeanDefinitionBuilder
                .genericBeanDefinition(UserModel.class)
                .addPropertyValue("name", "酒精泡芙")
                .addPropertyValue("age", 24)
                .getBeanDefinition());
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, factory.getBean(beanName));
        }
    }
}
