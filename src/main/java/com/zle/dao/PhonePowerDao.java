package com.zle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zle.entity.db.PhonePowerEntity;
import com.zle.entity.db.PhonePowerEntityExample;
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
public interface PhonePowerDao extends BaseMapper {
    long countByExample(PhonePowerEntityExample example);

    int deleteByExample(PhonePowerEntityExample example);

    @Delete({
        "delete from phone_power",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into phone_power (power, create_Time, ",
        "uid)",
        "values (#{power,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{uid,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(PhonePowerEntity record);

    int insertSelective(PhonePowerEntity record);

    List<PhonePowerEntity> selectByExample(PhonePowerEntityExample example);

    @Select({
        "select",
        "id, power, create_Time, uid",
        "from phone_power",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.zle.dao.PhonePowerDao.BaseResultMap")
    PhonePowerEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhonePowerEntity record, @Param("example") PhonePowerEntityExample example);

    int updateByExample(@Param("record") PhonePowerEntity record, @Param("example") PhonePowerEntityExample example);

    int updateByPrimaryKeySelective(PhonePowerEntity record);

    @Update({
        "update phone_power",
        "set power = #{power,jdbcType=VARCHAR},",
          "create_Time = #{createTime,jdbcType=TIMESTAMP},",
          "uid = #{uid,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PhonePowerEntity record);
}