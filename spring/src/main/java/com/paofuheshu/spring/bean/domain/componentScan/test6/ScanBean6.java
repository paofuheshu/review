package com.paofuheshu.spring.bean.domain.componentScan.test6;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 19:48
 * @des
 */
@ComponentScan(useDefaultFilters = false,
includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyFilter.class)
})
public class ScanBean6 {
}
