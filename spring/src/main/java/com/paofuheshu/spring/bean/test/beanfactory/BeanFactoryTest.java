package com.paofuheshu.spring.bean.test.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/4 20:26
 * @des
 */
@RestController
public class BeanFactoryTest {
    /**
     * 按bean的id或者别名查找容器中的bean
     * Object getBean(String name) throws BeansException
     * 这个是一个泛型方法，按照bean的id或者别名查找指定类型的bean，返回指定类型的bean对象
     * <T> T getBean(String name, Class<T> requiredType) throws BeansException;
     * 返回容器中指定类型的bean对象
     * <T> T getBean(Class<T> requiredType) throws BeansException;
     * 获取指定类型bean对象的获取器，这个方法比较特别
     * <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType);
     */

    @Resource
    private BeanFactory beanFactory;

    @Resource
    private ApplicationContext applicationContext;

    @GetMapping("/test1")
    public void test1() {
        Object bean2 = beanFactory.getBean("bean2");
    }

    @GetMapping("/bean")
    public void bean() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    /**
     * 测试scope注解
     * 如果bean1上@Scope("singleton")表示单例  此时获取的bean1和bean2是相同的 spring默认为单例
     * 单例bean是整个应用共享的，所以需要考虑到线程安全问题
     * 如果bean1上@Scope("prototype")表示多例  此时获取的bean1和bean2是不同的
     * 多例bean每次获取的时候都会重新创建，如果这个bean比较复杂，创建时间比较长，会影响系统的性能，这个地方需要注意
     * 如果bean1上@Scope("request")表示一个bean的作用域为request  此时在同一次的http请求中获取的bean1和bean2是相同的，
     * 下一次的http请求中获取到的bean1和bean2也是相同的  但是两次http请求中的bean不相同
     * 如果bean1上@Scope("session")表示一个bean的作用域为session  此时在同一个session中获取到的bean是相同的
     * 比如在谷歌浏览器中获取到的bean相同  但是在火狐浏览器下访问获取到的bean不同
     * 或者将浏览器中的session清除之后再访问 bean也会发生变化
     * 如果bean1上@Scope("application")表示一个bean的作用域为全局web应用级别的
     * 一个web应用程序对应一个bean实例，通常情
     * 况下和singleton效果类似的，不过也有不一样的地方，singleton是每个spring容器中只有一个bean实
     * 例，一般我们的程序只有一个spring容器，但是，一个应用程序中可以创建多个spring容器，不同的容
     * 器中可以存在同名的bean，但是scope=application的时候，不管应用中有多少个spring容器，这个应用中
     * 同名的bean只有一个。
     */
    @GetMapping("/test2")
    public void test2() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames));
        Object bean1 = beanFactory.getBean("bean1");
        Object bean2 = beanFactory.getBean("bean1");
        System.out.println(bean1);
        System.out.println(bean2);
        System.out.println(bean1.equals(bean2));
        String[] beanDefinitionNames1 = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(beanDefinitionNames1));
    }

    /**
     * 此时同一个线程获取到的bean是相同的 不同线程获取到的bean是不同的
     * @throws InterruptedException
     */
    @GetMapping("/test3")
    public void test3() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread() + "," + applicationContext.getBean("threadBean"));
            }).start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
