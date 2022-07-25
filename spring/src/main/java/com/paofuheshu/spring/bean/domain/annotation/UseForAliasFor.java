package com.paofuheshu.spring.bean.domain.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 22:11
 * @des
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface A3 {
    String name() default "a";

    String age() default "23";
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@A3
@interface A4 {

    @AliasFor(annotation = A3.class)
    String name() default "b";

    @AliasFor(annotation = A3.class)
    String age();
}

@A4(name = "泡芙和树", age = "24")
public class UseForAliasFor {

    public static void main(String[] args) {
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseForAliasFor.class, A3.class));
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseForAliasFor.class, A4.class));
    }
}
