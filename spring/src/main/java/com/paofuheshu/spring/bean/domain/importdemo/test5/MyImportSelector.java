package com.paofuheshu.spring.bean.domain.importdemo.test5;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 19:52
 * @des
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Service1.class.getName(), Module1Config.class.getName()};
    }
}
