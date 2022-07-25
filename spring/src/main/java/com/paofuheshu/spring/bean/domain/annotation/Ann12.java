package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 21:40
 * @des
 * 为注解指定容器
 * 要让一个注解可以重复使用，需要在注解上加上@Repeatable注解，@Repeatable中value的值为容器注解，
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(Ann11.class)
public @interface Ann12 {
    String name();
}
