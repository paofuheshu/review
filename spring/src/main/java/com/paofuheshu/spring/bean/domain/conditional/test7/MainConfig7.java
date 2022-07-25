package com.paofuheshu.spring.bean.domain.conditional.test7;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 21:31
 * @des
 */
@Configuration
@Import({BeanConfig1.class, BeanConfig2.class})
public class MainConfig7 {
}
