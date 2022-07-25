package com.paofuheshu.spring.bean.test.methodbean;

import com.paofuheshu.spring.bean.domain.methodbean.normal.ServiceA;
import com.paofuheshu.spring.bean.domain.methodbean.normal.ServiceB;
import com.paofuheshu.spring.utils.IocUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 19:09
 * @des
 * 1. lookup-method：方法查找，可以对指定的bean的方法进行拦截，然后从容器中查找指定的
 * bean作为被拦截方法的返回值
 * 2. replaced-method：方法替换，可以实现bean方法替换的效果，整体来说比lookup-method更
 * 灵活一些
 */
public class MethodBeanTest {

    public static void main(String[] args) {
//        normalTest();
//        applicationContextAware();
//        lookupMethod();
        replaceMethod();
    }

    /**
     * 在xml配置文件中配置了serviceA为多例的
     * 所以下面的前两行输出是不同的对象
     * 而serviceB是默认为单例的，serviceB中的serviceA会在容器创建serviceB的时候，从容器中获取一个serviceA将
     * 其注入到serviceB中，所以自始至终serviceB中的serviceA都是同一个对象。
     * 如果我们希望beanB中每次使用beanA的时候beanA都是一个新的实例，
     * 我们可以在serviceB中加个方法去获取serviceA，这个方法中我们主动去容器中获取serviceA，那么每
     * 次获取到的都是不同的serviceA实例。
     */
    public static void normalTest() {
        ClassPathXmlApplicationContext context = IocUtil.context("methodbean/normalBean.xml");
        System.out.println(context.getBean(ServiceA.class));
        System.out.println(context.getBean(ServiceA.class));
        System.out.println("serviceB中的serviceA");
        ServiceB serviceB = context.getBean(ServiceB.class);
        System.out.println(serviceB.getServiceA());
        System.out.println(serviceB.getServiceA());
    }

    /**
     * 此时通过重写接口给serviceB设置bean：serviceA 在serviceB中获取到的serviceA也是多例的
     */
    public static void applicationContextAware() {
        ClassPathXmlApplicationContext context = IocUtil.context("methodbean/applicationContextAware.xml");
        System.out.println(context.getBean(com.paofuheshu.spring.bean.domain.methodbean.applicationcontextaware.ServiceA.class));
        System.out.println(context.getBean(com.paofuheshu.spring.bean.domain.methodbean.applicationcontextaware.ServiceA.class));
        System.out.println("serviceB中的serviceA");
        com.paofuheshu.spring.bean.domain.methodbean.applicationcontextaware.ServiceB serviceB =
                context.getBean(com.paofuheshu.spring.bean.domain.methodbean.applicationcontextaware.ServiceB.class);
        System.out.println(serviceB.getServiceA());
        System.out.println(serviceB.getServiceA());
        serviceB.say();
        serviceB.say();
    }

    /**
     * 注意最后2行的输出，serviceA是调用this.getServiceA()方法获取 ，代码中这个方法返回的是null，但是在配置文件中配置了lookup-method
     * spring内部对这个方法进行了拦截，每次调用这个方法的时候，都会去容器中查找serviceA，然后返
     * 回，所以上面最后2行的输出中serviceA是有值的，并且是不同的serviceA实例。
     *
     * lookup-method：看其名字，就知道意思：方法查找，调用name属性指定的方法的时候，spring会
     * 对这个方法进行拦截，然后去容器中查找lookup-method元素中bean属性指定的bean，然后将找到
     * 的bean作为方法的返回值返回。
     *
     * spring提供的还有一个功能，同样可以可以解决上面单例bean中用到多例bean的问题，也就是下一个方法replacedMethod
     *
     * 这个地方底层是使用cglib代理实现的
     */
    public static void lookupMethod() {
        ClassPathXmlApplicationContext context = IocUtil.context("methodbean/lookupmethod.xml");
        System.out.println(context.getBean(com.paofuheshu.spring.bean.domain.methodbean.lookup.ServiceA.class));
        System.out.println(context.getBean(com.paofuheshu.spring.bean.domain.methodbean.lookup.ServiceA.class));
        System.out.println("serviceB中的serviceA");
        com.paofuheshu.spring.bean.domain.methodbean.lookup.ServiceB serviceB =
                context.getBean(com.paofuheshu.spring.bean.domain.methodbean.lookup.ServiceB.class);
        System.out.println(serviceB.getServiceA());
        System.out.println(serviceB.getServiceA());
        serviceB.say();
        serviceB.say();
    }

    /**
     * replaced-method：方法替换，比如我们要调用serviceB中的getServiceA的时候，我们可以对serviceB
     * 这个bean中的getServiceA方法进行拦截，把这个调用请求转发到一个替换者处理。这就是replaced-method可以实现的功能，比lookup-method更强大更灵活
     * replaced-method的使用3个步骤
     * 1: 定义替换者  自定义一个替换者，替换者需要实现spring中的MethodReplacer接口，包路径为：org.springframework.beans.factory.support
     * 2: 定义替换者bean
     * 3: 通过replaced-method元素配置目标bean需要被替换的方法
     */
    public static void replaceMethod() {
        ClassPathXmlApplicationContext context = IocUtil.context("methodbean/replacemethod.xml");
        System.out.println(context.getBean(com.paofuheshu.spring.bean.domain.methodbean.replcaemethod.ServiceA.class));
        System.out.println(context.getBean(com.paofuheshu.spring.bean.domain.methodbean.replcaemethod.ServiceA.class));
        System.out.println("serviceB中的serviceA");
        com.paofuheshu.spring.bean.domain.methodbean.replcaemethod.ServiceB serviceB =
                context.getBean(com.paofuheshu.spring.bean.domain.methodbean.replcaemethod.ServiceB.class);
        System.out.println(serviceB.getServiceA());
        System.out.println(serviceB.getServiceA());
        serviceB.say();
        serviceB.say();
    }
}
