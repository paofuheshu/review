package com.paofuheshu.spring.bean.domain.componentScan.test7;

import com.paofuheshu.spring.bean.domain.componentScan.test4.MyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 19:51
 * @des
 */
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyBean.class)
})
public class ScanBean7 {
}
