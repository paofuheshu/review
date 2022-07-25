package com.paofuheshu.spring.bean.domain.beanLife.test8;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/24 17:13
 * @des
 */
@Configurable
public class MainConfig {

    @Bean(destroyMethod = "customDestroyMethod")
    public ServiceC serviceC() { return new ServiceC(); }
}
