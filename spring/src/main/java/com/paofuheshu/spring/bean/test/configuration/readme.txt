@Configuration注解
用法
@Configuration这个注解可以加在类上，让这个类的功能等同于一个bean xml配置文件，如下：
@Configuration public class ConfigBean {

}
上面代码类似于下面的xml：
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">

</beans>
通过 AnnotationConfigApplicationContext 来加载 @Configuration 修饰的类，如下：
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigBean.class);

此时ConfigBean类中没有任何内容，相当于一个空的xml配置文件，此时我们要在ConfigBean类中注册
bean，那么我们就要用到@Bean注解了。

@Configuration 使用步骤：
1. 在类上使用 @Configuration 注解
2. 通过 AnnotationConfigApplicationContext 容器来加 @Configuration 注解修饰的类

@Bean注解
用法
这个注解类似于bean xml配置文件中的bean元素，用来在spring容器中注册一个bean。
@Bean注解用在方法上，表示通过方法来定义一个bean，默认将方法名称作为bean名称，将方法返回
值作为bean对象，注册到spring容器中。
如：
@Bean public User user1() { return new User(); }

@Bean注解还有很多属性，我们来看一下其源码：
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE}) //@1
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {
@AliasFor("name") String[] value() default {};
@AliasFor("value") String[] name() default {};
@Deprecated Autowire autowire() default Autowire.NO;
boolean autowireCandidate() default true;
String initMethod() default "";
String destroyMethod() default AbstractBeanDefinition.INFER_METHOD;
}
@1：说明这个注解可以用在方法和注解类型上面。
每个参数含义：
1. value和name是一样的，设置的时候，这2个参数只能选一个，原因是@AliasFor导致的
2. value：字符串数组，第一个值作为bean的名称，其他值作为bean的别名
3. autowire：这个参数上面标注了@Deprecated，表示已经过期了，不建议使用了
4. autowireCandidate：是否作为其他对象注入时候的候选bean
5. initMethod：bean初始化的方法，这个和生命周期有关，
6. destroyMethod：bean销毁的方法，也是和生命周期相关的，

spring这块的源码
spring中用下面这个类处理@Configuration这个注解：
org.springframework.context.annotation.ConfigurationClassPostProcessor
这里面重点关注这几个方法：
public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
public void enhanceConfigurationClasses(ConfigurableListableBeanFactory beanFactory)
最后一个方法会创建cglib代理

总结：
1. @Configuration注解修饰的类，会被spring通过cglib做增强处理，通过cglib会生成一个代理对
象，代理会拦截所有被@Bean注解修饰的方法，可以确保一些bean是单例的
2. 不管@Bean所在的类上是否有@Configuration注解，都可以将@Bean修饰的方法作为一个bean
注册到spring容器中