package com.paofuheshu.spring.bean.test.beanlife;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.Arrays;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 17:43
 * @des
 */
public class BeanLifeStage3 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        // 创建一个bean工厂，这个默认实现了BeanDefinitionRegistry接口，所以也是一个bean注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 定义一个bean
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(String.class);
        beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, "泡芙和树");
        // 将bean注册到容器中
        factory.registerBeanDefinition("name", beanDefinition);
        // 通过名称判断是否注册过BeanDefinition
        System.out.println(factory.containsBeanDefinition("name"));
        // 获取所有注册的名称
        System.out.println(Arrays.asList(factory.getBeanDefinitionNames()));
        // 获取已注册的BeanDefinition的数量
        System.out.println(factory.getBeanDefinitionCount());
        // 判断指定的name是否使用过
        System.out.println(factory.isBeanNameInUse("name"));

        // 别名相关方法
        // 为name注册2个别名
        factory.registerAlias("name", "alias-name-1");
        factory.registerAlias("name", "alias-name-2");

        // 判断alias-name-1是否已被作为别名使用
        System.out.println(factory.isAlias("alias-name-1"));
        System.out.println(factory.isAlias("alias-name-3"));

        // 通过名称获取对应的所有别名
        System.out.println(Arrays.asList(factory.getAliases("name")));

        // 获取一下这个bean
        System.out.println(factory.getBean("name"));
    }
}
