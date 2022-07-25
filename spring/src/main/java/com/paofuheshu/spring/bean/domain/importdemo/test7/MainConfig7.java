package com.paofuheshu.spring.bean.domain.importdemo.test7;

import org.springframework.context.annotation.Import;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:19
 * @des
 * 此时@Import中被导入类的顺序：
 * DeferredImportSelector1->Configuration3->ImportSelector1
 */
@Import({
        DeferredImportSelector1.class,
        Configuration3.class,
        ImportSelector1.class,
})
public class MainConfig7 {
}
