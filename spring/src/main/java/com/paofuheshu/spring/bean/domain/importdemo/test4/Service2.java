package com.paofuheshu.spring.bean.domain.importdemo.test4;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 22:18
 * @des
 */
public class Service2 {

    private Service1 service1;

    public Service1 getService1() {
        return service1;
    }

    public void setService1(Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public String toString() {
        return "Service2{" +
                "service1=" + service1 +
                '}';
    }
}
