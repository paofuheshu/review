package com.paofuheshu.spring.bean.domain.componentScan;

import com.paofuheshu.spring.bean.domain.componentScan.test4.MyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 19:30
 * @des 面指定了Filter的type为注解的类型，只要类上面有 @MyBean 注解的，都会被作为bean注册到容器中。
 */
@ComponentScan(includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyBean.class)
}, value = "com.paofuheshu.spring.bean.domain.componentScan.test4",
useDefaultFilters = false)
public class ScanBean4 {
}
