package com.paofuheshu.spring.bean.domain.beanfactory;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/4 21:20
 * @des   @Lazy注解 懒加载注入bean 只有当使用到这个bean时才去创建注入
 * 下面的static静态块中的代码如果使用了@Lazy注解时，在项目启动成功的时候不会执行
 * 在BeanFactoryTest的test1方法中使用bean了  此时会执行代码  代表懒加载成功
 * 如果不使用的话  在项目启动时，spring就会把bean2注入到容器中  此时静态代码块会执行
 */
@Component
@Lazy
public class Bean2 {

    static {
        System.out.println("我是bean2,现在开始加载");
    }

    public void test() {
        System.out.println("bean2方法开始执行");
    }
}
