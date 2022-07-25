package com.paofuheshu.spring.bean.domain.conditional.test4;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:12
 * @des
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(EnvCondition.class)
public @interface EnvConditional {

    enum Env {
        TEST,
        DEV,
        PROD
    }

    Env value() default Env.DEV;
}
