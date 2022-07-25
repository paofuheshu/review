package com.paofuheshu.spring.bean.domain.di;

import lombok.Data;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 18:41
 * @des
 */
@Data
public class DiAutowireByConstructor {

    private Service1 service1;

    private Service2 service2;

    @Data
    public static class BaseService {
        private String desc;
    }

    /**
     * Service1实现了BaseService接口
     */
    public static class Service1 extends BaseService {

    }

    /**
     * Service2实现了BaseService接口
     */
    public static class Service2 extends BaseService {

    }

    public DiAutowireByConstructor() {
    }

    public DiAutowireByConstructor(Service1 service1) {
        System.out.println("DiAutowireByConstructor(Service1 service1)");
        this.service1 = service1;
    }

    public DiAutowireByConstructor(Service2 service2) {
        System.out.println("DiAutowireByConstructor(Service2 service2)");
        this.service2 = service2;
    }

    public DiAutowireByConstructor(Service1 service1, Service2 service2) {
        System.out.println("DiAutowireByConstructor(Service1 service1, Service2 service2)");
        this.service1 = service1;
        this.service2 = service2;
    }
}
