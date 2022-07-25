package com.paofuheshu.spring.bean.domain.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 21:51
 * @des
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@A1
public @interface B1 {

    String value() default "b";

    /**
     * AliasFor注解中的value的值要对应A1直接的参数值
     */
    @AliasFor(annotation = A1.class, value = "value")
    String A1Value();
}
