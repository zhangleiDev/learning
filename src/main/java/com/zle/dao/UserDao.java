package com.zle.dao;

import com.zle.entity.db.UserEntity;
import com.zle.entity.db.UserEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {
    long countByExample(UserEntityExample example);

    int deleteByExample(UserEntityExample example);

    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user (name, age, ",
        "email, date, date_time, ",
        "stamp)",
        "values (#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, ",
        "#{email,jdbcType=VARCHAR}, #{date,jdbcType=DATE}, #{dateTime,jdbcType=TIMESTAMP}, ",
        "#{stamp,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    List<UserEntity> selectByExample(UserEntityExample example);

    @Select({
        "select",
        "id, name, age, email, date, date_time, stamp",
        "from user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.zle.dao.UserDao.BaseResultMap")
    UserEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserEntity record, @Param("example") UserEntityExample example);

    int updateByExample(@Param("record") UserEntity record, @Param("example") UserEntityExample example);

    int updateByPrimaryKeySelective(UserEntity record);

    @Update({
        "update user",
        "set name = #{name,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "email = #{email,jdbcType=VARCHAR},",
          "date = #{date,jdbcType=DATE},",
          "date_time = #{dateTime,jdbcType=TIMESTAMP},",
          "stamp = #{stamp,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserEntity record);
}