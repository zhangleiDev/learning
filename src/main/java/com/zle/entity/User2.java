package com.zle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class User2 {

    private Integer id;
    private String name;
    private Integer age2;
    private String email;


}
