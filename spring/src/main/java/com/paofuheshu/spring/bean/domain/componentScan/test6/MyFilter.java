package com.paofuheshu.spring.bean.domain.componentScan.test6;

import com.paofuheshu.spring.bean.domain.componentScan.test5.IService;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 19:46
 * @des
 */
public class MyFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        Class curClass = null;
        try {
            curClass = Class.forName(metadataReader.getClassMetadata().getClassName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return MyFilterService.class.isAssignableFrom(curClass);
    }
}
