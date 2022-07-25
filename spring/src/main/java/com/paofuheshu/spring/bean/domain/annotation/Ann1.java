package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 21:40
 * @des  此处指定了注解Ann1的使用范围为type 可以使用在类、接口、枚举、注解上面，所以可以定义在类UserAnn1上  如果修改成METHOD则会报错
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann1 {
}

@Ann1
class UserAnn1 {

}
