package com.paofuheshu.spring.bean.domain.conditional.test2;

import com.paofuheshu.spring.bean.domain.conditional.test1.MyCondition1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/19 20:57
 * @des
 */
@Configuration
public class MainConfig2 {

    @Conditional(MyCondition1.class)
    @Bean
    public String name() { return "泡芙和树"; }

    @Bean
    public String address() { return "池州市"; }
}
