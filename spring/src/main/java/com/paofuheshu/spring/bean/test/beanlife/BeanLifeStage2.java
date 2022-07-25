package com.paofuheshu.spring.bean.test.beanlife;

import com.paofuheshu.spring.bean.domain.beanLife.test2.Service1;
import com.paofuheshu.spring.bean.domain.beanLife.test2.Service2;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 17:13
 * @des
 */
public class BeanLifeStage2 {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    /**
     * xml方式bean配置信息解析
     * 输出结果：
     * 一共注册了4个bean
     * 17:18:36.924 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car'
     * car:
     * beanDefinitionClassName：org.springframework.beans.factory.support.GenericBeanDefinition
     * beanDefinition：Generic bean: class [com.paofuheshu.spring.bean.domain.beanLife.test1.Car]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in class path resource [beanlife/beanXml.xml]
     * bean：Car{name='宝马'}
     * 17:18:36.958 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car1'
     * car1:
     * beanDefinitionClassName：org.springframework.beans.factory.support.GenericBeanDefinition
     * beanDefinition：Generic bean: class [com.paofuheshu.spring.bean.domain.beanLife.test1.Car]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in class path resource [beanlife/beanXml.xml]
     * bean：Car{name='奥迪'}
     * 17:18:36.958 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car2'
     * car2:
     * beanDefinitionClassName：org.springframework.beans.factory.support.GenericBeanDefinition
     * beanDefinition：Generic bean with parent 'car1': class [null]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in class path resource [beanlife/beanXml.xml]
     * bean：Car{name='奥迪'}
     * 17:18:36.958 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user'
     * user:
     * beanDefinitionClassName：org.springframework.beans.factory.support.GenericBeanDefinition
     * beanDefinition：Generic bean: class [com.paofuheshu.spring.bean.domain.beanLife.test1.User]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in class path resource [beanlife/beanXml.xml]
     * bean：User(name=泡芙和树, car=Car{name='奥迪'})
     * 这几个BeanDefinition都是 GenericBeanDefinition 这种类型的，也就是说
     * xml中定义的bean被解析之后都是通过 GenericBeanDefinition 这种类型表示的
     */
    public static void test1() {
        //  定义一个spring容器，这个容器默认实现了BeanDefinitionRegistry，所以本身就是一个bean注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 定义一个xml的BeanDefinition读取器，需要传递一个BeanDefinitionRegistry（bean注册器）对象
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        // 指定bean xml配置文件的位置
        String path = "beanlife/beanXml.xml";
        // 通过XmlBeanDefinitionReader加载bean xml文件，然后将解析产生的BeanDefinition注册到容器中
        int count = xmlBeanDefinitionReader.loadBeanDefinitions(path);
        System.out.println("一共注册了" + count + "个bean");
        // 打印出注册的bean的配置信息
        for (String beanDefinitionName : factory.getBeanDefinitionNames()) {
            // 通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanDefinitionName);
            // 获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            // 通过名称获取bean对象
            Object bean = factory.getBean(beanDefinitionName);
            // 打印输出
            System.out.println(beanDefinitionName + ":");
            System.out.println("beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("beanDefinition：" + beanDefinition);
            System.out.println("bean：" + bean);
        }
    }

    /**
     * properties文件方式bean配置信息解析
     * 输出结果：
     * 17:27:51.134 [main] DEBUG org.springframework.beans.factory.support.PropertiesBeanDefinitionReader - Loaded 4 bean definitions from class path resource [beanlife/beans.properties]
     * 一共注册了4个bean
     * 17:27:51.134 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user'
     * 17:27:51.166 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car'
     * user:
     * beanDefinitionClassName：org.springframework.beans.factory.support.GenericBeanDefinition
     * beanDefinition：Generic bean: class [com.paofuheshu.spring.bean.domain.beanLife.test1.User]; scope=singleton; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null
     * bean：User(name='paofuheshu', car=Car{name='bmw'})
     * 17:27:51.166 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car1'
     * car1:
     * beanDefinitionClassName：org.springframework.beans.factory.support.GenericBeanDefinition
     * beanDefinition：Generic bean: class [com.paofuheshu.spring.bean.domain.beanLife.test1.Car]; scope=singleton; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null
     * bean：Car{name='aodi'}
     * car:
     * beanDefinitionClassName：org.springframework.beans.factory.support.GenericBeanDefinition
     * beanDefinition：Generic bean: class [com.paofuheshu.spring.bean.domain.beanLife.test1.Car]; scope=singleton; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null
     * bean：Car{name='bmw'}
     * 17:27:51.166 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car2'
     * car2:
     * beanDefinitionClassName：org.springframework.beans.factory.support.GenericBeanDefinition
     * beanDefinition：Generic bean with parent 'car1': class [null]; scope=singleton; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null
     * bean：Car{name='aodi'}
     * 输出和xml方式输出基本上一致。
     * properties方式使用起来并不是太方便，所以平时我们很少看到有人使用。
     */
    public static void test2() {
        // 定义一个spring容器，这个容器默认实现了BeanDefinitionRegistry，所以本身就是一个bean 注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 定义一个properties的BeanDefinition读取器，需要传递一个 BeanDefinitionRegistry（bean注册器）对象
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(factory);
        // 指定bean xml配置文件的位置
        String path = "beanlife/beans.properties";
        // 通过PropertiesBeanDefinitionReader加载bean properties文件，然后将解析产生的 BeanDefinition注册到容器容器中
        int count = propertiesBeanDefinitionReader.loadBeanDefinitions(path);
        System.out.println("一共注册了" + count + "个bean");
        // 打印出注册的bean的配置信息
        for (String beanDefinitionName : factory.getBeanDefinitionNames()) {
            // 通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanDefinitionName);
            // 获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            // 通过名称获取bean对象
            Object bean = factory.getBean(beanDefinitionName);
            // 打印输出
            System.out.println(beanDefinitionName + ":");
            System.out.println("beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("beanDefinition：" + beanDefinition);
            System.out.println("bean：" + bean);
        }
    }

    /**
     * 注解定义的bean解析为BeanDefinition
     * 输出结果：
     * service1:
     * beanDefinitionClassName：org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition
     * beanDefinition：Generic bean: class [com.paofuheshu.spring.bean.domain.beanLife.test2.Service1]; scope=prototype; abstract=false; lazyInit=true; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=true; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null
     * bean：com.paofuheshu.spring.bean.domain.beanLife.test2.Service1@5ae50ce6
     * 17:34:38.287 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'service2'
     * service2:
     * beanDefinitionClassName：org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition
     * beanDefinition：Generic bean: class [com.paofuheshu.spring.bean.domain.beanLife.test2.Service2]; scope=singleton; abstract=false; lazyInit=null; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null
     * bean：Service2{service1=null}
     * 输出中可以看出service1这个bean的beanDefinition中lazyInit确实为true，primary也为true，scope
     * 为prototype，说明类Service1注解上标注3个注解信息被解析之后放在了beanDefinition中。
     * todo 注意下：最后一行中的service1为什么为null，不是标注了@Autowired么？
     */
    public static void test3() {
        // 定义一个spring容器，这个容器默认实现了BeanDefinitionRegistry，所以本身就是一个bean注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 定义一个注解方式的BeanDefinition读取器，需要传递一个BeanDefinitionRegistry（bean注 册器）对象
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(factory);
        // 通过annotatedBeanDefinitionReader加载注解定义的bean，然后将解析产生的 BeanDefinition注册到容器容器中
        annotatedBeanDefinitionReader.register(Service1.class, Service2.class);
        // 加上这行代码  最后一行中的service1就不为null了
        factory.getBeansOfType(BeanPostProcessor.class).values().forEach(factory::addBeanPostProcessor);
        // 打印出注册的bean的配置信息
        for (String beanName : new String[]{"service1", "service2"}) {
            // 通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            // 获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            // 通过名称获取bean对象
            Object bean = factory.getBean(beanName);
            // 打印输出
            System.out.println(beanName + ":");
            System.out.println("beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("beanDefinition：" + beanDefinition);
            System.out.println("bean：" + bean);
        }
    }
}
