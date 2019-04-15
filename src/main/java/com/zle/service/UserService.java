package com.zle.service;

import com.zle.dao.UserDao;
import com.zle.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        mapper.queryAll().forEach(a-> System.out.println(a));
        sqlSession.close();
    }

}
