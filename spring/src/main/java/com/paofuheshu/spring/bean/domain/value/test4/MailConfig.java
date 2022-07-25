package com.paofuheshu.spring.bean.domain.value.test4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 18:59
 * @des
 */
@Component
@RefreshScope
public class MailConfig {

    @Value("${mail.username}")
    private String username;

    @Override
    public String toString() {
        return "MailConfig{" +
                "username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
