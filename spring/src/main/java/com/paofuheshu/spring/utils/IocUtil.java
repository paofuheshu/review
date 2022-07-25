package com.paofuheshu.spring.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/6 20:39
 * @des
 */
public class IocUtil {

    public static ClassPathXmlApplicationContext context(String beanXml) {
        return new ClassPathXmlApplicationContext(beanXml); }
}
