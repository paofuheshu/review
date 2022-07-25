package com.paofuheshu.spring.bean.test.proxy;

import com.paofuheshu.spring.bean.domain.proxy.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 21:27
 * @des
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
//        test1();
//        serviceProxy();
//        jdkProxyMethod1();
//        jdkProxyMethod2();
//        costTimeProxy();
        test();
    }

    public static void test1() {
        IService serviceA = new ServiceA();
        IService serviceB = new ServiceB();
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();
        System.out.println("============");
        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }

    /**
     * 增加代理类，统计接口的耗时时间
     * 我们没有去修改ServiceA和ServiceB中的方法，只是给IService接口创建了一个代理类，通过
     * 代理类去访问目标对象，需要添加的一些共有的功能都放在代理中，当有其他需求的时候，我们只
     * 需修改ServiceProxy的代码，方便系统的扩展和测试。
     */
    public static void serviceProxy() {
        IService serviceA = new ServiceProxy(new ServiceA());
        IService serviceB = new ServiceProxy(new ServiceB());
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();
        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }

    /**
     * 步骤
     * 1.调用Proxy.getProxyClass方法获取代理类的Class对象
     * 2.使用InvocationHandler接口创建代理类的处理器
     * 3.通过代理类和InvocationHandler创建代理对象
     * 4.上面已经创建好代理对象了，接着我们就可以使用代理对象了
     * @throws Exception
     */
    public static void jdkProxyMethod1() throws Exception {
        // 1:获取接口对应的代理类
        Class<IService> proxyClass = (Class<IService>) Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
        // 2. 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };
        // 3. 创建代理实例
        IService proxyService = proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
        // 4. 调用代理的方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }

    /**
     * 步骤
     * 1.使用InvocationHandler接口创建代理类的处理器
     * 2.使用Proxy类的静态方法newProxyInstance直接创建代理对象
     * 3.使用代理对象
     */
    public static void jdkProxyMethod2() {
        // 1. 创建代理类的处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };
        // 2. 创建代理实例
        IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, invocationHandler);
        // 3. 调用代理的方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }

    public static void costTimeProxy() {
        IService serviceA = CostTimeInvocationHandler.createProxy(new ServiceA(), IService.class);
        IService serviceB = CostTimeInvocationHandler.createProxy(new ServiceB(), IService.class);
        serviceA.m1();
        serviceA.m2();
        serviceA.m3();
        serviceB.m1();
        serviceB.m2();
        serviceB.m3();
    }

    public static void test() {
        TestService proxy = CostTimeInvocationHandler.createProxy(new TestServiceImpl(), TestService.class);
        proxy.test();
    }
}
