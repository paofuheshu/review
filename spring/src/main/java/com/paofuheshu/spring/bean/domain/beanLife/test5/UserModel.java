package com.paofuheshu.spring.bean.domain.beanLife.test5;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 15:52
 * @des
 */
public class UserModel {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
