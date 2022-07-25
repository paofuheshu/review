package com.paofuheshu.spring.bean.domain.annotation;

import java.lang.annotation.*;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/14 21:26
 * @des
 */
public class InheritAnnotationTest {

    /**
     * 定义了一个注解A1，上面使用了@Inherited，表示这个具有继承功能
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface A1 {

    }

    /**
     * 定义了一个注解A2，上面使用了@Inherited，表示这个具有继承功能
     */
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface A2 {

    }

    /**
     * 定义接口I1，上面使用了@A1注解
     */
    @A1
    interface I1 {

    }

    /**
     * 定义了一个C1类，使用了A2注解
     */
    @A2
    static class C1 {

    }

    /**
     * C2继承了C1并且实现了I1接口
     */
    static class C2 extends C1 implements I1 {

    }

    /**
     * 从输出中可以看出类可以继承父类上被@Inherited修饰的注解，而不能继承接口上被@Inherited修饰
     * 的注解
     * @param args  args
     */
    public static void main(String[] args) {
        for (Annotation annotation : C2.class.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
