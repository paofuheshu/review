package com.paofuheshu.spring.bean.domain.componentScan.test4;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 19:29
 * @des
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface MyBean {

    @AliasFor(annotation = Component.class)
    String value() default "";
}
