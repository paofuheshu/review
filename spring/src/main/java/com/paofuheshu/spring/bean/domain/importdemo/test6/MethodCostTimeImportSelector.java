package com.paofuheshu.spring.bean.domain.importdemo.test6;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:08
 * @des
 */
public class MethodCostTimeImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                MethodCostTimeProxyBeanPostProcessor.class.getName()
        };
    }
}
