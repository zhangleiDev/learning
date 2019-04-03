package com.zle.controller;

import lombok.Data;

@Data
public class ResObject {
    private int code;
    private Object msg;

    public ResObject(int code, Object msg) {
        this.code = code;
        this.msg = msg;
    }
}
