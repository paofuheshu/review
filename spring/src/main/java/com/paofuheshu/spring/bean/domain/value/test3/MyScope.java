package com.paofuheshu.spring.bean.domain.value.test3;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 18:45
 * @des
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope(BeanMyScope.SCOPE_MY)
public @interface MyScope {

    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
}
