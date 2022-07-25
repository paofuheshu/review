背景介绍
到目前为止，复习了2种注册bean的方式：
1. xml中bean元素的方式
2. @Bean注解标注方法的方式
通常情况下，项目中大部分类都需要交给spring去管理，按照上面这2种方式，代码量还是挺大的。
为了更方便bean的注册，Spring提供了批量的方式注册bean，方便大量bean批量注册，spring中的
@ComponentScan就是干这个事情的。

@ComponentScan
@ComponentScan用于批量注册bean。
这个注解会让spring去扫描某些包及其子包中所有的类，然后将满足一定条件的类作为bean注册到spring容器容器中。
具体需要扫描哪些包？以及这些包中的类满足什么条件时被注册到容器中，这些都可以通过这个注解中的参数动态配置。
先来看一下这个注解的定义：
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repeatable(ComponentScans.class)
public @interface ComponentScan {
    @AliasFor("basePackages") String[] value() default {};
    @AliasFor("value") String[] basePackages() default {};
    Class<?>[] basePackageClasses() default {};
    Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;
    Class<? extends ScopeMetadataResolver> scopeResolver() default AnnotationScopeMetadataResolver.class;
    ScopedProxyMode scopedProxy() default ScopedProxyMode.DEFAULT;
    String resourcePattern() default "**/*.class";
    boolean useDefaultFilters() default true;
    Filter[] includeFilters() default {};
    Filter[] excludeFilters() default {};
    boolean lazyInit() default false;
}
定义上可以看出此注解可以用在任何类型上面，不过我们通常将其用在类上面。
常用参数：
value：指定需要扫描的包，如：com.paofuheshu
basePackages：作用同value；value和basePackages不能同时存在设置，可二选一
basePackageClasses：指定一些类，spring容器会扫描这些类所在的包及其子包中的类
nameGenerator：自定义bean名称生成器
resourcePattern：需要扫描包中的那些资源，默认是：**/*.class，即会扫描指定包中所有的
class文件
useDefaultFilters：对扫描的类是否启用默认过滤器，默认为true
includeFilters：过滤器：用来配置被扫描出来的那些类会被作为组件注册到容器中
excludeFilters：过滤器，和includeFilters作用刚好相反，用来对扫描的类进行排除的，被排除的
类不会被注册到容器中
lazyInit：是否延迟初始化被注册的bean
@Repeatable(ComponentScans.class)，这个注解可以同时使用多个。

@ComponentScan工作的过程：
1. Spring会扫描指定的包，且会递归下面子包，得到一批类的数组
2. 然后这些类会经过上面的各种过滤器，最后剩下的类会被注册到容器中

所以了解这个注解，主要关注2个问题：
第一个：需要扫描哪些包？通过 value、backPackages、basePackageClasses 这3个参数来控制
第二：过滤器有哪些？通过 useDefaultFilters、includeFilters、excludeFilters 这3个参数来控制过滤器
这两个问题搞清楚了，就可以确定哪些类会被注册到容器中。
默认情况下，任何参数都不设置的情况下，此时，会将@ComponentScan修饰的类所在的包作为扫描包；
默认情况下useDefaultFilters为true，这个为true的时候，spring容器内部会使用默认过滤器，
规则是：凡是类上有 @Repository、@Service、@Controller、@Component 这几个注解中的任何一个的，
那么这个类就会被作为bean注册到spring容器中，所以默认情况下，只需在类上加上这几个注解中的任何一个，这些类就会自动交给spring容器来管理了。

@Component、@Repository、@Service、@Controller
这几个注解都是spring提供的。
先说一下 @Component 这个注解，看一下其定义：
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component { String value() default ""; }

从定义中可以看出，这个注解可以用在任何类型上面。
通常情况下将这个注解用在类上面，标注这个类为一个组件，默认情况下，被扫描的时候会被作为bean注册到容器中。
value参数：被注册为bean的时候，用来指定bean的名称，如果不指定，默认为类名首字母小写。如：类UserService对应的beanName为userService

再来看看 @Repository 源码如下：
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Repository {
    @AliasFor(annotation = Component.class)
    String value() default "";
}

Repository上面有@Component注解。
value参数上面有 @AliasFor(annotation = Component.class) ，设置value参数的时候，也相当于给 @Component 注解中的value设置值。

其他两个注解 @Service、@Controller 源码和 @Repository 源码类似。
这4个注解本质上是没有任何差别，都可以用在类上面，表示这个类被spring容器扫描的时候，可以作为一个bean组件注册到spring容器中。
spring容器中对这4个注解的解析并没有进行区分，统一采用 @Component 注解的方式进行解析，所以这几个注解之间可以相互替换。
spring提供这4个注解，是为了让系统更清晰，通常情况下，系统是分层结构的，多数系统一般分为controller层、service层、dao层。
@controller通常用来标注controller层组件，@service注解标注service层的组件，@Repository标注dao层的组件，
这样可以让整个系统的结构更清晰，当看到这些注解的时候，会和清晰的知道属于哪个层，
对于spring来说，将这3个注解替换成@Component注解，对系统没有任何影响，产生的效果是一样的。
下面通过案例来感受@ComponentScan各种用法。具体案例代码和测试类对象见componentScan包下

Spring中这块的源码
@ComponentScan注解是被下面这个类处理的
org.springframework.context.annotation.ConfigurationClassPostProcessor
这个类非常非常关键，主要用户bean的注册，前面我们介绍的@Configuration,@Bean注解也是被这个类处理的。
还有下面这些注解：
@PropertySource @Import @ImportResource @Component
以上这些注解都是被ConfigurationClassPostProcessor这个类处理的，内部会递归处理这些注解，完成bean的注册。
以@ComponentScan来说一下过程，第一次扫描之后会得到一批需要注册的类，然后会对这些需要注册的类进行遍历，判断是否有上面任意一个注解，如果有，会将这个类交给
ConfigurationClassPostProcessor继续处理，直到递归完成所有bean的注册。

总结
1. @ComponentScan用于批量注册bean，spring会按照这个注解的配置，递归扫描指定包中的所有类，将满足条件的类批量注册到spring容器中
2. 可以通过value、basePackages、basePackageClasses 这几个参数来配置包的扫描范围
3. 可以通过useDefaultFilters、includeFilters、excludeFilters这几个参数来配置类的过滤器，被过滤器处理之后剩下的类会被注册到容器中
4. 指定包名的方式配置扫描范围存在隐患，包名被重命名之后，会导致扫描实现，所以一般我们在需要扫描的包中可以创建一个标记的接口或者类，
作为basePackageClasses的值，通过这个来控制包的扫描范围
5. @ComponentScan注解会被ConfigurationClassPostProcessor类递归处理，最终得到所有需要注册的类。