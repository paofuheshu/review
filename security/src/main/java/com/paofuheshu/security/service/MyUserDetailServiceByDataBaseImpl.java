package com.paofuheshu.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.paofuheshu.security.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/6/27 21:06
 * @des
 */
@Service
public class MyUserDetailServiceByDataBaseImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户注入
        QueryWrapper<com.paofuheshu.security.domain.User> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        com.paofuheshu.security.domain.User user = userMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 此处配置角色名称时需要带上前缀ROLE_
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        return new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), auths);
    }
}
