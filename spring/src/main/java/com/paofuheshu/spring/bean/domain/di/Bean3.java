package com.paofuheshu.spring.bean.domain.di;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/6 21:09
 * @des
 */
public class Bean3 {

    private String name;

    public Bean3() {
    }

    public Bean3(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean3{" +
                "name='" + name + '\'' +
                '}';
    }
}
