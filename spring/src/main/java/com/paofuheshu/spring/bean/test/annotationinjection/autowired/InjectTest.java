package com.paofuheshu.spring.bean.test.annotationinjection.autowired;

import com.paofuheshu.spring.bean.domain.annotationinjection.test1.MainConfig1;
import com.paofuheshu.spring.bean.domain.annotationinjection.test10.MainConfig10;
import com.paofuheshu.spring.bean.domain.annotationinjection.test11.MainConfig11;
import com.paofuheshu.spring.bean.domain.annotationinjection.test12.MainConfig12;
import com.paofuheshu.spring.bean.domain.annotationinjection.test13.MainConfig13;
import com.paofuheshu.spring.bean.domain.annotationinjection.test14.MainConfig14;
import com.paofuheshu.spring.bean.domain.annotationinjection.test15.MainConfig15;
import com.paofuheshu.spring.bean.domain.annotationinjection.test18.MainConfig18;
import com.paofuheshu.spring.bean.domain.annotationinjection.test18.OrderService;
import com.paofuheshu.spring.bean.domain.annotationinjection.test18.UserService;
import com.paofuheshu.spring.bean.domain.annotationinjection.test2.MainConfig2;
import com.paofuheshu.spring.bean.domain.annotationinjection.test3.MainConfig3;
import com.paofuheshu.spring.bean.domain.annotationinjection.test4.MainConfig4;
import com.paofuheshu.spring.bean.domain.annotationinjection.test5.MainConfig5;
import com.paofuheshu.spring.bean.domain.annotationinjection.test6.MainConfig6;
import com.paofuheshu.spring.bean.domain.annotationinjection.test7.MainConfig7;
import com.paofuheshu.spring.bean.domain.annotationinjection.test8.MainConfig8;
import com.paofuheshu.spring.bean.domain.annotationinjection.test9.MainConfig9;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/20 19:04
 * @des
 */
public class InjectTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
//        test11();
//        test12();
//        test13();
//        test14();
//        test15();
//        test17();
        test18();
    }

    /**
     * 注解@Autowired标注在构造器上，通过构造器注入依赖对象
     * 输出结果分析：
     * mainConfig1->com.paofuheshu.spring.bean.domain.annotationinjection.test1.MainConfig1@5d7148e2
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test1.Service1@25fb8912
     * service2->Service2{service1=null}
     * 此时输出中可以看出调用了Service2的无参构造器，service2中的service1为null
     * 如果将@Autowired注解加在有参构造器上
     * 再看输出结果：
     * mainConfig1->com.paofuheshu.spring.bean.domain.annotationinjection.test1.MainConfig1@71a8adcf
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test1.Service1@27462a88
     * service2->Service2{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test1.Service1@27462a88}
     * 此时通过输出结果可以看出Service2有参构造器被调用了，service2中的service1有值了。
     */
    public static void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig1.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 注解@Autowired标注在方法上，通过方法注入依赖的对象
     * 将@Autowired注解标注在injectService1方法上  此时可以成功注入service1对象
     * 输出结果：
     * mainConfig2->com.paofuheshu.spring.bean.domain.annotationinjection.test2.MainConfig2@a38c7fe
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test2.Service1@6fdbe764
     * service2->Service2{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test2.Service1@6fdbe764}
     * 取消掉注解再查看输出：
     * mainConfig2->com.paofuheshu.spring.bean.domain.annotationinjection.test2.MainConfig2@5d7148e2
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test2.Service1@25fb8912
     * service2->Service2{service1=null}
     *
     */
    public static void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 注解@Autowired标注在setter方法上，通过setter方法注入
     * 将@Autowired注解标注在setter方法上  此时可以成功注入service1对象
     * 输出结果：
     * mainConfig3->com.paofuheshu.spring.bean.domain.annotationinjection.test3.MainConfig3@6fdbe764
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test3.Service1@51c668e3
     * service2->Service2{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test3.Service1@51c668e3}
     * 取消注解之后输出：
     * mainConfig3->com.paofuheshu.spring.bean.domain.annotationinjection.test3.MainConfig3@5d7148e2
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test3.Service1@25fb8912
     * service2->Service2{service1=null}
     */
    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 注解@Autowired标注在方法参数上
     * 此时在test4.Service2的injectService1方法上加了注解@Autowired
     * 表示会将这个参数作为注入方法，这个方法有2个参数，spring查找这2个参数对应的bean，然后注入
     * 但此时第一个参数对应的bean是存在的，第二个是一个String类型的，我们并没有定义String类型bean
     * 所以运行会报错，输出结果如下：
     * org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'service2':
     * Unsatisfied dependency expressed through method 'injectService1' parameter 1;
     * nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
     * No qualifying bean of type 'java.lang.String' available:
     * expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
     * 此时可以这么做：
     * 多个参数的时候，方法上面的@Autowire默认对方法中所有参数起效，如果我们想对某个参数进行特定
     * 的配置，可以在参数上加上@Autowired，这个配置会覆盖方法上面的@Autowired配置
     * 在第二个参数上面加上@Autowired，设置required为false：表示这个bean不是强制注入的，能找到就
     * 注入，找不到就注入一个null对象，调整一下代码，再次运行看输出结果：
     * com.paofuheshu.spring.bean.domain.annotationinjection.test4.Service2.injectService1(),
     * {service1=com.paofuheshu.spring.bean.domain.annotationinjection.test4.Service1@12aba8be,name=null}
     */
    public static void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig4.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 注解@Autowired用在字段上
     * 此时在test5.Service3中的两个字段都标注了@Autowired spring会去容器中按照类型查找这2种类型的bean，然后设置给这2个属性。
     * 输出结果：
     * mainConfig5->com.paofuheshu.spring.bean.domain.annotationinjection.test5.MainConfig5@3e11f9e9
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test5.Service1@1de5f259
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test5.Service2@729d991e
     * service3->Service3{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test5.Service1@1de5f259,
     * service2=com.paofuheshu.spring.bean.domain.annotationinjection.test5.Service2@729d991e}
     */
    public static void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig5.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 注解@Autowire标注字段，多个候选者的时候，按字段名称注入
     * 在test6.Service3中的service1字段上使用了@Autowried注解
     * 需要注入类型为IService类型的bean，但满足这种类型的有2个：service1和service2
     * 此时按照候选者查找过程，最后会注入和字段名称一样的bean，即：service1
     * 输出结果：
     * mainConfig6->com.paofuheshu.spring.bean.domain.annotationinjection.test6.MainConfig6@659499f1
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test6.Service1@51e69659
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test6.Service2@47e2e487
     * service3->Service3{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test6.Service1@51e69659}
     * 如果将service1字段名称改成service2
     * 此时根据名称查找会注入service2
     * 输出结果：
     * mainConfig6->com.paofuheshu.spring.bean.domain.annotationinjection.test6.MainConfig6@659499f1
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test6.Service1@51e69659
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test6.Service2@47e2e487
     * service3->Service3{service2=com.paofuheshu.spring.bean.domain.annotationinjection.test6.Service2@47e2e487}
     * 如果修改成service0 此时会报错找不到bean  输出：
     *  Error creating bean with name 'service0': Unsatisfied dependency expressed through field 'service0';
     *  nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     *  No qualifying bean of type 'com.paofuheshu.spring.bean.domain.annotationinjection.test6.IService' available:
     *  expected single matching bean but found 2: service1,service2
     */
    public static void test6() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig6.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 将指定类型的所有bean，注入到Collection、Map中
     * 注入到Collection中
     * 被注入的类型为Collection类型或者Collection子接口类型，注意必须是接口类型，如：
     * Collection<IService> List<IService> Set<IService>
     * 会在容器中找到所有IService类型的bean，放到这个集合中。
     *
     * 注入到Map中
     * 被注入的类型为Map类型或者Map子接口类型，注意必须是接口类型，如：
     * Map<String,IService>
     * 会在容器中找到所有IService类型的bean，放到这个Map中，key为bean的名称，value为bean对象。
     * 输出结果：
     * mainConfig7->com.paofuheshu.spring.bean.domain.annotationinjection.test7.MainConfig7@d86a6f
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test7.Service1@e15b7e8
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test7.Service2@1b2abca6
     * service3->Service3{services=[com.paofuheshu.spring.bean.domain.annotationinjection.test7.Service1@e15b7e8,
     * com.paofuheshu.spring.bean.domain.annotationinjection.test7.Service2@1b2abca6],
     * serviceMap={service1=com.paofuheshu.spring.bean.domain.annotationinjection.test7.Service1@e15b7e8,
     * service2=com.paofuheshu.spring.bean.domain.annotationinjection.test7.Service2@1b2abca6}}
     */
    public static void test7() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig7.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 将@Resource标注在字段上
     * 输出结果：
     * mainConfig1->com.paofuheshu.spring.bean.domain.annotationinjection.autowired.test8.MainConfig1@7c711375
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.autowired.test8.Service1@57cf54e1
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.autowired.test8.Service2@5b03b9fe
     * service3->Service3{service1=com.paofuheshu.spring.bean.domain.annotationinjection.autowired.test8.Service1@57cf54e1}
     * 同样@Resource可以用在方法上，也可以将所有类型的bean注入到Collection、Map中
     */
    public static void test8() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig8.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 将@Qualifier用在类上
     * 用在类上，可以理解为给通过@Qulifier给这个bean打了一个标签。
     * 输出结果：
     * mainConfig9->com.paofuheshu.spring.bean.domain.annotationinjection.test9.MainConfig9@3527942a
     * injectService->InjectService{
     * serviceMap1={service1=com.paofuheshu.spring.bean.domain.annotationinjection.test9.Service1@942a29c,
     * service2=com.paofuheshu.spring.bean.domain.annotationinjection.test9.Service2@1ed6388a},
     * serviceMap2={service3=com.paofuheshu.spring.bean.domain.annotationinjection.test9.Service3@5a45133e}}
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test9.Service1@942a29c
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test9.Service2@1ed6388a
     * service3->com.paofuheshu.spring.bean.domain.annotationinjection.test9.Service3@5a45133e
     * 结果分析:
     * serviceMap1注入了@Qulifier的value为tag1的所有IService类型的bean
     * serviceMap2注入了@Qulifier的value为tag2的所有IService类型的bean
     * 实现了bean分组的效果。
     */
    public static void test9() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig9.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 使用@Autowired结合@Qualifier指定注入的bean
     * 被注入的类型有多个的时候，可以使用@Qulifier来指定需要注入那个bean，将@Qulifier的value设置为需要注入bean的名称
     * 输出结果：
     * mainConfig10->com.paofuheshu.spring.bean.domain.annotationinjection.test10.MainConfig10@659499f1
     * injectService->InjectService{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test10.Service2@51e69659}
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test10.Service1@47e2e487
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test10.Service2@51e69659
     */
    public static void test10() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig10.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 将@Qualifier用在方法参数上
     * 输出结果：
     * mainConfig11->com.paofuheshu.spring.bean.domain.annotationinjection.test11.MainConfig11@6c1a5b54
     * injectService->InjectService{s1=com.paofuheshu.spring.bean.domain.annotationinjection.test11.Service2@1c7696c6, s2=com.paofuheshu.spring.bean.domain.annotationinjection.test11.Service1@60099951}
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test11.Service1@60099951
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test11.Service2@1c7696c6
     */
    public static void test11() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig11.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 将@Qualifier用在setter方法上
     * 输出结果：
     * mainConfig12->com.paofuheshu.spring.bean.domain.annotationinjection.test12.MainConfig12@1ffaf86
     * injectService->InjectService{s1=com.paofuheshu.spring.bean.domain.annotationinjection.test12.Service2@6574a52c,
     * s2=com.paofuheshu.spring.bean.domain.annotationinjection.test12.Service1@6c1a5b54}
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test12.Service1@6c1a5b54
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test12.Service2@6574a52c
     */
    public static void test12() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig12.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 将@Primary用在类上
     * 输出结果：
     * mainConfig13->com.paofuheshu.spring.bean.domain.annotationinjection.test13.MainConfig13@47d9a273
     * injectService->InjectService{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test13.Service2@4b8ee4de}
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test13.Service1@27f981c6
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test13.Service2@4b8ee4de
     * 此时如果将test13.InjectService类中的字段属性定义为service  再加上了@Primary的时候依然会有一个主要的候选者  如果不加的话 会报错找不到这个bean
     */
    public static void test13() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig13.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 将@Primary用在方法上，结合@Bean使用
     * 输出结果：
     * mainConfig14->com.paofuheshu.spring.bean.domain.annotationinjection.test14.MainConfig14@2805d709
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test14.Service1@3ee37e5a
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test14.Service2@2ea41516
     * injectService->InjectService{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test14.Service2@2ea41516}
     */
    public static void test14() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig14.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 注解@Bean定义bean时注入依赖的第一种方式：硬编码方式
     * 输出结果：
     * mainConfig15->com.paofuheshu.spring.bean.domain.annotationinjection.test15.MainConfig15$$EnhancerBySpringCGLIB$$288ce8b6@33ecda92
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test15.Service1@14fc5f04
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test15.Service2@6e2829c7
     * service3->Service3{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test15.Service1@14fc5f04,
     * service2=com.paofuheshu.spring.bean.domain.annotationinjection.test15.Service2@6e2829c7}
     */
    public static void test15() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig15.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 注解@Bean定义bean时注入依赖的第二种方式：@Autowired、@Resource的方式
     */
    public static void test16() {

    }

    /**
     * 注解@Bean定义bean时注入依赖的第三种方式：@Bean标注的方法使用参数来进行注入
     * 输出结果：
     * mainConfig15->com.paofuheshu.spring.bean.domain.annotationinjection.test15.MainConfig15$$EnhancerBySpringCGLIB$$288ce8b6@aba625
     * service1->com.paofuheshu.spring.bean.domain.annotationinjection.test15.Service1@97e93f1
     * service2->com.paofuheshu.spring.bean.domain.annotationinjection.test15.Service2@5a5a729f
     * service4->Service3{service1=com.paofuheshu.spring.bean.domain.annotationinjection.test15.Service1@97e93f1,
     * service2=com.paofuheshu.spring.bean.domain.annotationinjection.test15.Service2@5a5a729f}
     */
    public static void test17() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig15.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanName, context.getBean(beanName));
        }
    }

    /**
     * 泛型注入
     * 输出结果：
     * com.paofuheshu.spring.bean.domain.annotationinjection.test18.UserDao@53aac487
     * com.paofuheshu.spring.bean.domain.annotationinjection.test18.OrderDao@52b1beb6
     */
    public static void test18() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig18.class);
        System.out.println(context.getBean(UserService.class).getDao());
        System.out.println(context.getBean(OrderService.class).getDao());
    }

    /**
     * 总结：
     *
     */
}
