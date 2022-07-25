package com.paofuheshu.spring.bean.test.beanlife;

import com.paofuheshu.spring.bean.domain.beanLife.test6.AwareBean;
import com.paofuheshu.spring.bean.domain.beanLife.test6.Bean1;
import com.paofuheshu.spring.bean.domain.beanLife.test6.Service;
import com.paofuheshu.spring.bean.domain.beanLife.test7.MainConfig;
import com.paofuheshu.spring.bean.domain.beanLife.test7.MySmartInitializingSingleton;
import com.paofuheshu.spring.bean.domain.beanLife.test7.Service1;
import com.paofuheshu.spring.bean.domain.beanLife.test7.Service2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 16:13
 * @des
 */
public class BeanLifeStage8 {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    public static void test1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("awareBean", BeanDefinitionBuilder.genericBeanDefinition(AwareBean.class).getBeanDefinition());
        // 调用getBean方法获取bean，将触发bean的初始化
        factory.getBean("awareBean");
    }

    public static void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Bean1.class);
        context.refresh();
    }

    /**
     * 初始化方法测试
     */
    public static void test3() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("service", BeanDefinitionBuilder.genericBeanDefinition(Service.class).setInitMethodName("init").getBeanDefinition());
        System.out.println(factory.getBean("service"));
    }

    /**
     * bean初始化后置处理
     */
    public static void test4() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 加入bean初始化后置处理器方法实现
        factory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                System.out.println("postProcessAfterInitialization：" + beanName);
                return bean;
            }
        });
        // 下面注册2个String类型的bean
        factory.registerBeanDefinition("name", BeanDefinitionBuilder.genericBeanDefinition(String.class)
                .addConstructorArgValue("泡芙和树")
                .getBeanDefinition());
        factory.registerBeanDefinition("personInformation", BeanDefinitionBuilder.genericBeanDefinition(String.class)
                .addConstructorArgValue("酒精泡芙")
                .getBeanDefinition());
        System.out.println("-------输出bean信息---------");
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, factory.getBean(beanName));
        }
    }

    public static void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        System.out.println("开始启动容器!");
        context.refresh();
        System.out.println("容器启动完毕!");
    }

    public static void test6() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("service1", BeanDefinitionBuilder.genericBeanDefinition(Service1.class).getBeanDefinition());
        factory.registerBeanDefinition("service2", BeanDefinitionBuilder.genericBeanDefinition(Service2.class).getBeanDefinition());
        factory.registerBeanDefinition("mySmartInitializingSingleton", BeanDefinitionBuilder.genericBeanDefinition(MySmartInitializingSingleton.class)
                .getBeanDefinition());
        System.out.println("准备触发所有单例bean初始化");
        // 触发所有bean初始化，并且回调 SmartInitializingSingleton#afterSingletonsInstantiated 方法
        factory.preInstantiateSingletons();
    }
}
