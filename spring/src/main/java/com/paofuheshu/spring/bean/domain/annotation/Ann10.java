package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 21:34
 * @des  如果在类UseAnn10上使用了两个注解Ann10  此时代码会报错
 * 因为默认情况下@Ann12注解是不允许重复使用的。
 * 如果我们想重复使用注解的时候，需要用到 @Repeatable 注解
 *
 * 使用步骤
 * 1：先定义容器注解
 * 容器注解中必须有个value类型的参数，参数类型为子注解类型的数组 如同包内的Ann11注解
 * 2：为注解指定容器
 * 要让一个注解可以重复使用，需要在注解上加上@Repeatable注解，@Repeatable中value的值为容器注解，见同包内的Ann12注解
 * 使用注解
 * 重复使用相同的注解有2种方式
 * 1. 重复使用注解，在类上重复使用@Ann12注解
 * 2. 通过容器注解来使用更多个注解，如类UseAnn12字段name上使用@Ann12s容器注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann10 {

}

//@Ann10
@Ann10
class UserAnn10 {


}


