package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 21:54
 * @des
 * 下面演示了自定义注解在在类、字段、构造器、方法参数、方法、本地变量上的使用，@Ann6注
 * 解有个 elementType 参数，通过这个参数的值来告诉大家对应@Target中的那个值来限制使
 * 用目标的，注意一下下面每个 elementType 的值。
 */
@Target(value = {
        ElementType.TYPE,
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.PARAMETER,
        ElementType.CONSTRUCTOR,
        ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann6 {
    String value();

    ElementType elementType();
}

@Ann6(value = "我用在类上", elementType = ElementType.TYPE)
class UseAnn6 {

    @Ann6(value = "我用在字段上", elementType = ElementType.FIELD)
    private String a;

    @Ann6(value = "我用在构造方法上", elementType = ElementType.CONSTRUCTOR)
    public UseAnn6(@Ann6(value = "我用在方法参数上", elementType = ElementType.PARAMETER) String a) {
        this.a = a;
    }

    @Ann6(value = "我用在了普通方法上面", elementType = ElementType.METHOD)
    public void m1() {
        @Ann6(value = "我用在了本地变量上", elementType = ElementType.LOCAL_VARIABLE)
        String a;
    }
}
