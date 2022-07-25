package com.paofuheshu.spring.bean.domain.beanLife.test1;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 14:40
 * @des
 */
public class Car {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}
