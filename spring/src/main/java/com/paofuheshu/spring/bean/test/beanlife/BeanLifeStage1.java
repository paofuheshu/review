package com.paofuheshu.spring.bean.test.beanlife;

import com.paofuheshu.spring.bean.domain.beanLife.test1.Car;
import com.paofuheshu.spring.bean.domain.beanLife.test1.CompositeObj;
import com.paofuheshu.spring.bean.domain.beanLife.test1.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.*;

import java.util.Arrays;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 14:42
 * @des
 */
public class BeanLifeStage1 {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    /**
     * 组装一个简单的bean
     */
    public static void test1() {
        // 指定class
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
        // 获取BeanDefinition
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition);
    }

    /**
     * 组装一个有属性的bean
     */
    public static void test2() {
        // 指定class
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName());
        beanDefinitionBuilder.addPropertyValue("name", "法拉利");
        // 获取BeanDefinition
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition);
        // 创建spring容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("car", beanDefinition);
        Car car = factory.getBean("car", Car.class);
        System.out.println(car);
    }

    /**
     * 组装一个有依赖关系的bean
     */
    public static void test3() {
        // 先创建car这个BeanDefinition
        AbstractBeanDefinition carBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Car.class.getName())
                .addPropertyValue("name", "法拉利").getBeanDefinition();
        // 创建User这个BeanDefinition
        AbstractBeanDefinition userBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(User.class.getName()).addPropertyValue("name", "泡芙和树")
                // 注入依赖的bean，需要使用addPropertyReference方法，2个参数，第一个为属性的名称，第二个为需要注入的bean的名称
                .addPropertyReference("car", "car").getBeanDefinition();
        // 创建spring容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("car", carBeanDefinition);
        factory.registerBeanDefinition("user", userBeanDefinition);
        System.out.println(factory.getBean("car"));
        System.out.println(factory.getBean("user"));
    }

    /**
     * 案例4：来2个有父子关系的bean
     */
    public static void test4() {
        // 先创建car这个BeanDefinition
        BeanDefinition carBeanDefinition1 = BeanDefinitionBuilder. genericBeanDefinition(Car.class)
                .addPropertyValue("name", "保时捷").getBeanDefinition();
        BeanDefinition carBeanDefinition2 = BeanDefinitionBuilder
                // 内部生成一个GenericBeanDefinition对象
                .genericBeanDefinition()
                // 设置父bean的名称为car1
                .setParentName("car1")
                .getBeanDefinition();
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("car1", carBeanDefinition1);
        factory.registerBeanDefinition("car2", carBeanDefinition2);
        //从容器中获取car1
        System.out.printf("car1->%s%n", factory.getBean("car1"));
        // 从容器中获取car2
        System.out.printf("car2->%s%n", factory.getBean("car2"));
    }

    /**
     * 案例5：通过api设置（Map、Set、List）属性
     * 输出：
     * 17:00:29.381 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car1'
     * car1->Car{name='保时捷'}
     * 17:00:29.430 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'car2'
     * car2->Car{name='宝马'}
     * 17:00:29.430 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'compositeObj'
     * compositeObj->CompositeObj(name=泡芙和树, salary=200, car1=Car{name='保时捷'},
     * stringList=[泡芙和树, 酒精泡芙, 泡芙], carList=[Car{name='保时捷'}, Car{name='宝马'}],
     * stringSet=[泡芙和树, 酒精泡芙, 泡芙], carSet=[Car{name='保时捷'},
     * Car{name='宝马'}], stringMap={name1=泡芙和树, name2=酒精泡芙, name3=泡芙},
     * stringCarMap={car1=Car{name='保时捷'}, car2=Car{name='宝马'}})
     *
     * 说明：
     * RuntimeBeanReference：用来表示bean引用类型，类似于xml中的ref
     * ManagedList：属性如果是List类型的，需要用到这个类进行操作，这个类继承了ArrayList
     * ManagedSet：属性如果是Set类型的，需要用到这个类进行操作，这个类继承了LinkedHashSet
     * ManagedMap：属性如果是Map类型的，需要用到这个类进行操作，这个类继承了LinkedHashMap
     */
    public static void test5() {
        AbstractBeanDefinition car1 = BeanDefinitionBuilder.genericBeanDefinition(Car.class).addPropertyValue("name", "保时捷").getBeanDefinition();
        AbstractBeanDefinition car2 = BeanDefinitionBuilder.genericBeanDefinition(Car.class).addPropertyValue("name", "宝马").getBeanDefinition();
        // 定义CompositeObj这个bean
        // 创建stringList这个属性对应的值
        ManagedList<String> stringList = new ManagedList<>();
        stringList.addAll(Arrays.asList("泡芙和树", "酒精泡芙", "泡芙"));
        // 创建carList这个属性对应的值,内部引用其他两个bean的名称[car1,car2]
        ManagedList<RuntimeBeanReference> carList = new ManagedList<>();
        carList.add(new RuntimeBeanReference("car1"));
        carList.add(new RuntimeBeanReference("car2"));
        //创建stringList这个属性对应的值
        ManagedSet<String> stringSet = new ManagedSet<>();
        stringSet.addAll(Arrays.asList("泡芙和树", "酒精泡芙", "泡芙"));
        // 创建carSet这个属性对应的值,内部引用其他两个bean的名称[car1,car2]
        ManagedSet<RuntimeBeanReference> carSet = new ManagedSet<>();
        carSet.add(new RuntimeBeanReference("car1"));
        carSet.add(new RuntimeBeanReference("car2"));
        // 创建stringMap这个属性对应的值
        ManagedMap<String, String> stringMap = new ManagedMap<>();
        stringMap.put("name1", "泡芙和树");
        stringMap.put("name2", "酒精泡芙");
        stringMap.put("name3", "泡芙");
        // 创建stringCarMap这个属性对应的值,内部引用其他两个bean的名称[car1,car2]
        ManagedMap<String, RuntimeBeanReference> stringCarMap = new ManagedMap<>();
        stringCarMap.put("car1", new RuntimeBeanReference("car1"));
        stringCarMap.put("car2", new RuntimeBeanReference("car2"));

        // 下面我们使用原生的api来创建BeanDefinition
        GenericBeanDefinition compositeObj = new GenericBeanDefinition();
        compositeObj.setBeanClassName(CompositeObj.class.getName());
        compositeObj.getPropertyValues()
                .add("name", "泡芙和树")
                .add("salary", 200)
                .add("car1", new RuntimeBeanReference("car1"))
                .add("stringList", stringList)
                .add("carList", carList)
                .add("stringSet", stringSet)
                .add("carSet", carSet)
                .add("stringMap", stringMap)
                .add("stringCarMap", stringCarMap);
        // 将上面bean 注册到容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("car1", car1);
        factory.registerBeanDefinition("car2", car2);
        factory.registerBeanDefinition("compositeObj", compositeObj);
        // 下面将容器中所有的bean输出
        for (String beanDefinitionName : factory.getBeanDefinitionNames()) {
            System.out.printf("%s->%s%n", beanDefinitionName, factory.getBean(beanDefinitionName));
        }
    }
}
