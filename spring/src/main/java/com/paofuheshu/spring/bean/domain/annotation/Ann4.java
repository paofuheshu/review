package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 21:49
 * @des  数组类型参数 注解Ann4定义了一个数组型的参数 name  name有多个值的时候，需要使用{}包含起来  如果name只有一个值，{}可以省略
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann4 {

    String[] name();
}

@Ann4(name = {"泡芙和树", "酒精泡芙"})
class UserAnn4 {

    @Ann4(name = "泡芙和树")
    public void test() {

    }
}
