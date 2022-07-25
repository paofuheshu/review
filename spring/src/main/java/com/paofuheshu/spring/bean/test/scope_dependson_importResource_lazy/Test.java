package com.paofuheshu.spring.bean.test.scope_dependson_importResource_lazy;

import com.paofuheshu.spring.bean.domain.scope_dependson_importResource_lazy.test2.MainConfig2;
import com.paofuheshu.spring.bean.domain.scope_dependson_importResource_lazy.test2.MainConfig3;
import com.paofuheshu.spring.bean.domain.scope_dependson_importResource_lazy.test2.Service1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 13:58
 * @des
 */
public class Test {

    public static void main(String[] args) {
//        test3();
        test4();
    }

    /**
     * scpoe注解和@Compontent一起使用在类上
     */
    public static void test1() {

    }

    /**
     * scope注解和@Bean一起标注在方法上
     */
    public static void test2() {

    }

    /**
     * dependsOn注解和@Compontent一起使用在类上
     * 输出结果：
     * create Service2
     * create Service3
     * create Service1
     * com.paofuheshu.spring.bean.domain.scope_dependson_importResource_lazy.test2.Service1@2d2e5f00
     */
    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println(context.getBean(Service1.class));
    }

    /**
     * dependsOn注解和@Bean一起标注在方法上
     * 输出结果：
     * create Service2
     * create Service3
     * create Service1
     * com.paofuheshu.spring.bean.domain.scope_dependson_importResource_lazy.test2.Service1@58134517
     */
    public static void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        System.out.println(context.getBean(Service1.class));
    }
}
