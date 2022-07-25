package com.paofuheshu.spring.bean.domain.componentScan.test5;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 19:41
 * @des 被扫描的类满足 IService.class.isAssignableFrom(被扫描的类) 条件的都会被注册到spring容器中
 */
@ComponentScan(useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = IService.class)
})
public class ScanBean5 {
}
