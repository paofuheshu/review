package com.paofuheshu.spring.bean.domain.value.test4;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 18:55
 * @des
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Scope(BeanRefreshScope.SCOPE_REFRESH)
@Documented
public @interface RefreshScope {

    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
}
