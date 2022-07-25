package com.paofuheshu.spring.bean.domain.di;

import lombok.Data;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 18:54
 * @des
 */
@Data
public class DiAutowireByDefault {

    private Service1 service1;

    private Service2 service2;

    @Data
    public static class Service1 {
        private String desc;
    }

    @Data
    public static class Service2 {
        private String desc;
    }
}
