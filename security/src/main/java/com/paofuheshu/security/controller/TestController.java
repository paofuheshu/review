package com.paofuheshu.security.controller;

import cn.hutool.core.collection.CollUtil;
import com.paofuheshu.security.domain.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/6/27 18:04
 * @des
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("/index")
    public String index() {
        return "hello index";
    }

    @PostMapping("/login")
    public String login() {
        return "hello login";
    }

    /**
     * 判断是否具有角色 使用Secured注解时 配置角色名称也需要带上前缀ROLE_
     */
    @GetMapping("/update")
    @Secured({"ROLE_sale", "ROLE_manager"})
    public String update() {
        System.out.println("hello update");
        return "hello update";
    }

    @GetMapping("/save")
    @PreAuthorize("hasAnyAuthority('admins')")
    public String save() {
        System.out.println("hello save");
        return "hello save";
    }

    /**
     * PostAuthorize 注解使用并不多，在方法执行后再进行权限验证，适合验证带有返回值的权限
     * 即使用户没有该方法的权限，但方法内部的方法还是会被调用  但是不会执行返回
     */
    @GetMapping("/add")
    @PostAuthorize("hasAnyAuthority('admins11')")
    public String add() {
        System.out.println("hello add");
        return "hello add";
    }

    /**
     * 权限验证之后对数据进行过滤 留下用户名是 test 的数据
     */
    @GetMapping("/list")
    @PostFilter("filterObject.username == 'test'")
    public List<User> list() {
        return CollUtil.newArrayList(new User(1, "admin", "123"), new User(2, "test", "123"));
    }

    /**
     * 进入控制器之前对数据进行过滤  根据filterObject对传入的数据进行限制
     */
    @PostMapping("/addAll")
    @PreFilter("filterObject.id % 2 == 0")
    public List<User> addAll(@RequestBody List<User> users) {
        return users;
    }
}
