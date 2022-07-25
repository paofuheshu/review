package com.paofuheshu.spring.bean.domain.importdemo.test2;

import org.springframework.context.annotation.Import;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 21:59
 * @des 通过Import来汇总多个@Configuration标注的配置类
 */
@Import({ConfigModule1.class, ConfigModule2.class})
public class MainConfig2 {
}
