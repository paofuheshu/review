package com.paofuheshu.spring.bean.domain.annotationinjection.test15;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 13:35
 * @des
 */
public class Service3 {

    private Service1 service1;

    private Service2 service2;

    public Service1 getService1() {
        return service1;
    }

    public void setService1(Service1 service1) {
        this.service1 = service1;
    }

    public Service2 getService2() {
        return service2;
    }

    public void setService2(Service2 service2) {
        this.service2 = service2;
    }

    @Override
    public String toString() {
        return "Service3{" +
                "service1=" + service1 +
                ", service2=" + service2 +
                '}';
    }
}
