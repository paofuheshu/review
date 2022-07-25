package com.paofuheshu.spring.bean.test.annotation;

import com.paofuheshu.spring.bean.domain.annotation.*;
import lombok.SneakyThrows;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 18:19
 * @des
 */
public class AnnotationTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();;
//        test6();
//        test7();
//        test8();
//        test9();
        test10();
    }

    /**
     * 解析类上的注解
     * 返回此元素上存在的所有注解，包括从父类继承的
     */
    public static void test1() {
        for (Annotation annotation : UseAnn9.class.getAnnotations()) {
            System.out.println(annotation);
        }
    }

    /**
     * 解析类上的类型变量
     */
    public static void test2() {
        TypeVariable<Class<UseAnn9>>[] typeParameters = UseAnn9.class.getTypeParameters();
        for (TypeVariable<Class<UseAnn9>> typeParameter : typeParameters) {
            System.out.println(typeParameter.getName() + "变量类型注解信息：");
            for (Annotation annotation : typeParameter.getAnnotations()) {
                System.out.println(annotation);
            }
            System.out.println("=============");
        }
    }

    /**
     * 解析字段name上的注解
     */
    public static void test3() {
        try {
            Field name = UseAnn9.class.getDeclaredField("name");
            Annotation[] annotations = name.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析泛型字段map上的注解
     */
    public static void test4() {
        try {
            Field field = UseAnn9.class.getDeclaredField("map");
            Type genericType = field.getGenericType();
            Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
            AnnotatedType annotatedType = field.getAnnotatedType();
            AnnotatedType[] annotatedActualTypeArguments = ((AnnotatedParameterizedType) annotatedType).getAnnotatedActualTypeArguments();
            int i = 0;
            for (AnnotatedType annotatedActualTypeArgument : annotatedActualTypeArguments) {
                Type actualTypeArgument = actualTypeArguments[i++];
                System.out.println(actualTypeArgument.getTypeName() + "类型上加的注解如下");
                Annotation[] annotations = annotatedActualTypeArgument.getAnnotations();
                for (Annotation annotation : annotations) {
                    System.out.println(annotation);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析构造函数上的注解
     */
    public static void test5() {

        for (Constructor<?> constructor1 : UseAnn9.class.getConstructors()) {
            System.out.println("构造方法：" + constructor1.getName());
            for (Annotation annotation : constructor1.getAnnotations()) {
                System.out.println(annotation);
            }
        }
    }

    /**
     * 解析m1方法上的注解
     */
    public static void test6() {
        try {
            Method m1 = UseAnn9.class.getDeclaredMethod("m1", String.class);
            for (Annotation annotation : m1.getAnnotations()) {
                System.out.println(annotation);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析m1方法参数注解
     */
    public static void test7() {
        try {
            Method m1 = UseAnn9.class.getDeclaredMethod("m1", String.class);
            for (Parameter parameter : m1.getParameters()) {
                System.out.printf("参数%s上的注解如下：%n", parameter.getName());
                for (Annotation annotation : parameter.getAnnotations()) {
                    System.out.println(annotation);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注解@Inherit：实现类之间的注解继承  此注解是专门用来修饰注解的
     * 源码中标记了：@Target(ElementType.ANNOTATION_TYPE)
     * 作用：让子类可以继承父类中被@Inherited修饰的注解，注意是继承父类中的，如果接口中的注解也使
     * 用@Inherited修饰了，那么接口的实现类是无法继承这个注解的
     * 具体案例见InheritAnnotationTest类
     */
    public static void test8() {

    }

    /**
     * 注解@Repeatable重复使用注解此注解是专门用来修饰注解的
     * 源码中标记了：@Target(ElementType.ANNOTATION_TYPE)
     * 具体案例见annotation包下方的Ann10 Ann11 Ann12注解
     */
    @SneakyThrows
    public static void test9() {
        for (Annotation annotation : UseAnn12.class.getAnnotations()) {
            System.out.println(annotation);
        }
        System.out.println("==========");
        Field name = UseAnn12.class.getDeclaredField("name");
        for (Annotation annotation : name.getDeclaredAnnotations()) {
            System.out.println(annotation);
        }
    }

    /**
     * 此时有个问题：此时如果想在 UseA1AndB1 上给B1上的A1注解设置值是没有办法的，注解定义无法继承导致的
     * 如果注解定义上面能够继承，那用起来会爽很多，spring通过@Aliasfor方法解决了这个问题。
     * 在B1注解中使用@Aliasfor即可解决问题
     * 这个相当于给某个注解指定别名，即将B1注解中 A1Value 参数作为 A1 中 value 参数的别名，当给
     * B1的A1Value 设置值的时候，就相当于给 A1的value设置值 ，有个前提是@AliasFor注解的
     * annotation 参数指定的注解需要加载当前注解上面
     *
     * 还可以在同一个注解中使用@AliasFor  具体代码见annotation包下方的UseAnnForAliasFor
     *
     *
     * 如果@AliasFor中不指定value和attribute
     * 当@AliasFor中不指定value或者attribute的时候，自动将@AliasFor修饰的参数作为value和attribute的值，具体案例见annotation包下方的UseForAliasFor
     *
     */
    public static void test10() {
        // AnnotatedElementUtils是spring提供的一个查找注解的工具类
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseA1AndB1.class, B1.class));
        System.out.println(AnnotatedElementUtils.getMergedAnnotation(UseA1AndB1.class, A1.class));
    }
}
