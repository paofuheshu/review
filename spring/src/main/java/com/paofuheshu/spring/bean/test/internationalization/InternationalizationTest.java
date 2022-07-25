package com.paofuheshu.spring.bean.test.internationalization;

import com.paofuheshu.spring.bean.domain.internationalization.MainConfig1;
import com.paofuheshu.spring.bean.domain.internationalization.MainConfig2;
import com.paofuheshu.spring.bean.domain.internationalization.MainConfig3;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 21:35
 * @des
 */
public class InternationalizationTest {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
//        test4();
    }

    public static void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig1.class);
        context.refresh();
        // 未指定Locale，此时系统会取默认的locale对象，本地默认的值中文【中国】，即：zh_CN
        System.out.println(context.getMessage("name", null, null));
        System.out.println(context.getMessage("name", null, Locale.CHINA));
        System.out.println(context.getMessage("name", null, Locale.UK));
    }

    public static void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig1.class);
        context.refresh();
        // 未指定Locale，此时系统会取默认的locale对象，本地默认的值中文【中国】，即：zh_CN
        System.out.println(context.getMessage("personal_introduction", new String[] {"paofuheshu", "jiujingpaofu"}, null));
        System.out.println(context.getMessage("personal_introduction", new String[] {"paofuheshu", "jiujingpaofu"}, Locale.CHINA));
        System.out.println(context.getMessage("personal_introduction", new String[] {"spring", "java"}, Locale.UK));
    }

    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig2.class);
        context.refresh();
        for (int i = 0; i < 20; i++) {
            System.out.println(context.getMessage("address", null, Locale.CHINA));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig3.class);
        context.refresh();
        System.out.println(context.getMessage("desc", null, Locale.CHINA));
        System.out.println(context.getMessage("desc", null, Locale.UK));
    }
}
