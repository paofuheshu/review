package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 21:52
 * @des  为参数指定默认值
 * 通过default为参数指定默认值，用的时候如果没有设置值，则取默认值，没有指定默认值的参数，使用
 * 的时候必须为参数设置值，如下：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann5 {

    String[] name() default {"泡芙和树", "酒精泡芙"};
    int[] score() default 1;
    int age() default 30;
    String address();
}

@Ann5(age = 23, address = "杭州")
class UserAnn5 {

}
