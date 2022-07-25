package com.paofuheshu.spring.bean.test.configuration;

import com.paofuheshu.spring.bean.domain.configuration.ConfigureBean2;
import com.paofuheshu.spring.bean.domain.configuration.ConfigureBean3;
import com.paofuheshu.spring.bean.domain.configuration.ConfigureUserBean;
import com.paofuheshu.spring.bean.domain.configuration.ConfigureUserBean1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 18:23
 * @des
 */
public class ConfigurationUserTest {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
//        test4();
    }

    /**
     * 输出结果
     * bean名称:configureUserBean,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.ConfigureUserBean$$EnhancerBySpringCGLIB$$559b353e@4b14c583
     * bean名称:user1,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.User@65466a6a
     * bean名称:user2Bean,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.User@4ddced80
     * bean名称:user3Bean,别名:[user3BeanAlias2, user3BeanAlias1],bean对象:com.paofuheshu.spring.bean.domain.configuration.User@1534f01b
     */
    public static void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigureUserBean.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            String[] aliases = context.getAliases(beanDefinitionName);
            System.out.printf("bean名称:%s,别名:%s,bean对象:%s%n", beanDefinitionName, Arrays.asList(aliases), context.getBean(beanDefinitionName));
        }
    }

    /**
     * 去除掉注解@Configuration
     * 输出结果：
     * bean名称:configureUserBean1,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.ConfigureUserBean1@22e357dc
     * bean名称:user1,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.User@49912c99
     * bean名称:user2Bean,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.User@10163d6
     * bean名称:user3Bean,别名:[user3BeanAlias2, user3BeanAlias1],bean对象:com.paofuheshu.spring.bean.domain.configuration.User@2dde1bff
     *
     * test1和test2的区别在于test2的配置类上没有加注解@Configuration 对比结果发现
     * 1. 对比最后3行，可以看出：有没有@Configuration注解，@Bean都会起效，都会将@Bean修饰的
     * 方法作为bean注册到容器中
     * 2. 两个内容的第一行有点不一样，被@Configuration修饰的bean最后输出的时候带有
     * EnhancerBySpringCGLIB 的字样，而没有@Configuration注解的bean没有Cglib的字样；有
     * EnhancerBySpringCGLIB 字样的说明这个bean被cglib处理过的，变成了一个代理对象。
     */
    public static void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigureUserBean1.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            String[] aliases = context.getAliases(beanDefinitionName);
            System.out.printf("bean名称:%s,别名:%s,bean对象:%s%n", beanDefinitionName, Arrays.asList(aliases), context.getBean(beanDefinitionName));
        }
    }

    /**
     * 在bean之间是有依赖关系的情况下 存在@Configuration注解
     * 输出结果
     * 18:36:59.088 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'serviceA'
     * 调用serviceA()方法
     * 18:36:59.091 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'serviceB1'
     * 调用serviceB1()方法
     * 18:36:59.091 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'serviceB2'
     * 调用serviceB2()方法
     * bean名称:configureBean2,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.ConfigureBean2$$EnhancerBySpringCGLIB$$cc9c7cb6@20d28811
     * bean名称:serviceA,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.ServiceA@3967e60c
     * bean名称:serviceB1,别名:[],bean对象:ServiceB{serviceA=com.paofuheshu.spring.bean.domain.configuration.ServiceA@3967e60c}
     * bean名称:serviceB2,别名:[],bean对象:ServiceB{serviceA=com.paofuheshu.spring.bean.domain.configuration.ServiceA@3967e60c}
     *
     * 分析结果
     * 从输出中可以看出
     * 1. 前几行输出可以看出，被@Bean修饰的方法都只被调用了一次，这个很关键
     * 2. 最后三行中可以看出都是同一个ServiceA对象，都是 ServiceA@3967e60c 这个实例
     * 这是为什么？
     * 被@Configuration修饰的类，spring容器中会通过cglib给这个类创建一个代理，代理会拦截所有被@Bean 修饰的方法，
     * 默认情况（bean为单例）下确保这些方法只被调用一次，从而确保这些bean是同一个bean，即单例的。
     */
    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigureBean2.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            String[] aliases = context.getAliases(beanDefinitionName);
            System.out.printf("bean名称:%s,别名:%s,bean对象:%s%n", beanDefinitionName, Arrays.asList(aliases), context.getBean(beanDefinitionName));
        }
    }

    /**
     * 对比方法test3  此时类上不加@Configuration注解
     * 输出结果：
     * 18:41:06.000 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'serviceA'
     * 调用serviceA()方法
     * 18:41:06.000 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'serviceB1'
     * 调用serviceB1()方法
     * 调用serviceA()方法
     * 18:41:06.000 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'serviceB2'
     * 调用serviceB2()方法
     * 调用serviceA()方法
     * bean名称:configureBean3,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.ConfigureBean3@702b8b12
     * bean名称:serviceA,别名:[],bean对象:com.paofuheshu.spring.bean.domain.configuration.ServiceA@22e357dc
     * bean名称:serviceB1,别名:[],bean对象:ServiceB{serviceA=com.paofuheshu.spring.bean.domain.configuration.ServiceA@49912c99}
     * bean名称:serviceB2,别名:[],bean对象:ServiceB{serviceA=com.paofuheshu.spring.bean.domain.configuration.ServiceA@10163d6}
     *
     * 分析结果
     * 1. serviceA()方法被调用了3次
     * 2. configBean2这个bean没有代理效果了
     * 3. 最后3行可以看出，几个ServiceA对象都是不一样的
     */
    public static void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigureBean3.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            String[] aliases = context.getAliases(beanDefinitionName);
            System.out.printf("bean名称:%s,别名:%s,bean对象:%s%n", beanDefinitionName, Arrays.asList(aliases), context.getBean(beanDefinitionName));
        }
    }
}
