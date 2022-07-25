@Import出现的背景
目前为止，注解的方式批量注册bean，已经复习了2种方式：
到目前，我们复习的批量定义bean的方式有2种：
1. @Configuration结合@Bean注解的方式
2. @ComponentScan扫描包的方式
但这会存在以下问题
问题1：
如果需要注册的类是在第三方的jar中，那么我们如果想注册这些bean有2种方式：
1. 通过@Bean标注方法的方式，一个个来注册
2. @ComponentScan的方式：默认的@CompontentScan是无能为力的，默认情况下只会注册@Component等注解标注的类，此时只能自定义@CompontentScan中的过滤器来实现了
但这2种方式都不是太好，每次有变化，调整的代码都比较多。
问题2：
通常我们的项目中有很多子模块，可能每个模块都是独立开发的，最后通过jar的方式引进来，每个模块中都有各自的@Configuration、@Bean标注的类，
或者使用@CompontentScan标注的类，被@Configuration、@Bean、@ComponentScan标注的类，我们统称为bean配置类，配置类可以用
来注册bean，此时如果我们只想使用其中几个模块的配置类，怎么办？

而@Import可以很好的解决这2个问题

@Import使用
先看Spring对它的注释，总结下来作用就是和xml配置的 <import />标签作用一样，允许通过它引入@Configuration标注的类 ，
引入ImportSelector接口和ImportBeanDefinitionRegistrar接口的实现，也包括 @Component注解的普通类。
总的来说：@Import可以用来批量导入需要注册的各种类，如普通的类、配置类，然后完成普通类和配置类中所有bean的注册。
@Import的源码：
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented public @interface Import {
    /**
     * {@link Configuration @Configuration}, {@link ImportSelector},
     * {@link ImportBeanDefinitionRegistrar}, or regular component classes to import.
     */
     Class<?>[] value();
}
@Import可以使用在任何类型上，通常情况下，类和注解上用的比较多。
value：一个Class数组，设置需要导入的类，可以是@Configuration标注的列，可以是ImportSelector接口
或者ImportBeanDefinitionRegistrar接口类型的，或者需要导入的普通组件类。

使用步骤
1. 将@Import标注在类上，设置value参数
2. 将@Import标注的类作为AnnotationConfigApplicationContext构造参数创建AnnotationConfigApplicationContext对象
3. 使用AnnotationConfigApplicationContext对象

@Import的value常见的有5种用法
1. value为普通的类
2. value为@Configuration标注的类
3. value为@CompontentScan标注的类
4. value为ImportBeanDefinitionRegistrar接口类型
5. value为ImportSelector接口类型
6. value为DeferredImportSelector接口类型

了解一下相关的几个接口
ImportBeanDefinitionRegistrar接口：这个接口提供了通过spring容器api的方式直接向容器中注册bean
接口的完整名称：org.springframework.context.annotation.ImportBeanDefinitionRegistrar
源码：
public interface ImportBeanDefinitionRegistrar {
    default void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
    BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        registerBeanDefinitions(importingClassMetadata, registry);
    }
    default void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    }
}
2个默认方法，都可以用来调用spring容器api来注册bean。
2个方法中主要有3个参数
importingClassMetadata：
AnnotationMetadata类型的，通过这个可以获取被@Import注解标注的类所有注解的信息。
registry：
BeanDefinitionRegistry类型，是一个接口，内部提供了注册bean的各种方法。
importBeanNameGenerator
BeanNameGenerator类型，是一个接口，内部有一个方法，用来生成bean的名称。
关于BeanDefinitionRegistry和BeanNameGenerator接口在来细说一下。

BeanDefinitionRegistry接口：bean定义注册器
bean定义注册器，提供了bean注册的各种方法，来看一下源码：
public interface BeanDefinitionRegistry extends AliasRegistry {
    // 注册一个新的bean定义  beanName：bean的名称  beanDefinition：bean定义信息
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException;

    // 通过bean名称移除已注册的bean  beanName：bean名称
    void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    // 通过名称获取bean的定义信息   beanName：bean名称
    BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    // 查看beanName是否注册过
    boolean containsBeanDefinition(String beanName);

    // 获取已经定义（注册）的bean名称列表
    String[] getBeanDefinitionNames();

    // 返回注册器中已注册的bean数量
    int getBeanDefinitionCount();

    // 确定给定的bean名称或者别名是否已在此注册表中使用  beanName：可以是bean名称或者bean的别名
    boolean isBeanNameInUse(String beanName);
}
基本上所有bean工厂都实现了这个接口，让bean工厂拥有bean注册的各种能力。
前面代码中我们用到的AnnotationConfigApplicationContext 类也实现了这个接口。

BeanNameGenerator接口：bean名称生成器
bean名称生成器，这个接口只有一个方法，用来生成bean的名称：
public interface BeanNameGenerator {
    String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);
}
spring内置了3个实现
DefaultBeanNameGenerator
默认bean名称生成器，xml中bean未指定名称的时候，默认就会使用这个生成器，默认为：完整的类名#bean编号
AnnotationBeanNameGenerator
注解方式的bean名称生成器，比如通过@Component(bean名称)的方式指定bean名称，如果没有通过注解方式指定名称，默认会将完整的类名作为bean名称。
FullyQualifiedAnnotationBeanNameGenerator
将完整的类名作为bean的名称

BeanDefinition接口：bean定义信息
用来表示bean定义信息的接口，我们向容器中注册bean之前，会通过xml或者其他方式定义bean的各种配置信息，
bean的所有配置信息都会被转换为一个BeanDefinition对象，然后通过容器中BeanDefinitionRegistry接口中的方法，
将BeanDefinition注册到spring容器中，完成bean的注册操作。

总结：
1. @Import可以用来批量导入任何普通的组件、配置类，将这些类中定义的所有bean注册到容器中
2. @Import常见的5种用法需要掌握
3. 掌握ImportSelector、ImportBeanDefinitionRegistrar、DeferredImportSelector的用法
4. DeferredImportSelector接口可以实现延迟导入、按序导入的功能
5. spring中很多以@Enable开头的都是使用@Import集合ImportSelector方式实现的
6. BeanDefinitionRegistry接口：bean定义注册器，这个需要掌握常见的方法