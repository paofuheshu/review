package com.paofuheshu.spring.bean.domain.importdemo.test8;

import org.springframework.context.annotation.Import;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:26
 * @des
 */
@Import({
        DeferredImportSelector2.class,
        DeferredImportSelector1.class
})
public class MainConfig8 {
}
