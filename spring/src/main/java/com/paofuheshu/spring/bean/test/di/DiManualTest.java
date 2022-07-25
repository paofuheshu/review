package com.paofuheshu.spring.bean.test.di;

import com.paofuheshu.spring.utils.IocUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/6 20:43
 * @des  测试手动注入
 * 1. 主要测试了xml中bean的依赖注入，都是采用硬编码的方式进行注入的，这种算是手动的方式
 * 2. 注入普通类型通过value属性或者value元素设置注入的值；注入对象如果是容器的其他bean的时候，需要使用ref属性或者ref元素或者内置bean元素的方式
 * 3. 其他几种类型List、Set、Map、数组、Properties类型的注入
 * 手动注入存在的问题：
 * 如果需要注入的对象比较多，比如A类中有几十个属性，那么上面的property属性是不是需要写几十个，此时配置文件代码量暴增
 * 如果A类中新增或者删除了一些依赖，还需要手动去调整bean xml中的依赖配置信息，否则会报错
 * 不利于维护和扩展
 */
public class DiManualTest {

    public static void main(String[] args) {
        diByConstructorParamIndex();
        diByConstructorParamType();
        diByConstructorParamName();
        diBySetter();
        diBean();
    }

    /**
     * 根据构造器参数索引注入
     * 优缺点
     * 参数位置的注入对参数顺序有很强的依赖性，若构造函数参数位置被人调整过，会导致注入出错。
     * 不过通常情况下，不建议去在代码中修改构造函数，如果需要新增参数的，可以新增一个构造函数来实
     * 现，这算是一种扩展，不会影响目前已有的功能。
     */
    public static void diByConstructorParamIndex() {
        String beanXml = "di/diByConstructorParamIndex.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(beanXml);
        System.out.println(context.getBean("diByConstructorParamIndex"));
    }

    /**
     * 根据构造器参数类型注入
     * 优缺点
     * 实际上按照参数位置或者按照参数的类型注入，都有一个问题，很难通过bean的配置文件，知道这个参
     * 数是对应UserModel中的那个属性的，代码的可读性不好，比如我想知道这每个参数对应UserModel
     * 中的那个属性，必须要去看UserModel的源码，
     */
    public static void diByConstructorParamType() {
        String beanXml = "di/diByConstructorParamType.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(beanXml);
        System.out.println(context.getBean("diByConstructorParamType"));
    }

    /**
     * 根据构造器参数名称注入
     */
    public static void diByConstructorParamName() {
        String beanXml = "di/diByConstructorParamName.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(beanXml);
        System.out.println(context.getBean("diByConstructorParamName"));
    }

    /**
     * setter注入
     * 优缺点
     * setter注入相对于构造函数注入要灵活一些，构造函数需要指定对应构造函数中所有参数的值，而
     * setter注入的方式没有这种限制，不需要对所有属性都进行注入，可以按需进行注入。
     */
    public static void diBySetter() {
        String beanXml = "di/diBySetter.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(beanXml);
        System.out.println(context.getBean("diBySetter"));
    }

    // 上面几种方法都是注入普通类型的对象，都是通过value属性来设置需要注入的对象的值的，value属性的
    // 值是String类型的，spring容器内部自动会将value的值转换为对象的实际类型。
    // 若我们依赖的对象是容器中的其他bean对象的时候，需要用下面的方法进行注入。

    /**
     * 注入容器中的bean
     * 注入容器中的bean有两种写法：
     * ref属性方式
     * 内置bean的方式
     */
    public static void diBean() {
        String beanXml = "di/diBean.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(beanXml);
        System.out.println(context.getBean("diBeanByConstructor"));
        System.out.println(context.getBean("diBeanBySetter"));
    }

    /**
     * 其他类型注入
     * 注入java.util.List（list元素）
     * <list>
     * <value>Spring</value>
     * 或
     * <ref bean="bean名称"/>
     * 或
     * <list></list>
     * 或
     * <bean></bean>
     * 或
     * <array></array>
     * 或
     * <map></map>
     * </list>
     * 注入java.util.Set（set元素）
     * <set>
     *     <value>Spring</value>
     *     或
     *     <ref bean="bean名称"/>
     *     或
     *     <list></list>
     *     或
     *     <bean></bean>
     *     或
     *     <array></array>
     *     或
     *     <map></map>
     * </set>
     */
}
