package com.paofuheshu.spring.bean.domain.value.test1;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 18:14
 * @des
 */
@Configurable
@ComponentScan
@PropertySource({"classpath:db.properties"})
public class MainConfig1 {
}
