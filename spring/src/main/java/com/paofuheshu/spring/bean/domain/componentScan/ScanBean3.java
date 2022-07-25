package com.paofuheshu.spring.bean.domain.componentScan;

import com.paofuheshu.spring.bean.domain.componentScan.test3.ScanClass;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 19:21
 * @des
 */
@ComponentScan(basePackageClasses = ScanClass.class)
public class ScanBean3 {
}
