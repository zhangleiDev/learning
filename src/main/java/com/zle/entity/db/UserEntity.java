package com.zle.entity.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -4033590196742367785L;
    List<BookEntity> books;
    BookEntity book;
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