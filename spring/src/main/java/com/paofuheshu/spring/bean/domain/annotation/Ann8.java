package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.*;
import java.util.Map;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 22:03
 * @des
 * 范围 @Target(ElementType.TYPE_USE) 这个是1.8加上的，能用在任何类型名称上
 */
@Target({ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann8 {
    String value();
}

@Ann8("用在类上")
class UseAnn8<@Ann8("用在变量v1上") v1, @Ann8("用在变量v2上") v2> {

    private Map<@Ann8("用在了泛型类型上") String, Integer> map;

    public <@Ann8("用在了参数上") T> String m1(String name) {
        return null;
    }

}
