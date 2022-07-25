本测试包内容：
1. 介绍spring中通过注解实现依赖注入的所有方式
@Autowired注解
@Qualifier注解
@Resource注解
@Primary注解
@Bean中注入的几种方式
2. 将指定类型的所有bean，注入到集合中
3. 将指定类型的所有bean，注入到map中
4. 注入泛型
5. 依赖注入源码方面的一些介绍

@Autowired：注入依赖对象
作用
实现依赖注入，spring容器会对bean中所有字段、方法进行遍历，标注有@Autowired注解的，都会进行注入。
看一下其定义：
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    // Declares whether the annotated dependency is required.
    // <p>Defaults to {@code true}.
    boolean required() default true;
}
可以用在构造器、方法、方法参数、字段、注解上。
参数：
required：标注的对象是否必须注入，可能这个对象在容器中不存在，如果为true的时候，找不到
匹配的候选者就会报错，为false的时候，找不到也没关系 。
@Autowire查找候选者的过程
@Autowired查找候选者可以简化为下面这样
按类型找->通过限定符@Qualifier过滤->@Primary->@Priority->根据名称找（字段名称或者参数名称）
概括为：先按类型找，然后按名称找

spring使用下面这个类处理@Autowired注解
org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor

@Resource：注入依赖对象
作用
和@Autowired注解类似，也是用来注入依赖的对象的，spring容器会对bean中所有字段、方法进行遍历，标注有@Resource注解的，都会进行注入。

看一下这个注解定义：
javax.annotation.Resource
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface Resource {
    String name() default "";
    ..其他不常用的参数省略
}

这个注解是javax中定义的，并不是spring中定义的注解。
从定义上可以见，这个注解可以用在任何类型上面、字段、方法上面。
注意点：
用在方法上的时候，方法参数只能有一个。

@Resource查找候选者可以简化为
先按Resource的name值作为bean名称找->按名称（字段名称、方法名称、set属性名称）找->按类型找-> 通过限定符@Qualifier过滤->@Primary->@Priority->根据名称找（字段名称或者方法参数名称）
概括为：先按名称找，然后按类型找

spring使用下面这个类处理@Resource注解
org.springframework.context.annotation.CommonAnnotationBeanPostProcessor

@Qualifier：限定符
作用
这个单词的意思是：限定符。
可以在依赖注入查找候选者的过程中对候选者进行过滤。
看一下其定义：
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}

可以用在字段、方法、参数、任意类型、注解上面
有一个参数value

@Primary：设置为主要候选者
注入依赖的过程中，当有多个候选者的时候，可以指定哪个候选者为主要的候选者。
看一下其定义
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Primary { }

可以用在类上或者方法上面。
通常定义bean常见的有2种方式：
方式1：在类上标注@Component注解，此时可以配合@Primary，标注这个bean为主要候选者
方式2：在配置文件中使用@Bean注解标注方法，来注册bean，可以在@Bean标注的方法上加上@Primary，标注这个bean为主要候选bean。

@Bean定义bean时注入依赖的几种方式
常见3种方式
1. 硬编码方式
2. @Autowired、@Resource的方式
3. @Bean标注的方法参数的方式

总结
1. 需要掌握@Autowired注解和@Resource注解中候选者查找的过程
2. @Autowired：先通过类型找，然后通过名称找
3. @Resource：先通过名称找，然后通过类型找
4. @Autowired和@Resource，建议开发中使用@Autowired来实现依赖注入，spring的注解用起来更名正言顺一些
5. @Qualifier：限定符，可以用在类上；也可以用在依赖注入的地方，可以对候选者的查找进行过滤
6. @Primary：多个候选者的时候，可以标注某个候选者为主要的候选者
7. @Bean中注入依赖的3种方式需要掌握
8. 掌握泛型注入的使用
9. 主要还是掌握候选者的查找过程