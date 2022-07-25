@Scope、@DependsOn、@ImportResource、@Lazy
@Scope：指定bean的作用域
@Scope用来配置bean的作用域，等效于bean xml中的bean元素中的scope属性。
看一下其源码：
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    @AliasFor("scopeName")
    String value() default "";
    @AliasFor("value")
    String scopeName() default "";
    ScopedProxyMode proxyMode() default ScopedProxyMode.DEFAULT;
}

@Scope可以用在类上和方法上
参数：value和scopeName效果一样，用来指定bean作用域名称，如：singleton、prototype

常见2种用法
1. 和@Compontent一起使用在类上
2. 和@Bean一起标注在方法上
具体的不同范围的scope可以查看com.paofuheshu.spring.bean.test.beanfactory.BeanFactoryTest类中的test2方法

@DependsOn：指定当前bean依赖的bean
用法
@DependsOn等效于bean xml中的bean元素中的depend-on属性。
spring在创建bean的时候，如果bean之间没有依赖关系，那么spring容器很难保证bean实例创建的顺
序，如果想确保容器在创建某些bean之前，需要先创建好一些其他的bean，可以通过@DependsOn来
实现，@DependsOn可以指定当前bean依赖的bean，通过这个可以确保@DependsOn指定的bean
在当前bean创建之前先创建好
常见2种用法
1. 和@Compontent一起使用在类上
2. 和@Bean一起标注在方法上

@ImportResource：配置类中导入bean定义的配置文件
用法
有些项目，前期可能采用xml的方式配置bean，后期可能想采用spring注解的方式来重构项目，但是有
些老的模块可能还是xml的方式，spring为了方便在注解方式中兼容老的xml的方式，提供了
@ImportResource注解来引入bean定义的配置文件。
bean定义配置文件：目前我们主要介绍了xml的方式，还有一种properties文件的方式，以后我们会介
绍，此时我们还是以引入bean xml来做说明。

@Lazy：延迟初始化

用法
@Lazy等效于bean xml中bean元素的lazy-init属性，可以实现bean的延迟初始化。
所谓延迟初始化：就是使用到的时候才会去进行初始化。
常用3种方式
1. 和@Compontent一起标注在类上，可以是这个类延迟初始化
2. 和@Configuration一起标注在配置类中，可以让当前配置类中通过@Bean注册的bean延迟初始化
3. 和@Bean一起使用，可以使当前bean延迟初始化
具体案例效果可以查看LazyBeanTest测试类
