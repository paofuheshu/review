package com.paofuheshu.spring.bean.test.di;

import com.paofuheshu.spring.bean.domain.di.DiAutowireByTypeExtend;
import com.paofuheshu.spring.utils.IocUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/7 17:54
 * @des 测试自动注入
 * 自动注入是采用约定大约配置的方式来实现的，程序和spring容器之间约定好，遵守某一种都认同的规则，来实现自动注入。
 * xml中可以在bean元素中通过autowire属性来设置自动注入的方式：
 * <bean id="" class="" autowire="byType|byName|constructor|default" />
 * byteName：按照名称进行注入
 * byType：按类型进行注入
 * constructor：按照构造方法进行注入
 * default：默认注入方式
 */
public class DiAutomaticTest {

    public static void main(String[] args) {
//        testMethod();
//        diAutowireByName();
//        diAutowireByType();
//        diAutowireByTypeExtend();
//        diAutowireByConstructor();
        diAutowireByDefault();
    }

    /**
     * 测试Class.isAssignableFrom方法
     * isAssignableFrom是Class类中的一个方法，用来判断c2和c1的class类对象是否相等，或者c2是否是c1的子类。
     * 用法：c1.isAssignableFrom(c2)
     */
    public static void testMethod() {
        String a = "1";
        String b = "1";
        String c = "2";
        System.out.println(Object.class.isAssignableFrom(Integer.class));
        System.out.println(Object.class.isAssignableFrom(int.class));
        System.out.println(a.getClass().isAssignableFrom(b.getClass()));
        System.out.println(a.getClass().isAssignableFrom(c.getClass()));
        System.out.println(a.getClass());
        System.out.println(c.getClass());
    }

    /**
     * 按照名称进行注入
     * 按名称进行注入的时候，要求名称和set属性的名称必须同名，相对于硬编码的方式注入，确实节省了不
     * 少代码。
     */
    public static void diAutowireByName() {
        String src = "di/diAutowireByName.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        System.out.println(context.getBean("diAutowireByName1"));
        System.out.println(context.getBean("diAutowireByName2"));
    }

    /**
     * 按照类型进行注入
     * 相对于手动注入，节省了不少代码，新增或者删除属性，只需要增减对应的set方法就可以了，更容易扩
     * 展了。
     */
    public static void diAutowireByType() {
        String src = "di/diAutowireByType.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        System.out.println(context.getBean("diAutowireByType1"));
    }

    /**
     * 按照类型注入集合
     */
    public static void diAutowireByTypeExtend() {
        String src = "di/diAutowireByTypeExtend.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        DiAutowireByTypeExtend diAutowireByTypeExtend = context.getBean(DiAutowireByTypeExtend.class);
        System.out.println("serviceList：" + diAutowireByTypeExtend.getServiceList());
        System.out.println("baseServiceList：" + diAutowireByTypeExtend.getBaseServiceList());
        System.out.println("service1Map：" + diAutowireByTypeExtend.getService1Map());
        System.out.println("baseServiceMap：" + diAutowireByTypeExtend.getBaseServiceMap());
    }

    /**
     * 按照构造函数进行自动注入
     * 构造函数匹配采用贪婪匹配，多个构造函数结合容器找到一个合适的构造函数，最匹配的就是第一
     * 个有参构造函数，而第二个有参构造函数的第二个参数在spring容器中找不到匹配的bean对象，
     * 所以被跳过了。
     */
    public static void diAutowireByConstructor() {
        String src = "di/diAutowireByConstructor.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        System.out.println(context.getBean("diAutowireByConstructor"));
    }

    public static void diAutowireByDefault() {
        String src = "di/diAutowireByDefault.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        System.out.println(context.getBean("diAutowireByDefault1"));
        System.out.println(context.getBean("diAutowireByDefault2"));
    }
}
