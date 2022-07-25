package com.paofuheshu.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.paofuheshu.security.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author paofuheshu
 * @version 1.0
 * @date 2022/6/27 21:04
 * @des
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
