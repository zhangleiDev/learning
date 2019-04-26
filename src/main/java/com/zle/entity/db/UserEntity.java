package com.zle.entity.db;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    /**
     * id
     *   主键ID
     */
    private Integer id;

    /**
     * name
     *   姓名
     */
    private String name;

    /**
     * age
     *   年龄
     */
    private Integer age;

    /**
     * email
     *   邮箱
     */
    private String email;

    /**
     * date
     *   日期yyyymmdd
     */
    private Date date;

    /**
     * date_time
     */
    private Date dateTime;

    /**
     * stamp
     *   时间戳
     */
    private Date stamp;
}