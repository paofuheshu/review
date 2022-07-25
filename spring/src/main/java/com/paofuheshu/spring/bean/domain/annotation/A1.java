package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 21:50
 * @des
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface A1 {
    String value() default "a";
}
