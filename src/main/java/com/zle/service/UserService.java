package com.zle.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zle.dao.UserDao2;
import com.zle.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserService {
    @Autowired
    SqlSessionFactory sessionFactory;

    /**
     * 直接调用mapper对象
     */
    public void testSession(){
        SqlSession sqlSession = sessionFactory.openSession();
        List<User> rows=sqlSession.selectList("com.zle.dao.UserDao.queryAll");
        rows.forEach(a-> System.out.println(a));
        sqlSession.close();
    }

    /**
     * 先获取mapper对象,然后再调用具体的方法
     */
    public void testSession2(){
        SqlSession sqlSession = sessionFactory.openSession();
        UserDao2 mapper = sqlSession.getMapper(UserDao2.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.queryAll(1,"2019-04-15 15:13:02").forEach(a-> {
            try {
                System.out.println(new ObjectMapper().writeValueAsString(a));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            //System.out.println("Date:"+sdf.format(a.getDate())+" DateTime:"+sdf.format(a.getDateTime())+" TimeStamp "+sdf.format(a.getStamp()));
        });
        sqlSession.close();
    }

}
