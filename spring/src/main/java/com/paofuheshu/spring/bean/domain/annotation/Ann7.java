package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 21:57
 * @des  @Target(ElementType.TYPE_PARAMETER)
 * 这个是1.8加上的，用来标注类型参数，类型参数一般在类后面声明或者方法上声明，
 */
@Target(value = { ElementType.TYPE_PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Ann7 {
    String value();
}

class UseAnn7<@Ann7("T0是在类上声明的一个泛型类型变量") T0, @Ann7("T1是在类上声明的一个泛型类型变量") T1> {

    public <@Ann7("T2是在方法上声明的泛型类型变量") T2> void m1() {

    }

    public static void main(String[] args) throws NoSuchMethodException{
        for (TypeVariable<Class<UseAnn7>> typeParameter : UseAnn7.class.getTypeParameters()) {
            print(typeParameter);
        }
        System.out.println("=====================");
        for (TypeVariable<Method> m1 : UseAnn7.class.getDeclaredMethod("m1").getTypeParameters()) {
            print(m1);
        }
    }

    public static void print(TypeVariable typeVariable) {
        System.out.println("类型变量名称：" + typeVariable.getName());
        Arrays.stream(typeVariable.getAnnotations()).forEach(System.out::println);
    }
}
