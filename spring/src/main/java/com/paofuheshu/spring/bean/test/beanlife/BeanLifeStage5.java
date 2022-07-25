package com.paofuheshu.spring.bean.test.beanlife;

import com.paofuheshu.spring.bean.domain.beanLife.test1.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 15:10
 * @des
 */
public class BeanLifeStage5 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 添加一个BeanPostProcessor：InstantiationAwareBeanPostProcessor
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
                System.out.println("调用postProcessBeforeInstantiation()");
                if (beanClass == Car.class) {
                    Car car = new Car();
                    car.setName("奥迪");
                    return car;
                }
                return null;
            }
        });

        // 定义一个car bean,车名为：宝马
        AbstractBeanDefinition carBeanDefinition = BeanDefinitionBuilder. genericBeanDefinition(Car.class)
                .addPropertyValue("name", "宝马")
                .getBeanDefinition();
        factory.registerBeanDefinition("car", carBeanDefinition);
        // 从容器中获取car这个bean的实例，输出
        System.out.println(factory.getBean("car"));
    }
}
