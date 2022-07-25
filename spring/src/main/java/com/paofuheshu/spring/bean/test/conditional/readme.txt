@Conditional注解
@Conditional注解是从spring4.0才有的，可以用在任何类型或者方法上面，通过@Conditional注解
可以配置一些条件判断，当所有条件都满足的时候，被@Conditional标注的目标才会被spring容器处理。
比如可以通过@Conditional来控制bean是否需要注册，控制被@Configuration标注的配置类是需要需要被解析等。
效果就像这段代码，相当于在spring容器解析目标前面加了一个条件判断：
if(@Conditional中配置的多个条件是否都匹配){ //spring继续处理被@Conditional注解标注的对象 }
@Conditional源码
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Conditional {
    Class<? extends Condition>[] value();
}
这个注解只有一个value参数，Condition类型的数组，Condition是一个接口，表示一个条件判断，内部有个方法返回true或false，
当所有Condition都成立的时候，@Conditional的结果才成立。

Condition接口
用来表示条件判断的接口，源码如下：
@FunctionalInterface
public interface Condition {
    // 判断条件是否匹配   context:条件判断上下文
    boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata);
}
是一个函数式接口，内部只有一个matches方法，用来判断条件是否成立的，2个参数：
context：条件上下文，ConditionContext接口类型的，可以用来获取容器中的信息
metadata：用来获取被@Conditional标注的对象上的所有注解信息

ConditionContext接口
这个接口中提供了一些常用的方法，可以用来获取spring容器中的各种信息，看一下源码：
public interface ConditionContext {
    // 返回bean定义注册器，可以通过注册器获取bean定义的各种配置信息
    BeanDefinitionRegistry getRegistry();

    // 返回ConfigurableListableBeanFactory类型的bean工厂，相当于一个ioc容器对象
    @Nullable
    ConfigurableListableBeanFactory getBeanFactory();

    // 返回当前spring容器的环境配置信息对象
    Environment getEnvironment();

    // 返回资源加载器
    ResourceLoader getResourceLoader();

    // 返回类加载器
    @Nullable
    ClassLoader getClassLoader();
}

比较关键性的问题：条件判断在什么时候执行？
Spring对配置类的处理主要分为2个阶段：
配置类解析阶段
会得到一批配置类的信息，和一些需要注册的bean
bean注册阶段
将配置类解析阶段得到的配置类和需要注册的bean注册到spring容器中
看一下什么是配置类
类中有下面任意注解之一的就属于配置类：
1. 类上有@Compontent注解
2. 类上有@Configuration注解
3. 类上有@CompontentScan注解
4. 类上有@Import注解
5. 类上有@ImportResource注解
6. 类中有@Bean标注的方法

判断一个类是不是一个配置类，是否的是下面这个方法，
org.springframework.context.annotation.ConfigurationClassUtils#isConfigurationCandidate
spring中处理这2个过程会循环进行，直到完成所有配置类的解析及所有bean的注册。
Spring对配置类处理过程
源码位置：org.springframework.context.annotation.ConfigurationClassPostProcessor#processConfigBeanDefinitions
整个过程大致的过程如下：
1. 通常我们会通过new AnnotationConfigApplicationContext()传入多个配置类来启动spring容器
2. spring对传入的多个配置类进行解析
3. 配置类解析阶段：这个过程就是处理配置类上面6中注解的过程，此过程中又会发现很多新的配置类，
比如@Import导入的一批新的类刚好也符合配置类，而被@CompontentScan扫描到的一些类刚好也是配置类；此时会对这些新产生的配置类进行同样的过程解析
4. bean注册阶段：配置类解析后，会得到一批配置类和一批需要注册的bean，此时spring容器会将
这批配置类作为bean注册到spring容器，同样也会将这批需要注册的bean注册到spring容器
5. 经过上面第3个阶段之后，spring容器中会注册很多新的bean，这些新的bean中可能又有很多新的配置类
6. Spring从容器中将所有bean拿出来，遍历一下，会过滤得到一批未处理的新的配置类，继续交给第3步进行处理
7. step3到step6，这个过程会经历很多次，直到完成所有配置类的解析和bean的注册

从上面过程中可以了解到：
1. 可以在配置类上面加上@Conditional注解，来控制是否需要解析这个配置类，配置类如果不被解析，那么这个配置上面6种注解的解析都会被跳过
2. 可以在被注册的bean上面加上@Conditional注解，来控制这个bean是否需要注册到spring容器中
3. 如果配置类不会被注册到容器，那么这个配置类解析所产生的所有新的配置类及所产生的所有新的bean都不会被注册到容器
一个配置类被spring处理有2个阶段：配置类解析阶段、bean注册阶段（将配置类作为bean被注册到spring容器)。
如果将Condition接口的实现类作为配置类上@Conditional中，那么这个条件会对两个阶段都有效，此时通过Condition是无法精细的控制某个阶段的，
如果想控制某个阶段，比如可以让他解析，但是不能让他注册，此时就就需要用到另外一个接口了：ConfigurationCondition

ConfigurationCondition接口
看一下这个接口的源码：
public interface ConfigurationCondition extends Condition {
    // 条件判断的阶段，是在解析配置类的时候过滤还是在创建bean的时候过滤
    ConfigurationPhase getConfigurationPhase();

    // 表示阶段的枚举：2个值
    enum ConfigurationPhase {
        // 配置类解析阶段，如果条件为false，配置类将不会被解析
        PARSE_CONFIGURATION,

        // bean注册阶段，如果为false，bean将不会被注册
        REGISTER_BEAN
    }
}
ConfigurationCondition接口相对于Condition接口多了一个getConfigurationPhase方法，用来指定条
件判断的阶段，是在解析配置类的时候过滤还是在创建bean的时候过滤。

@Conditional使用的3步骤
1. 自定义一个类，实现Condition或ConfigurationCondition接口，实现matches方法
2. 在目标对象上使用@Conditional注解，并指定value的指为自定义的Condition类型
3. 启动spring容器加载资源，此时@Conditional就会起作用了

总结：
1. @Conditional注解可以标注在spring需要处理的对象上（配置类、@Bean方法），相当于加了个
条件判断，通过判断的结果，让spring觉得是否要继续处理被这个注解标注的对象
2. spring处理配置类大致有2个过程：解析配置类、注册bean，这两个过程中都可以使用
@Conditional来进行控制spring是否需要处理这个过程
3. Condition默认会对2个过程都有效
4. ConfigurationCondition控制得更细一些，可以控制到具体那个阶段使用条件判断