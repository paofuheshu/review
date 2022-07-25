package com.paofuheshu.spring.bean.domain.primary;

import lombok.Data;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/11 21:22
 * @des
 */
@Data
public class PrimaryNormalBean {

    public interface IService {

    }

    public static class ServiceA implements IService {

    }

    public static class ServiceB implements IService {

    }


}
