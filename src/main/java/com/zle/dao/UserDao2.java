package com.zle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zle.entity.User;
import com.zle.entity.User2;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserDao2 extends BaseMapper<User> {

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

    /**
     * 通过 useGeneratedKeys
     * 不指定时,默认主键字段为id
     * @param user
     * @return
     */
    @Insert({"insert into user(name,age,email)",
                " values(#{name},#{age},#{email})",
        })
    @Options(useGeneratedKeys = true)
    public int insert2(User user);

    /**
     * 用SELECT LAST_INSERT_ID() 方式 获取新增主键
     * @param user
     * @return
     */
    @Insert({"insert into user(name,age,email)",
                " values(#{name},#{age},#{email})",
        })
    @SelectKey(statement ="SELECT LAST_INSERT_ID()",
            keyProperty ="id",
            resultType = Integer.class ,
            before = false)
    public int insert3(User user);

    /**
     * 类中自定义sql
     * @param id
     * @return
     */
    @SelectProvider(type = UserDaoSql.class,method = "queryId")
    public User2 queryId2(int id);

    public int batchInsert(List<User> users);

    public List<User> testSelect2(Map users);

    public List<User> testSelect3(User user);

    public List<User> testSelect4(User user);

}
