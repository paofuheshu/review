package com.paofuheshu.spring.bean.domain.beanLife.test4;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 15:24
 * @des
 */
public class Person {

    private String name;

    private Integer age;

    public Person() {
        System.out.println("调用 Person()");
    }

    @MyAutowired
    public Person(String name) {
        System.out.println("调用 Person(String name)");
        this.name = name;
    }

    public Person(String name, Integer age) {
        System.out.println("调用 Person(String name, Integer age)");
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
