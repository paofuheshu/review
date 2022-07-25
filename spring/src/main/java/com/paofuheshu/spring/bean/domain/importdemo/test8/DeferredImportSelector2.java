package com.paofuheshu.spring.bean.domain.importdemo.test8;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:25
 * @des
 */
public class DeferredImportSelector2 implements DeferredImportSelector, Ordered {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {Configuration2.class.getName()};
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
