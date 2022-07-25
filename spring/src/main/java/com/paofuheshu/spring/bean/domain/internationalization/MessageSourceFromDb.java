package com.paofuheshu.spring.bean.domain.internationalization;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 21:57
 * @des
 */
public class MessageSourceFromDb extends StaticMessageSource implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        // 此处我们在当前bean初始化之后，模拟从db中获取国际化信息，然后调用addMessage来配置国 际化信息
        this.addMessage("desc", Locale.CHINA, "我是从db来的信息");
        this.addMessage("desc", Locale.UK, "MessageSource From Db");
    }
}
