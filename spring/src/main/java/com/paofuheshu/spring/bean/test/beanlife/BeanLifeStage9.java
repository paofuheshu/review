package com.paofuheshu.spring.bean.test.beanlife;

import com.paofuheshu.spring.bean.domain.beanLife.test8.*;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 17:00
 * @des
 */
public class BeanLifeStage9 {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    public static void test1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 添加自定义的DestructionAwareBeanPostProcessor
        factory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 向容器中注入3个单例bean
        factory.registerBeanDefinition("serviceA1", BeanDefinitionBuilder.genericBeanDefinition(ServiceA.class).getBeanDefinition());
        factory.registerBeanDefinition("serviceA2", BeanDefinitionBuilder.genericBeanDefinition(ServiceA.class).getBeanDefinition()) ;
        factory.registerBeanDefinition("serviceA3", BeanDefinitionBuilder.genericBeanDefinition(ServiceA.class).getBeanDefinition()) ;
        //触发所有单例bean初始化
        factory.preInstantiateSingletons();
        System.out.println("销毁serviceA1");
        // 销毁指定的bean
        factory.destroySingleton("serviceA1");
        System.out.println("触发所有单例bean的销毁");
        factory.destroySingletons();
    }

    public static void test2() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 添加自定义的DestructionAwareBeanPostProcessor
        factory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 将CommonAnnotationBeanPostProcessor加入
        factory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        // 向容器中注入bean
        factory.registerBeanDefinition("serviceB", BeanDefinitionBuilder.genericBeanDefinition(
                ServiceB.class).getBeanDefinition()) ;
        // 触发所有单例bean初始化
        factory.preInstantiateSingletons();
        System.out.println("销毁serviceB");
        // 销毁指定的bean
        factory.destroySingleton("serviceB");
    }

    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        // 启动容器
        System.out.println("准备启动容器");
        context.refresh();
        System.out.println("容器启动完毕");
        System.out.println("serviceC：" + context.getBean(ServiceC.class));
        // 关闭容器
        System.out.println("准备关闭容器");
        // 调用容器的close方法，会触发bean的销毁操作
        context.close();
        System.out.println("容器关闭完毕");
    }
}
