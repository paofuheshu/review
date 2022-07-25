package com.paofuheshu.spring.bean.domain.configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 18:33
 * @des
 */
public class ServiceB {

    private ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    @Override
    public String toString() {
        return "ServiceB{" +
                "serviceA=" + serviceA +
                '}';
    }
}
