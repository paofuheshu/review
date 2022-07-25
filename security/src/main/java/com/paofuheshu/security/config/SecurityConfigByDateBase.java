package com.paofuheshu.security.config;

import com.paofuheshu.security.service.MyUserDetailServiceByDataBaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/6/27 21:05
 * @des 在配置中使用数据库来对用户名和密码进行操作
 */
@Configuration
public class SecurityConfigByDateBase extends WebSecurityConfigurerAdapter {

    @Resource
    private MyUserDetailServiceByDataBaseImpl myUserDetailServiceByDataBase;

    /**
     * 注入数据源
     */
    @Resource
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 此处可以帮助我们自动生成表，因为已经提前创建好了  所以此处设置为false
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailServiceByDataBase).passwordEncoder(password());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置没有权限访问跳转到自定义页面
        http.exceptionHandling().accessDeniedPage("/unAuth.html");
        // 配置退出的路径
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();
        http.formLogin()
                // 自定义登陆页面设置
                .loginPage("/login.html")
                // 登陆访问路径
                .loginProcessingUrl("/test/login")
                // 登录成功之后，跳转路径
                .defaultSuccessUrl("/index.html")
                .permitAll()
                .and().authorizeRequests()
                // 设置部分路径可以直接访问
                .antMatchers("/", "/test/hello", "/test/login", "/test/addAll").permitAll()
                // 当前登录用户只有拥有admins这个权限才可以访问这个路径
//                .antMatchers("/test/index").hasAuthority("admins")
                // 当前登录用户只要拥有其中一个这个权限就可以访问这个路径
                // 此处配置多个权限的时候，以逗号直接分割不起作用，查看源码 此版本的源码是以','进行字符串的分割
                // "admins,manager"这种形式没效果  "admins","manager"这种形式可以
                .antMatchers("/test/index").hasAnyAuthority("admins","manager")
                // 用户角色才能访问
                // 查看源码可以得知  源码会自动加上一个前缀ROLE_  所以在给用户赋予角色时我们需要带上前缀
//                .antMatchers("/test/index").hasRole("sale")
//                .antMatchers("/test/index").hasAnyRole("sale", "test")
                .anyRequest().authenticated()
                // 设置记住我的相关配置
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                // 设置token的有效时长  以秒为单位
                .tokenValiditySeconds(60)
                .userDetailsService(myUserDetailServiceByDataBase)
                // 关闭csrf防护
                .and().csrf().disable();
    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }
}
