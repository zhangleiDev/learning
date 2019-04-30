package com.zle.entity.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@ToString
@Getter
@Setter
public class BookEntity {

    /**
     * id
     *   主键
     */
    private Integer id;

    /**
     * name
     *   书名
     */
    private String name;

    /**
     * price
     *   价格
     */
    private String price;
}