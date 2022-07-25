package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 21:43
 * @des 此处定义的注解Ann2含有一个参数  且没有默认值  所以UserAnn2这个类在使用注解Ann2时必须要指定这个参数name的值，不然会报错
 * 或者在参数name处指定他的默认值  这样在使用时可以直接使用  不需要指定参数值也可以
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann2 {
//    String name();
    String name() default "泡芙和树";
}

//@Ann2(name = "sad")
@Ann2
class UserAnn2 {

}
