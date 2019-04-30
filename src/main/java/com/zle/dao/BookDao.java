package com.zle.dao;

import com.zle.entity.db.BookEntity;
import com.zle.entity.db.BookEntityExample;
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
public interface BookDao {

    long countByExample(BookEntityExample example);

    int deleteByExample(BookEntityExample example);

    @Delete({
        "delete from book",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into book (name, price)",
        "values (#{name,jdbcType=VARCHAR}, #{price,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(BookEntity record);

    int insertSelective(BookEntity record);

    List<BookEntity> selectByExample(BookEntityExample example);

    @Select({
        "select",
        "id, name, price",
        "from book",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.zle.dao.BookDao.BaseResultMap")
    BookEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookEntity record, @Param("example") BookEntityExample example);

    int updateByExample(@Param("record") BookEntity record, @Param("example") BookEntityExample example);

    int updateByPrimaryKeySelective(BookEntity record);

    @Update({
        "update book",
        "set name = #{name,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BookEntity record);

}