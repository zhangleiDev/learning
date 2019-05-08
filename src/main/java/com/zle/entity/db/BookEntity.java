package com.zle.entity.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

//@ToString
@Getter
@Setter
public class BookEntity implements Serializable {


    private static final long serialVersionUID = 6979733651869140304L;
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