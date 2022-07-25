package com.paofuheshu.spring.bean.test.primary;

import com.paofuheshu.spring.bean.domain.primary.PrimaryNormalBean;
import com.paofuheshu.spring.utils.IocUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 21:26
 * @des  总结：当从容器中查找一个bean的时候，如果容器中出现多个Bean候选者时，可以通过primary="true"将
 * 当前bean置为首选者，那么查找的时候就会返回主要的候选者，否则将抛出异常。
 */
public class PrimaryTest {

    public static void main(String[] args) {
//        primaryTest();
        primaryTest1();
    }

    /**
     * 此时这段代码会报错，抛出异常： org.springframework.beans.factory.NoUniqueBeanDefinitionException 原因如下
     * spring容器中定义了2个bean，分别是serviceA和serviceB，
     * 这两个bean对象都实现了IService接口，而用例中我们想从容器中获取IService接口对应的bean，
     * 此时容器中有2个候选者（serviceA和serviceB）满足我们的需求，此时spring容器不知道如何选择，到底是返回serviceA呢
     * 还是返回serviceB呢？spring容器也懵逼了，所以报错了
     * 解决方法：
     * spring中可以通过bean元素的primary属性来解决这个问题，可以通过这个属性来指定当前bean为主要候选者，
     * 当容器查询一个bean的时候，如果容器中有多个候选者匹配的时候，此时spring会返回主要的候选者
     * 见下方方法primaryTest1
     */
    public static void primaryTest() {
        String src = "primary/primaryNormalBean.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        // 通过spring容器的T getBean(Class<T> requiredType)方法获取容器中对应的 bean
        PrimaryNormalBean.IService bean = context.getBean(PrimaryNormalBean.IService.class);
        System.out.println(bean);
    }

    /**
     * 设置xml文件同类型的bean其中一个的primary属性为true，此时如果spring容器查找到多个同类型的bean时 会优先返回这个候选者
     */
    public static void primaryTest1() {
        String src = "primary/primaryNormalBean1.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        // 通过spring容器的T getBean(Class<T> requiredType)方法获取容器中对应的 bean
        PrimaryNormalBean.IService bean = context.getBean(PrimaryNormalBean.IService.class);
        System.out.println(bean);
    }
}
