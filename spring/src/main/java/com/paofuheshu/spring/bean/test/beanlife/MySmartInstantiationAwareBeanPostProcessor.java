package com.paofuheshu.spring.bean.test.beanlife;

import com.paofuheshu.spring.bean.domain.beanLife.test4.MyAutowired;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 15:27
 * @des
 */
public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println(beanClass);
        System.out.println("调用 MySmartInstantiationAwareBeanPostProcessor.determineCandidateConstructors 方法");
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        if (declaredConstructors != null) {
            // 获取有@MyAutowried注解的构造器列表
            List<Constructor<?>> constructors = Arrays.asList(declaredConstructors);
            constructors = constructors.stream().filter(constructor -> constructor.isAnnotationPresent(MyAutowired.class)).collect(Collectors.toList());
            return constructors.size() == 0 ? null : constructors.toArray(new Constructor[0]);
        }
        return new Constructor[0];
    }
}
