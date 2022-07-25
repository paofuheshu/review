package com.paofuheshu.spring.bean.domain.importdemo.test1;

import org.springframework.context.annotation.Import;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 21:47
 * @des
 */
@Import(value = {Service1.class, Service2.class})
public class MainConfig1 {
}
