package com.paofuheshu.spring.bean.domain.scope_dependson_importResource_lazy.test1;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/23 14:01
 * @des
 */
@Configurable
public class MainConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ServiceA serviceA() {
        return new ServiceA();
    }
}
