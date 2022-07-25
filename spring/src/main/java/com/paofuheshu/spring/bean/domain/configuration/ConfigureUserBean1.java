package com.paofuheshu.spring.bean.domain.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/7/18 18:21
 * @des
 */
public class ConfigureUserBean1 {

    /**
     * bean名称为方法默认值：user1
     * @return User
     */
    @Bean
    public User user1() {
        return new User();
    }

    /**
     * bean名称通过value指定了：user2Bean
     * @return  User
     */
    @Bean("user2Bean")
    public User user2() {
        return new User();
    }

    /**
     * bean名称为：user3Bean，2个别名：[user3BeanAlias1,user3BeanAlias2]
     * @return  User
     */
    @Bean({"user3Bean", "user3BeanAlias1", "user3BeanAlias2"})
    public User user3() {
        return new User();
    }

    public void test() {
        System.out.println(user1());
    }
}
