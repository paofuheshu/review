package com.paofuheshu.spring.bean.test.proxy;

import com.paofuheshu.spring.bean.domain.proxy.cglib.*;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/13 18:39
 * @des
 */
public class CglibTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    public static void test1() {
        // 使用Enhancer来给某个类创建代理类，步骤
        // 1.创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        // 2.通过setSuperclass来设置父类型，即需要给哪个类创建代理类
        enhancer.setSuperclass(Service1.class);
        /*3.设置回调，
        需实现org.springframework.cglib.proxy.Callback接口，
        此处使用的是org.springframework.cglib.proxy.MethodInterceptor，
        也是一个接口，实现了Callback接口，
        当调用代理对象的任何方法的时候，都会被MethodInterceptor接口的invoke方法处理
        */
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * 代理对象方法拦截器
             * @param o 代理对象
             * @param method  被代理的类的方法，即Service1中的方法
             * @param objects  调用方法传递的参数
             * @param methodProxy  方法代理对象
             * @return Object
             * @throws Throwable Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法：" + method);
                // 可以调用MethodProxy的invokeSuper调用被代理类的方法
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        // 4.获取代理对象,调用enhancer.create方法获取代理对象，这个方法返回的是Object类型的，所以需要强转一下
        Service1 proxy = (Service1) enhancer.create();
        // 5.调用代理对象的方法
        proxy.m1();
        proxy.m2();
    }

    /**
     * 虽然service2中是在m1方法中调用了m2方法  但m2方法也会被拦截处理
     * spring中的@configuration注解就是采用这种方式实现的
     */
    public static void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service2.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法：" + method);
                methodProxy.invokeSuper(o, objects);
                return null;
            }
        });
        Service2 proxy = (Service2) enhancer.create();
        proxy.m1();
    }

    /**
     * 拦截所有方法并返回固定值（FixedValue）
     * 当调用某个类的任何方法的时候，都希望返回一个固定的值，此时可以使用 FixedValue 接口
     * 输出结果：
     * 泡芙和树
     * 泡芙和树
     * 泡芙和树
     * 未被代理对象输出=======
     * 我是m1方法
     * hello:m1
     * 我是m2方法
     * hello:m2
     * 此时被代理的对象连输出代码都未被执行
     */
    public static void test3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "泡芙和树";
            }
        });
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());
        System.out.println(proxy.m2());
        System.out.println(proxy.toString());
        System.out.println("未被代理对象输出=======");
        Service3 service3 = new Service3();
        System.out.println(service3.m1());
        System.out.println(service3.m2());
    }

    /**
     * 直接放行，不做任何操作
     * Callback 接口下面有个子接口 org.springframework.cglib.proxy.NoOp ，将这个作为Callback的
     * 时候，被调用的方法会直接放行，像没有任何代理一样
     */
    public static void test4() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());
        System.out.println(proxy.m2());
    }

    /**
     * 不同的方法使用不同的拦截器（CallbackFilter）
     * 此方法对应的类为com/paofuheshu/spring/bean/domain/proxy/cglib/Service4.java
     * 该类中有四个方法 insert1 insert2 get1 get2
     * 现在有一个需求
     * 以insert开头的方法需要统计方法耗时
     * 以get开头的的方法直接返回固定字符串
     *
     * 由于需求中要对不同的方法做不同的处理，所以需要有2个Callback对象，当调用代理对象的方法
     * 的时候，具体会走哪个Callback呢，此时会通过 CallbackFilter 中的 accept 来判断，这个方法
     * 返回 callbacks数组的索引 。
     */
    public static void test5() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service4.class);
        // 创建2个Callback
        Callback[] callbacks = {
                // 这个用来拦截所有insert开头的方法
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        long starTime = System.nanoTime();
                        Object result = methodProxy.invokeSuper(o, objects);
                        long endTime = System.nanoTime();
                        System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
                        return result;
                    }
                },
                // 这个用来拦截所有get开头的方法，返回固定值的
                new FixedValue() {
                    @Override
                    public Object loadObject() throws Exception {
                        return "泡芙和树";
                    }
                }
        };
        // 调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbacks);
        /*
         * 设置过滤器CallbackFilter
         * CallbackFilter用来判断调用方法的时候使用callbacks数组中的哪个Callback来处理当前方 法
         * 返回的是callbacks数组的下标
         */
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                // 获取当前调用的方法的名称
                String name = method.getName();
                // 方法名称以insert开头，返回callbacks中的第1个Callback对象来处理当前方法，
                // 否则使用第二个Callback处理被调用的方法
                return name.startsWith("insert") ? 0 : 1;
            }
        });
        Service4 proxy = (Service4) enhancer.create();
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());
    }

    /**
     * 对test5的优化
     * cglib中有个CallbackHelper类，可以对test5的代码进行优化，
     * CallbackHelper类相当于对一些代码进行了封装，方便实现test5的需求，
     */
    public static void test6() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service4.class);
        // 创建2个Callback
        Callback costTimeCallback = (MethodInterceptor) (o, method, objects, methodProxy) -> {
            long starTime = System.nanoTime();
            Object result = methodProxy.invokeSuper(o, objects);
            long endTime = System.nanoTime();
            System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
            return result;
        };
        // 下面这个用来拦截所有get开头的方法，返回固定值的
        Callback fixedValueCallback = (FixedValue) () -> "泡芙和树";
        CallbackHelper callbackHelper = new CallbackHelper(Service4.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? costTimeCallback : fixedValueCallback;
            }
        };
        enhancer.setCallbacks(callbackHelper.getCallbacks());
        enhancer.setCallbackFilter(callbackHelper);
        Service4 proxy = (Service4) enhancer.create();
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());
    }

    /**
     * 实现通用的统计任意类方法耗时代理类
     */
    public static void test7() {
        // 创建Service1代理
        Service1 service1 = CostTimeProxy.createProxy(new Service1());
        service1.m1();

        // 创建Service3代理
        Service3 service3 = CostTimeProxy.createProxy(new Service3());
        service3.m1();
    }
}
