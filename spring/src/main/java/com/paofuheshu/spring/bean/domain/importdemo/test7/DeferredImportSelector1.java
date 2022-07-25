package com.paofuheshu.spring.bean.domain.importdemo.test7;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:19
 * @des
 */
public class DeferredImportSelector1 implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Configuration2.class.getName()};
    }
}
