package com.paofuheshu.spring.bean.domain.beanLife.test7;

import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 16:41
 * @des
 */
@Component
public class Service1 {

    public Service1() { System.out.println("create " + this.getClass()); }
}
