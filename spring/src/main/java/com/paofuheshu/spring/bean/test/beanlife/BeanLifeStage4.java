package com.paofuheshu.spring.bean.test.beanlife;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 14:53
 * @des
 */
public class BeanLifeStage4 {

    public static void main(String[] args) {
        test1();
    }

    /**
     * BeanDefinition 合并
     *
     */
    public static void test1() {
        // 创建bean容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 创建一个bean xml解析器
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        // 解析bean xml，将解析过程中产生的BeanDefinition注册到 DefaultListableBeanFactory中
        xmlBeanDefinitionReader.loadBeanDefinitions("beanlife/parentBean.xml");
        // 遍历容器中注册的所有bean信息
        for (String beanName : factory.getBeanDefinitionNames()) {
            //  通过bean名称获取原始的注册的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            // 获取合并之后的BeanDefinition信息
            BeanDefinition mergedBeanDefinition = factory.getMergedBeanDefinition(beanName);
            System.out.println(beanName);
            System.out.println("解析xml过程中注册的beanDefinition：" + beanDefinition);
            System.out.println("beanDefinition中的属性信息" + beanDefinition.getPropertyValues());
            System.out.println("合并之后得到的mergedBeanDefinition：" + mergedBeanDefinition);
            System.out.println("mergedBeanDefinition中的属性信息" + mergedBeanDefinition.getPropertyValues());
            System.out.println("-----------------------");
        }
    }
}
