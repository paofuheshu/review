package com.paofuheshu.spring.bean.domain.conditional.test3;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import java.util.Map;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:02
 * @des
 */
public class OnMissingBeanCondition implements Condition {

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 从容器中获取IService类型bean
        Map<String, IService> beans = beanFactory.getBeansOfType(IService.class);
        // 判断serviceMap是否为空
        return beans.isEmpty();
    }
}
