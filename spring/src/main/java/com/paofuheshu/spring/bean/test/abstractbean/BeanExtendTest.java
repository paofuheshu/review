package com.paofuheshu.spring.bean.test.abstractbean;

import com.paofuheshu.spring.bean.domain.abstractbean.ServiceB;
import com.paofuheshu.spring.bean.domain.abstractbean.ServiceC;
import com.paofuheshu.spring.utils.IocUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 18:53
 * @des
 * 1. bean元素的abstract属性为true的时候可以定义某个bean为一个抽象的bean，相当于定义了一
 * 个bean模板，spring容器并不会创建这个bean，从容器中查找abstract为true的bean的时候，
 * 会报错BeanIsAbstractException异常
 * 2. bean元素的parent属性可以指定当前bean的父bean，子bean可以继承父bean中配置信息，也
 * 可以自定义配置信息，这样可以覆盖父bean中的配置
 */
public class BeanExtendTest {

    public static void main(String[] args) {
        normalBean();
        System.out.println("================");
        beanExtend();
        getAbstract();
    }

    /**
     * 普通的bean定义
     */
    public static void normalBean() {
        ClassPathXmlApplicationContext context = IocUtil.context("abstractbean/normalBean.xml");
        System.out.println("context.getBean(ServiceB.class) = " + context.getBean(ServiceB.class));
        System.out.println("context.getBean(ServiceC.class) = " + context.getBean(ServiceC.class));
    }

    /**
     * 将公共的bean定义抽取出来
     */
    public static void beanExtend() {
        ClassPathXmlApplicationContext context = IocUtil.context("abstractbean/extendBean.xml");
        System.out.println("context.getBean(ServiceB.class) = " + context.getBean(ServiceB.class));
        System.out.println("context.getBean(ServiceC.class) = " + context.getBean(ServiceC.class));
    }

    /**
     * 此时会报错
     * org.springframework.beans.factory.BeanIsAbstractException
     * 因为 baseService 是抽象的，不能够创建这个bean实例。
     */
    public static void getAbstract() {
        ClassPathXmlApplicationContext context = IocUtil.context("abstractbean/extendBean.xml");
        System.out.println(context.getBean("baseService"));
    }
}
