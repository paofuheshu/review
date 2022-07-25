package com.paofuheshu.spring.bean.domain.value.test3;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/25 18:47
 * @des
 */
@Component
@MyScope
public class User {

    private String username;

    public User() {
        System.out.println("---------创建User对象" + this);
        this.username = UUID.randomUUID().toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
