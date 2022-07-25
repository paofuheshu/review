package com.paofuheshu.spring.bean.domain.internationalization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 21:32
 * @des
 */
@Configuration
public class MainConfig2 {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        // 可以指定国际化化配置文件的位置，格式：路径/文件名称,注意不包含【语言_国 家.properties】含这部分
        resourceBundleMessageSource.setBasename("internation/message");
        resourceBundleMessageSource.setCacheMillis(0);
        return resourceBundleMessageSource;
    }
}
