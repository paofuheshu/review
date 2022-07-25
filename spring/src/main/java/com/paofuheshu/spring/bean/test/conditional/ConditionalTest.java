package com.paofuheshu.spring.bean.test.conditional;

import com.paofuheshu.spring.bean.domain.conditional.test1.MainConfig1;
import com.paofuheshu.spring.bean.domain.conditional.test2.MainConfig2;
import com.paofuheshu.spring.bean.domain.conditional.test3.IService;
import com.paofuheshu.spring.bean.domain.conditional.test3.MainConfig3;
import com.paofuheshu.spring.bean.domain.conditional.test4.MainConfig4;
import com.paofuheshu.spring.bean.domain.conditional.test5.MainConfig5;
import com.paofuheshu.spring.bean.domain.conditional.test6.MainConfig6;
import com.paofuheshu.spring.bean.domain.conditional.test7.MainConfig7;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:51
 * @des
 */
public class ConditionalTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    /**
     * 阻止配置类的处理
     * 定义MainConfig1配置类  内部配置了一个String的bean：name
     * 定义MyCondition1实现了Condition接口  重写内部matches方法  返回false
     * 并在配置类MainConfig1上配置@Conditional(MyCondition1.class)注解
     * 此时MainConfig1配置类内部定义的bean不会被注册到spring容器中
     * 如果将@Conditional注解取消掉或者将MyCondition1重写的matches方法返回true 则内部bean就会注册成功
     */
    public static void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig1.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName + "-->" + context.getBean(beanDefinitionName));
        }
    }

    /**
     * 阻止bean的注册
     * 在MainConfig2配置类中定义了两个bean 分别为：name  address
     * 但在name的bean上增加了注解@Conditional(MyCondition1.class)
     * 此时根据MyCondition1重写的matches方法规则 返回为false时 name不会被注册
     */
    public static void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
//        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
//            System.out.println(beanDefinitionName + "-->" + context.getBean(beanDefinitionName));
//        }
        Map<String, String> beansOfType = context.getBeansOfType(String.class);
        beansOfType.forEach((beanName, bean) -> {
            System.out.println(beanName + "-->" + bean);
        });
    }

    /**
     * bean不存在的时候才注册
     * 需求
     * IService接口有两个实现类Service1和Service1，这两个类会放在2个配置类中通过@Bean的方式来注册
     * 到容器，此时我们想加个限制，只允许有一个IService类型的bean被注册到容器。
     * 可以在@Bean标注的2个方法上面加上条件限制，当容器中不存在IService类型的bean时，才将这个方
     * 法定义的bean注册到容器，下面来看代码实现。
     * 输出结果：
     * service1->com.paofuheshu.spring.bean.domain.conditional.test3.Service1@3de8f619
     * 可以看出容器中只有一个IService类型的bean。
     * 可以将@Bean标注的2个方法上面的@Conditional去掉，再运行会输出：
     * service1->com.paofuheshu.spring.bean.domain.conditional.test3.Service1@4e5ed836
     * service2->com.paofuheshu.spring.bean.domain.conditional.test3.Service2@eadd4fb
     * 思考  为什么是service1注册上去了  而不是service2？
     * 因为在mainConfig3中的import注解中定义的顺序是BeanConfig1.class, BeanConfig2.class
     * 如果将BeanConfig2.class放在前面  则service2将被注册上去  service1不会注册
     */
    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        Map<String, IService> serviceMap = context.getBeansOfType(IService.class);
        serviceMap.forEach((beanName, bean) -> System.out.printf("%s->%s%n", beanName, bean));
    }

    /**
     * 根据环境选择配置类
     * 平常我们做项目的时候，有开发环境、测试环境、线上环境，每个环境中有些信息是不一样的，比如数据库的配置信息，
     * 下面模拟不同环境中使用不同的配置类来注册不同的bean。
     */
    public static void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig4.class);
        System.out.println(context.getBean("name"));
    }

    /**
     * Condition指定优先级
     * 多个Condition按顺序执行
     * 注解@Condtional中value指定多个Condtion的时候，默认情况下会按顺序执行
     * 输出结果：
     * com.paofuheshu.spring.bean.domain.conditional.test5.Condition1
     * com.paofuheshu.spring.bean.domain.conditional.test5.Condition2
     * com.paofuheshu.spring.bean.domain.conditional.test5.Condition3
     * 可以看出输出的属性和@Conditional中value值的顺序是一样的。
     */
    public static void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig5.class);
    }

    /**
     * 指定Condition的顺序
     * 自定义的Condition可以实现PriorityOrdered接口或者继承Ordered接口，或者使用@Order注解，通过这些来指定这些Condition的优先级。
     * 排序规则：先按PriorityOrdered排序，然后按照order的值进行排序；也就是：PriorityOrdered asc,order值 asc
     * 下面这几个都可以指定order的值
     * 接口：org.springframework.core.Ordered，有个getOrder方法用来返回int类型的值
     * 接口：org.springframework.core.PriorityOrdered，继承了Ordered接口，所以也有getOrder方 法
     * 注解：org.springframework.core.annotation.Order，有个int类型的value参数指定Order的大小
     * 输出结果：
     * com.paofuheshu.spring.bean.domain.conditional.test6.Condition3
     * com.paofuheshu.spring.bean.domain.conditional.test6.Condition2
     * com.paofuheshu.spring.bean.domain.conditional.test6.Condition1
     * 结果分析
     * 根据排序的规则，PriorityOrdered的会排在前面，然后会再按照order升序
     * Condition3实现的是PriorityOrdered接口  所以排在最前面
     * Condition2实现了Ordered接口 Condition1使用了@Order注解  两个按照顺序排
     * Condition2的order值大小为0 Condition1的order值大小为2
     * 按照asc排序  所以Condition2在Condition1前面
     * 所以最终顺序为Condition3->Condition2->Condition1
     */
    public static void test6() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig6.class);
    }

    /**
     * ConfigurationCondition使用
     * ConfigurationCondition使用的比较少，很多地方对这个基本上也不会去介绍，Condition接口基本上可
     * 以满足99%的需求了，但是springboot中却大量用到了ConfigurationCondition这个接口。
     * 需求：
     * 当容器中有Service这种类型的bean的时候，BeanConfig2才生效。
     * 很简单吧，加个Condition就行了，内部判断容器中是否有Service类型的bean
     * 此时如果使用@Conditional(MyCondition1.class) 也不会有任何输出
     * 原因：
     * 配置类的处理会依次经过2个阶段：配置类解析阶段和bean注册阶段，Condition
     * 接口类型的条件会对这两个阶段都有效，解析阶段的时候，容器中是还没有Service这个bean的，配置
     * 类中通过@Bean注解定义的bean在bean注册阶段才会被注册到spring容器，所以BeanConfig2在解析
     * 阶段去容器中是看不到Service这个bean的，所以就被拒绝了。
     * 此时我们需要用到ConfigurationCondition了，让条件判断在bean注册阶段才起效。
     * 将@Conditional(MyCondition1.class)替换成@Conditional(MyConfigurationCondition1.class)
     * 此时name这个bean被输出了。
     * 如果将BeanConfig1中service方法上面的@Bean去掉，此时Service就不会被注册到容器，再运
     * 行一下test7，会发现没有输出了，此时BeanConfig2会失效。
     * 判断bean存不存在的问题，通常会使用ConfigurationCondition这个接口，阶段为：
     * REGISTER_BEAN，这样可以确保条件判断是在bean注册阶段执行的
     */
    public static void test7() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig7.class); context.getBeansOfType(String.class).forEach((beanName, bean) -> { System.out.printf("%s->%s%n", beanName, bean); });
    }

    /**
     * 总结
     * 1. @Conditional注解可以标注在spring需要处理的对象上（配置类、@Bean方法），相当于加了个
     * 条件判断，通过判断的结果，让spring觉得是否要继续处理被这个注解标注的对象
     * 2. spring处理配置类大致有2个过程：解析配置类、注册bean，这两个过程中都可以使用
     * @Conditional来进行控制spring是否需要处理这个过程
     * 3. Condition默认会对2个过程都有效
     * 4. ConfigurationCondition控制得更细一些，可以控制到具体那个阶段使用条件判断
     */
}
