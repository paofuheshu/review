package com.paofuheshu.spring.bean.domain.importdemo.test3;

import com.paofuheshu.spring.bean.domain.importdemo.test3.module1.ComponentScanModule1;
import com.paofuheshu.spring.bean.domain.importdemo.test3.module2.ComponentScanModule2;
import org.springframework.context.annotation.Import;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 22:06
 * @des
 */
@Import({ComponentScanModule1.class, ComponentScanModule2.class})
public class MainConfig3 {
}
