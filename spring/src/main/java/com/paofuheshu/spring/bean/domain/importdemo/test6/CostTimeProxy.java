package com.paofuheshu.spring.bean.domain.importdemo.test6;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:02
 * @des
 */
public class CostTimeProxy implements MethodInterceptor {

    private Object target;

    public CostTimeProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, objects);
        long endTime = System.nanoTime(); System.out.println(method + "，耗时(纳秒)：" + (endTime - startTime));
        return result;
    }

    public static <T> T createProxy(T target) {
        CostTimeProxy costTimeProxy = new CostTimeProxy(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(costTimeProxy);
        return (T) enhancer.create();
    }
}
