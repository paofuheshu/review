package com.paofuheshu.spring.bean.domain.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 22:03
 * @des
 * 注解@AliasFor如果不指定
 * annotation 参数的值，那么 annotation 默认值就是当前注解，所以上面2个属性互为别名，当
 * 给v1设置值的时候也相当于给v2设置值，当给v2设置值的时候也相当于给v1设置值。
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface Ann13 {

    @AliasFor("v2")
    String v1() default "";

    @AliasFor("v1")
    String v2() default "";
}

@Ann13(v1 = "我是v1")
public class UseAnnForAliasFor {

    @Ann13(v2 = "我是v2")
    String name;

    /**
     * 1 @com.paofuheshu.spring.bean.domain.annotation.Ann13(v1="我是v1", v2="我是v1")
     * 2 @com.paofuheshu.spring.bean.domain.annotation.Ann13(v1="我是v2", v2="我是v2")
     * 从输出中可以看出v1和v2的值始终是相等的，上面如果同时给v1和v2设置值的时候运行代码会报错。
     */
    public static void main(String[] args) {
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseAnnForAliasFor.class, Ann13.class));
        try {
            System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseAnnForAliasFor.class.getDeclaredField("name"), Ann13.class));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
