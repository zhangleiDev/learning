package com.zle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String email;

}
