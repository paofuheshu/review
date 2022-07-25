package com.paofuheshu.spring.bean.test.lazyinit;

import com.paofuheshu.spring.bean.domain.lazyinit.LazyInitBean;
import com.paofuheshu.spring.utils.IocUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 18:18
 * @des
 */
public class LazyBeanTest {

    public static void main(String[] args) {
//        actualTimeBean();
//        lazyInitBean();
        actualTimeDependencyLazyBean();
    }

    /**
     * 实时初始化bean
     */
    public static void actualTimeBean() {
        System.out.println("spring容器启动中...");
        ClassPathXmlApplicationContext context = IocUtil.context("lazyinit/actualTimeBean.xml");
        System.out.println(context.getBean("actualTimeBean"));
        System.out.println("spring容器启动完毕...");
    }

    /**
     * 延迟初始化的bean
     * 从上面的方法我们可以看出，实时初始化的bean都会在容器启动过程中创建好，如果程序中定义的bean非常
     * 多，并且有些bean创建的过程中比较耗时的时候，会导致系统消耗的资源比较多，并且会让整个启动时
     * 间比较长，使用spring开发的系统比较大的时候，整个系统启动耗时是
     * 比较长的，基本上多数时间都是在创建和组装bean。
     * spring对这些问题也提供了解决方案：bean延迟初始化。
     * 所谓延迟初始化，就是和实时初始化刚好相反，延迟初始化的bean在容器启动过程中不会创建，而是需
     * 要使用的时候才会去创建，先说一下bean什么时候会被使用：
     * 1. 被其他bean作为依赖进行注入的时候，比如通过property元素的ref属性进行引用，
     * 通过构造器注入、通过set注入、通过自动注入，这些都会导致被依赖bean的创建
     * 2. 开发者自己写代码向容器中查找bean的时候，如调用容器的getBean方法获取bean。
     * 上面这2中情况会导致延迟初始化的bean被创建。
     */
    public static void lazyInitBean() {
        System.out.println("spring容器启动中...");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lazyinit/lazyInitBean.xml");
        System.out.println("spring容器启动完毕...");
        System.out.println("从容器中开始查找LazyInitBean");
        LazyInitBean lazyInitBean = context.getBean(LazyInitBean.class);
        System.out.println("LazyInitBean:" + lazyInitBean);
    }

    /**
     * 上面这种方式是我们主动从容器中获取bean的时候，延迟初始化的bean才被容器创建的，下面我们再
     * 来看一下当延迟初始化的bean被其他实时初始化的bean依赖的时候，是什么时候创建的。
     */
    public static void actualTimeDependencyLazyBean() {
        System.out.println("spring容器启动中...");
        ClassPathXmlApplicationContext context = IocUtil.context("lazyinit/actualTimeDependencyLazyBean.xml");
        System.out.println("spring容器启动完毕...");
    }
}
