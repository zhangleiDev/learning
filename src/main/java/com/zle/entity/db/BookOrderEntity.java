package com.zle.entity.db;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookOrderEntity {
    /**
     * id
     *   主键
     */
    private Integer id;

    /**
     * uid
     *   用户id
     */
    private Integer uid;

    /**
     * bid
     *   书籍id
     */
    private Integer bid;

    /**
     * date
     *   订阅日期
     */
    private Date date;
}