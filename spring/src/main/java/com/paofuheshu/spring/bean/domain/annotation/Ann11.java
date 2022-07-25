package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 21:39
 * @des
 * 定义容器注解
 * 容器注解中必须有个value类型的参数，参数类型为子注解类型的数组
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Ann11 {
    Ann12[] value();
}
