package com.paofuheshu.spring.bean.domain.internationalization;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 21:58
 * @des
 */
@Configuration
public class MainConfig3 {

    @Bean
    public MessageSource messageSource() {
        return new MessageSourceFromDb();
    }
}
