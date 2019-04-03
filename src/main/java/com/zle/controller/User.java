package com.zle.controller;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    private String userId;
    private String name;

}
