package com.paofuheshu.spring.bean.domain.beanLife.test1;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 15:03
 * @des
 */
@Data
public class CompositeObj {

    private String name;
    private Integer salary;
    private Car car1;
    private List<String> stringList;
    private List<Car> carList;
    private Set<String> stringSet;
    private Set<Car> carSet;
    private Map<String, String> stringMap;
    private Map<String, Car> stringCarMap;
}
