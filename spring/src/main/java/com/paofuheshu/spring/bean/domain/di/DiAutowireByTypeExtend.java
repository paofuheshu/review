package com.paofuheshu.spring.bean.domain.di;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 18:35
 * @des
 */
@Data
public class DiAutowireByTypeExtend {

    private List<IService1> serviceList;
    private List<BaseService> baseServiceList;
    private Map<String, IService1> service1Map;
    private Map<String, BaseService> baseServiceMap;

    public interface IService1 {

    }

    @Data
    public static class BaseService {
        private String desc;
    }

    public static class Service1 extends BaseService implements IService1 {

    }

    public static class Service2 extends BaseService implements IService1 {

    }
}
