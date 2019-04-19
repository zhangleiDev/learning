package com.zle.dao;

import org.apache.ibatis.jdbc.SQL;

public class UserDaoSql {
    public static void main(String[] args) {
        UserDaoSql sql = new UserDaoSql();
        System.out.println(sql.queryId());
    }

    public String queryId(){

        return new SQL(){
            {
                SELECT ("*");
                FROM ("user");
                WHERE ("id= #{id}");
            }
            {
                System.out.println("这是啥?");
            }

        }.toString();
    }
}
