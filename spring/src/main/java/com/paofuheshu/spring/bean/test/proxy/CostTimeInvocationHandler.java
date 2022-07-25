package com.paofuheshu.spring.bean.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/12 21:55
 * @des
 */
public class CostTimeInvocationHandler implements InvocationHandler {

    private Object target;

    public CostTimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long starTime = System.nanoTime();
        Object result = method.invoke(this.target, args);
        long endTime = System.nanoTime();
        long between = endTime - starTime;
        System.out.println(this.target.getClass() + "." + method.getName() + "()方法耗时(纳秒):" + between + "耗时(毫秒)：" + between / 1000000);
        return result;
    }

    /**
     * 用来创建targetInterface接口的代理对象
     * @param target  需要被代理的对象
     * @param targetInterface  被代理的接口
     * @param <T>
     * @return
     */
    public static <T> T createProxy(Object target, Class<T> targetInterface) {
        if (!targetInterface.isInterface()) {
            throw new IllegalStateException("targetInterface必须是接口类型!");
        } else if (!targetInterface.isAssignableFrom(target.getClass())) {
            throw new IllegalStateException("target必须是targetInterface接口的实现类!");
        }
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new CostTimeInvocationHandler(target));
    }

}
