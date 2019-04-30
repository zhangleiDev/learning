package com.zle.dao;

import com.zle.entity.db.BookOrderEntity;
import com.zle.entity.db.BookOrderEntityExample;
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
public interface BookOrderDao {
    long countByExample(BookOrderEntityExample example);

    int deleteByExample(BookOrderEntityExample example);

    @Delete({
        "delete from book_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into book_order (uid, bid, ",
        "date)",
        "values (#{uid,jdbcType=INTEGER}, #{bid,jdbcType=INTEGER}, ",
        "#{date,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(BookOrderEntity record);

    int insertSelective(BookOrderEntity record);

    List<BookOrderEntity> selectByExample(BookOrderEntityExample example);

    @Select({
        "select",
        "id, uid, bid, date",
        "from book_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.zle.dao.BookOrderDao.BaseResultMap")
    BookOrderEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookOrderEntity record, @Param("example") BookOrderEntityExample example);

    int updateByExample(@Param("record") BookOrderEntity record, @Param("example") BookOrderEntityExample example);

    int updateByPrimaryKeySelective(BookOrderEntity record);

    @Update({
        "update book_order",
        "set uid = #{uid,jdbcType=INTEGER},",
          "bid = #{bid,jdbcType=INTEGER},",
          "date = #{date,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BookOrderEntity record);
}