package com.paofuheshu.spring.bean.test.importdemo;

import com.paofuheshu.spring.bean.domain.importdemo.test1.MainConfig1;
import com.paofuheshu.spring.bean.domain.importdemo.test2.MainConfig2;
import com.paofuheshu.spring.bean.domain.importdemo.test3.MainConfig3;
import com.paofuheshu.spring.bean.domain.importdemo.test4.MainConfig4;
import com.paofuheshu.spring.bean.domain.importdemo.test5.MainConfig5;
import com.paofuheshu.spring.bean.domain.importdemo.test6.MainConfig6;
import com.paofuheshu.spring.bean.domain.importdemo.test6.Service1;
import com.paofuheshu.spring.bean.domain.importdemo.test6.Service2;
import com.paofuheshu.spring.bean.domain.importdemo.test6.Test;
import com.paofuheshu.spring.bean.domain.importdemo.test7.MainConfig7;
import com.paofuheshu.spring.bean.domain.importdemo.test8.MainConfig8;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 21:47
 * @des
 * 注解@Import的value常见的有6种用法
 * 1. value为普通的类
 * 2. value为@Configuration标注的类
 * 3. value为@CompontentScan标注的类
 * 4. value为ImportBeanDefinitionRegistrar接口类型
 * 5. value为ImportSelector接口类型
 * 6. value为DeferredImportSelector接口类型
 */
public class ImportTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
//        test7();
//        test8();
    }

    /**
     * value为普通的类
     * 输出结果：
     * mainConfig1->com.paofuheshu.spring.bean.domain.importdemo.test1.MainConfig1@524d6d96
     * com.paofuheshu.spring.bean.domain.importdemo.test1.Service1->com.paofuheshu.spring.bean.domain.importdemo.test1.Service1@152aa092
     * com.paofuheshu.spring.bean.domain.importdemo.test1.Service2->com.paofuheshu.spring.bean.domain.importdemo.test1.Service2@44a7bfbc
     * 结果分析：
     * 1. Service1和Service2成功注册到容器了。
     * 2. 通过@Import导入的2个类，bean名称为完整的类名
     * 我们也可以指定被导入类的bean名称，使用@Compontent注解就可以了
     *
     * 总结一下
     * 按模块的方式进行导入，需要哪个导入哪个，不需要的时候，直接修改一下总的配置类，调整一下@Import就可以了，非常方便。
     */
    public static void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig1.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName + "->" + context.getBean(beanDefinitionName));
        }
    }

    /**
     * value为@Configuration标注的配置类
     * 项目比较大的情况下，会按照模块独立开发，每个模块在maven中就表现为一个个的构建，然后通过坐标的方式进行引入需要的模块。
     * 假如项目中有2个模块，2个模块都有各自的配置类
     * 输出结果：
     * mainConfig2->com.paofuheshu.spring.bean.domain.importdemo.test2.MainConfig2@6b695b06
     * com.paofuheshu.spring.bean.domain.importdemo.test2.ConfigModule1->com.paofuheshu.spring.bean.domain.importdemo.test2.ConfigModule1$$EnhancerBySpringCGLIB$$ec37de4d@4d1bf319
     * module1->我是模块1配置类！
     * com.paofuheshu.spring.bean.domain.importdemo.test2.ConfigModule2->com.paofuheshu.spring.bean.domain.importdemo.test2.ConfigModule2$$EnhancerBySpringCGLIB$$249a816e@6f53b8a
     * module2->我是模块2配置类！
     */
    public static void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName + "->" + context.getBean(beanDefinitionName));
        }
    }

    /**
     * value为@CompontentScan标注的类
     * 项目中分多个模块，每个模块有各自独立的包，我们在每个模块所在的包中配置一个@CompontentScan类，然后通过@Import来导入需要启用的模块。
     * 部分输出结果：
     * module1Service1->com.paofuheshu.spring.bean.domain.importdemo.test3.module1.Module1Service1@5d7148e2
     * module1Service2->com.paofuheshu.spring.bean.domain.importdemo.test3.module1.Module1Service2@25fb8912
     * module2Service1->com.paofuheshu.spring.bean.domain.importdemo.test3.module2.Module2Service1@7c24b813
     * module2Service2->com.paofuheshu.spring.bean.domain.importdemo.test3.module2.Module2Service2@2c35e847
     * 可以看到两个模块中通过@Compontent定义的4个bean都输出了。
     * 如果只想注册模块1中的bean，只需要修改一下@Import，去掉ComponentScanModule2.class就行了
     */
    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName + "->" + context.getBean(beanDefinitionName));
        }
    }

    /**
     * value为ImportBeanDefinitionRegistrar接口类型
     * 用法（4个步骤）
     * 1. 定义ImportBeanDefinitionRegistrar接口实现类，在registerBeanDefinitions方法中使用 registry来注册bean
     * 2. 使用@Import来导入步骤1中定义的类
     * 3. 使用步骤2中@Import标注的类作为AnnotationConfigApplicationContext构造参数创建spring容器
     * 4. 使用AnnotationConfigApplicationContext操作bean
     */
    public static void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig4.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName + "->" + context.getBean(beanDefinitionName));
        }
    }

    /**
     * value为ImportSelector接口类型
     * 用法（4个步骤）
     * 1. 定义ImportSelector接口实现类，在selectImports返回需要导入的类的名称数组
     * 2. 使用@Import来导入步骤1中定义的类
     * 3. 使用步骤2中@Import标注的类作为AnnotationConfigApplicationContext构造参数创建spring容 器
     * 4. 使用AnnotationConfigApplicationContext操作bean
     * 输出结果：
     * mainConfig5->com.paofuheshu.spring.bean.domain.importdemo.test5.MainConfig5@2584b82d
     * com.paofuheshu.spring.bean.domain.importdemo.test5.Service1->com.paofuheshu.spring.bean.domain.importdemo.test5.Service1@7bbc8656
     * com.paofuheshu.spring.bean.domain.importdemo.test5.Module1Config->com.paofuheshu.spring.bean.domain.importdemo.test5.Module1Config$$EnhancerBySpringCGLIB$$6f789f73@6933b6c6
     * name->泡芙和树
     * address->池州市
     */
    public static void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig5.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName + "->" + context.getBean(beanDefinitionName));
        }
    }

    /**
     * 需求：凡是类名中包含service的，调用他们内部任何方法，我们希望调用之后能够输出这些方法的耗时。
     * 实现分析
     * 之前复习代理， 此处我们就可以通过代理来实现，bean实例创建的过程中，我们可以给这些bean生成一个代理，在代理中统计方法的耗时，这里面有2点：
     * 1. 创建一个代理类，通过代理来间接访问需要统计耗时的bean对象
     * 2. 拦截bean的创建，给bean实例生成代理生成代理
     */
    public static void test6() {
        // 1.通过AnnotationConfigApplicationContext创建spring容器，参数为@Import标注的类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig6.class);
        Service1 service1 = context.getBean(Service1.class);
        Service2 service2 = context.getBean(Service2.class);
        Test test = context.getBean(Test.class);
        service1.m1();
        service2.m1();
        test.m1();
    }

    /**
     * DeferredImportSelector接口
     * springboot中的核心功能@EnableAutoConfiguration就是靠DeferredImportSelector来实现的。
     * DeferredImportSelector是ImportSelector的子接口，既然是ImportSelector的子接口，所以也可以通
     * 过@Import进行导入，这个接口和ImportSelector不同地方有两点：
     * 1. 延迟导入
     * 2. 指定导入的类的处理顺序
     * 延迟导入
     * 比如@Import的value包含了多个普通类、多个@Configuration标注的配置类、多个ImportSelector接
     * 口的实现类，多个ImportBeanDefinitionRegistrar接口的实现类，还有DeferredImportSelector接口实
     * 现类，此时spring处理这些被导入的类的时候，会将DeferredImportSelector类型的放在最后处理，
     * 会先处理其他被导入的类，其他类会按照value所在的前后顺序进行处理。
     *
     * 那么我们是可以做很多事情的，比如我们可以在DeferredImportSelector导入的类中判断一下容器中是
     * 否已经注册了某个bean，如果没有注册过，那么再来注册。
     *
     * 另外一个注解@Conditional，这个注解可以按条件来注册bean，比如可以判断某个
     * bean不存在的时候才进行注册，某个类存在的时候才进行注册等等各种条件判断，通过@Conditional来
     * 结合DeferredImportSelector可以做很多事情。
     *
     * 输出结果：
     * name3
     * name1
     * name2
     *
     * 输出的结果结合一下@Import中被导入的3个类的顺序，可以看出DeferredImportSelector1是被最后
     * 处理的，其他2个是按照value中所在的先后顺序处理的。
     */
    public static void test7() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig7.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName + "-->" + context.getBean(beanDefinitionName));
        }
    }

    /**
     * 指定导入的类的处理顺序
     * 当@Import中有多个DeferredImportSelector接口的实现类时候，可以指定他们的顺序，指定顺序常见2种方式
     * 实现Ordered接口的方式
     * 实现Order注解的方式
     * 这两种实现方式中 value的值越小，优先级越高
     * 此处引用代码中
     * DeferredImportSelector1的order为2，DeferredImportSelector2的order为1，order值越小优先级越高。
     * 输出结果为：
     * name2
     * name1
     * 结果配合order的值，按照order从小到大来处理，可以看出DeferredImportSelector2先被处理的。
     */
    public static void test8() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig8.class);
    }

    /**
     * 总结
     * 1. @Import可以用来批量导入任何普通的组件、配置类，将这些类中定义的所有bean注册到容器中
     * 2. @Import常见的6种用法需要掌握
     * 3. 掌握ImportSelector、ImportBeanDefinitionRegistrar、DeferredImportSelector的用法
     * 4. DeferredImportSelector接口可以实现延迟导入、按序导入的功能
     * 5. spring中很多以@Enable开头的都是使用@Import集合ImportSelector方式实现的
     * 6. BeanDefinitionRegistry接口：bean定义注册器，这个需要掌握常见的方法
     */
}
