package com.zle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zle.entity.User;
import com.zle.entity.User2;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao extends BaseMapper<User> {

    public List<User> queryAll();
    public List<User> queryAll(@Param("age")Integer age,@Param("day") String day);
    public int insertWithGeneratedKeys(User user);
    public int insertWithSelectKey(User user);

    @Results(id ="aaa",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "age2",column = "age")
    })
    @Select("select * from user where id=#{id}" )
    public User2 queryId(int id);

    /**
     * queryId 先执行后此处的id:aaa 再能获取到. 不然报错
     * @return
     */
    @ResultMap("aaa")
    @Select("select * from user" )
    public List<User2> queryAll2();


}
