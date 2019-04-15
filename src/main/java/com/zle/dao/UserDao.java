package com.zle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zle.entity.User;
import lombok.Data;

import java.util.List;

public interface UserDao extends BaseMapper<User> {

public List<User> queryAll();

}
