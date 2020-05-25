package com.zle.entity.db;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("phone_power")
public class PhonePowerEntity {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * power
     *   电量
     */
    @TableField(value = "power")
    private String power;

    /**
     * create_Time
     *   创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * uid
     *   用户标识
     */
    @TableField(value = "uid")
    private String uid;
}