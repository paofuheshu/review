package com.paofuheshu.spring.bean.domain.di;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/6 20:39
 * @des
 */
public class UserModel {

    private String name;

    private int age;

    private String desc;

    private Bean3 bean3;

    private Bean4 bean4;

    public UserModel() {

    }

    public UserModel(Bean3 bean3, Bean4 bean4) {
        this.bean3 = bean3;
        this.bean4 = bean4;
    }

    public UserModel(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public UserModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserModel(String name, int age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public Bean3 getBean3() {
        return bean3;
    }

    public void setBean3(Bean3 bean3) {
        this.bean3 = bean3;
    }

    public Bean4 getBean4() {
        return bean4;
    }

    public void setBean4(Bean4 bean4) {
        this.bean4 = bean4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", desc='" + desc + '\'' +
                ", bean3=" + bean3 +
                ", bean4=" + bean4 +
                '}';
    }
}
