package com.paofuheshu.spring.bean.test.componentScan;

import com.paofuheshu.spring.bean.domain.componentScan.ScanBean1;
import com.paofuheshu.spring.bean.domain.componentScan.ScanBean2;
import com.paofuheshu.spring.bean.domain.componentScan.ScanBean3;
import com.paofuheshu.spring.bean.domain.componentScan.ScanBean4;
import com.paofuheshu.spring.bean.domain.componentScan.test5.ScanBean5;
import com.paofuheshu.spring.bean.domain.componentScan.test6.ScanBean6;
import com.paofuheshu.spring.bean.domain.componentScan.test7.ScanBean7;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 19:10
 * @des
 */
public class ComponentScanTest {

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
     * 情况1：任何参数未设置
     * 在ScanBean1使用了@ComponentScan注解  并且没有设置任何参数  此时会默认扫描ScanBean1所在类的包以及子包
     * 并将类上有@Component、@Repository、@Service、@Controller任何一个注解的注册到容器中
     */
    public static void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBean1.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + context.getBean(beanName));
        }
    }

    /**
     * 情况2：指定需要扫描的包
     * 指定需要扫描哪些包，可以通过value或者basePackage来配置，二者选其一，都配置运行会报错，此方法中我们通过value来配置。
     * 注意
     * 指定包名的方式扫描存在的一个隐患，若包被重名了，会导致扫描会失效，一般情况下面我们使用
     * basePackageClasses的方式来指定需要扫描的包，这个参数可以指定一些类型，默认会扫描这些类所
     * 在的包及其子包中所有的类，这种方式可以有效避免这种问题。
     */
    public static void test2() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBean2.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + context.getBean(beanName));
        }
    }

    /**
     * 情况3：basePackageClasses指定扫描范围
     * 我们可以在需要扫描的包中定义一个标记的接口或者类，他们的唯一的作用是作为basePackageClasses的值，其他没有任何用途。
     */
    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBean3.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + context.getBean(beanName));
        }
    }

    /**
     * 情况4：includeFilters的使用
     * 看一下includeFilters这个参数的定义：Filter[] includeFilters() default {};
     * 是一个 Filter 类型的数组，多个Filter之间为或者关系，即满足任意一个就可以了，看一下 Filter的代码：
     * @Retention(RetentionPolicy.RUNTIME)
     * @Target({})
     * @interface Filter {
     * FilterType type() default FilterType.ANNOTATION;
     * @AliasFor("classes")
     * Class<?>[] value() default {};
     * @AliasFor("value")
     * Class<?>[] classes() default {};
     * String[] pattern() default {};
     * }
     * 可以看出Filter也是一个注解，参数：
     * type：过滤器的类型，是个枚举类型，5种类型
     * ANNOTATION：通过注解的方式来筛选候选者，即判断候选者是否有指定的注解
     * ASSIGNABLE_TYPE：通过指定的类型来筛选候选者，即判断候选者是否是指定的类型
     * ASPECTJ：ASPECTJ表达式方式，即判断候选者是否匹配ASPECTJ表达式
     * REGEX：正则表达式方式，即判断候选者的完整名称是否和正则表达式匹配
     * CUSTOM：用户自定义过滤器来筛选候选者，对候选者的筛选交给用户自己来判断
     *
     * value：和参数classes效果一样，二选一
     * classes：3种情况如下
     * 当type=FilterType.ANNOTATION时，通过classes参数可以指定一些注解，用来判断被扫描的类上是否有classes参数指定的注解
     * 当type=FilterType.ASSIGNABLE_TYPE时，通过classes参数可以指定一些类型，用来判断被扫描的类是否是classes参数指定的类型
     * 当type=FilterType.CUSTOM时，表示这个过滤器是用户自定义的，classes参数就是用来指定用户自定义的过滤器，
     * 自定义的过滤器需要实现org.springframework.core.type.filter.TypeFilter接口
     * pattern：2种情况如下
     * 当type=FilterType.ASPECTJ时，通过pattern来指定需要匹配的ASPECTJ表达式的值
     * 当type=FilterType.REGEX时，通过pattern来自正则表达式的值
     *
     * 测试:扫描包含注解的类
     * 需求
     * 我们自定义一个注解，让标注有这些注解的类自动注册到容器中
     *
     * 输出结果：
     * scanBean4->com.paofuheshu.spring.bean.domain.componentScan.ScanBean4@25fb8912
     * service1->com.paofuheshu.spring.bean.domain.componentScan.test4.Service1@7c24b813
     * service2->com.paofuheshu.spring.bean.domain.componentScan.test4.Service2@2c35e847
     * 结果分析：
     * Service1上标注了 @MyBean 注解，被注册到容器了，但是 Service2 上没有标注 @MyBean，为什么也被注册到容器了？
     * 原因：Service2上标注了 @Component 注解，而@ComponentScan注解中的 useDefaultFilters
     * 默认是 true ，表示也会启用默认的过滤器，而默认的过滤器会将标注有 @Component、@Repository、 @Service、@Controller 这几个注解的类也注册到容器中
     * 此时如果设置useDefaultFilters为false  则service2不会被注册到容器中
     *
     * 扩展：自定义注解支持定义bean名称
     * 上面的自定义的@MyBean注解，是无法指定bean的名称的，可以对这个注解做一下改造，加个value参数来指定bean的名称
     *
     */
    public static void test4() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBean4.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + context.getBean(beanName));
        }
    }

    /**
     * 情况5：包含指定类型的类
     * 让spring来进行扫描，类型满足test5包下的IService的都将其注册到容器中
     */
    public static void test5() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBean5.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + context.getBean(beanName));
        }
    }

    /**
     * 情况6：自定义Filter
     * 用法
     * 有时候我们需要用到自定义的过滤器，使用自定义过滤器的步骤：
     * 1.设置@Filter中type的类型为：FilterType.CUSTOM
     * 2.自定义过滤器类，需要实现接口：org.springframework.core.type.filter.TypeFilter
     * 3.设置@Filter中的classses为自定义的过滤器类型
     * 来看一下 TypeFilter 这个接口的定义：
     * @FunctionalInterface
     * public interface TypeFilter {
     *  boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException;
     * }
     * 是一个函数式接口，包含一个match方法，方法返回boolean类型，有2个参数，都是接口类型的，下面介绍一下这2个接口。
     * MetadataReader接口
     * 类元数据读取器，可以读取一个类上的任意信息，如类上面的注解信息、类的磁盘路径信息、类的class对象的各种信息，spring进行了封装，提供了各种方便使用的方法。
     * 看一下这个接口的定义：
     * public interface MetadataReader {
     * /**
     * 返回类文件的资源引用
     * Resource getResource();
     * /**
     * 返回一个ClassMetadata对象，可以通过这个读想获取类的一些元数据信息，如类的class对象、 是否是接口、是否有注解、是否是抽象类、父类名称、接口名称、内部包含的之类列表等等，可以去看一下源 码
     * ClassMetadata getClassMetadata();
     * /**
     *  获取类上所有的注解信息
     *  AnnotationMetadata getAnnotationMetadata();
     *
     *  MetadataReaderFactory接口
     *  类元数据读取器工厂，可以通过这个类获取任意一个类的MetadataReader对象。
     *  public interface MetadataReaderFactory {
     *  /*** 返回给定类名的MetadataReader对象
     *  MetadataReader getMetadataReader(String className) throws IOException; /***
     *  返回指定资源的MetadataReader对象
     *  MetadataReader getMetadataReader(Resource resource) throws IOException;
     *  }
     *  需求
     * 我们来个自定义的Filter，判断被扫描的类如果是 MyFilterService 接口类型的，就让其注册到容器中。
     */
    public static void test6() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBean6.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + context.getBean(beanName));
        }
    }

    /**
     * 情况7：excludeFilters
     * 配置排除的过滤器，满足这些过滤器的类不会被注册到容器中，用法和includeFilters一样
     * 此外 @ComponentScan还可以重复使用
     * 还有一种写法，就是使用@ComponentScans
     */
    public static void test7() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanBean7.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + "->" + context.getBean(beanName));
        }
    }
}
