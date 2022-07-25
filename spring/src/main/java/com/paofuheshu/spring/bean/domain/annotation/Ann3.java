package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 21:46
 * @des  此处定义的注解只含有一个参数 并且名称为value  这样在使用该注解时  参数名称可以省略
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann3 {

    String value();
}

@Ann3("asd")
class UserAnn3 {

}