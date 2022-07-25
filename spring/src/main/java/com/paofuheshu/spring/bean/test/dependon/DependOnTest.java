package com.paofuheshu.spring.bean.test.dependon;

import com.paofuheshu.spring.utils.IocUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 19:06
 * @des  问题：通过setter方式注入依赖的bean的时候，bean的创建顺序和销毁的顺序是什么样的？
 */
public class DependOnTest {

    public static void main(String[] args) {
//        normalBean();
//        strongDependenceBean();
        dependOnBean();
    }

    /**
     * 无依赖的bean创建和销毁的顺序
     * 从此处的输出来看
     * bean对象的创建顺序和bean xml中定义的顺序一致
     * bean销毁的顺序和bean xml中定义的顺序相反
     */
    public static void normalBean() {
        System.out.println("容器启动中!");
        String src = "dependon/normalBean.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        System.out.println("容器启动完毕，准备关闭spring容器!");
        // 关闭容器
        context.close();
        System.out.println("spring容器已关闭");
    }

    /**
     * 通过构造器强依赖bean创建和销毁顺序
     * 此时因为bean中存在依赖关系，所以虽然xml中bean定义顺序是：bean3、bean2、bean1
     * 但他的实际创建顺序是bean1 bean2 bean3 销毁顺序是bean3、bean2、bean1
     * 得出：
     * bean对象的创建顺序和bean依赖的顺序一致
     *  bean销毁的顺序和bean创建的顺序相反
     */
    public static void strongDependenceBean() {
        System.out.println("容器启动中!");
        String src = "dependon/strongDependenceBean.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        System.out.println("容器启动完毕，准备关闭spring容器!");
        // 关闭容器
        context.close();
        System.out.println("spring容器已关闭");
    }

    /**
     * 通过depend-on干预bean创建和销毁顺序
     * xml中在定义bean3的时候使用了depend-on 值为bean2 bean1 所以创建顺序为bean2 bean1 bean3
     * 销毁顺序为bean3 bean1 bean2
     * depend-on可以指定当前bean依赖的bean，通过这个可以确保depend-on指定的bean在当前
     * bean创建之前先创建好，销毁顺序刚好相反
     */
    public static void dependOnBean() {
        System.out.println("容器启动中!");
        String src = "dependon/dependOnBean.xml";
        ClassPathXmlApplicationContext context = IocUtil.context(src);
        System.out.println("容器启动完毕，准备关闭spring容器!");
        // 关闭容器
        context.close();
        System.out.println("spring容器已关闭");
    }
}
