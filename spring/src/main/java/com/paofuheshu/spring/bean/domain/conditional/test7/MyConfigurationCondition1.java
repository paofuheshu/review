package com.paofuheshu.spring.bean.domain.conditional.test7;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:34
 * @des
 */
public class MyConfigurationCondition1 implements ConfigurationCondition {
    /**
     * Return the {@link ConfigurationPhase} in which the condition should be evaluated.
     */
    @Override
    public ConfigurationPhase getConfigurationPhase() {
        // 指定条件在bean注册阶段，这个条件才有效
        return ConfigurationPhase.REGISTER_BEAN;
    }

    /**
     * Determine if the condition matches.
     *
     * @param context  the condition context
     * @param metadata the metadata of the {@link AnnotationMetadata class}
     *                 or {@link MethodMetadata method} being checked
     * @return {@code true} if the condition matches and the component can be registered,
     * or {@code false} to veto the annotated component's registration
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取spring容器
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 判断容器中是否存在Service类型的bean
        boolean existsService = !beanFactory.getBeansOfType(Service.class).isEmpty();
        return existsService;
    }
}
