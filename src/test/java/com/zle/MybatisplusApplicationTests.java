package com.zle;

import com.zle.dao.UserDao;
import com.zle.entity.User;
import com.zle.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @Test
    public void testQueryAll2(){
        System.out.println(userDao.queryId(1));
       System.out.println(userDao.queryAll2());
    }

    @Test
    public void testQueryId(){

        System.out.println(userDao.queryId(1));
    }

    /**
     * sql session中获取执行对象2
     */
    @Test
    public void testSession2(){
        userService.testSession2();
        System.out.println("Session2 end");
    }
    /**
     * sql session中获取执行对象1
     */
    @Test
    public void testSession(){
        userService.testSession();
        System.out.println("end");
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userDao.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
    @Test
    public void testSelectAll() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userDao.queryAll();
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    /**
     * 获取新增主键方式1
     */
    @Test
    public void insertWithGeneratedKeys(){
        User user = new User();
        user.setAge(10);
        user.setEmail("fw@r23.cn");
        user.setName("haha");
        userDao.insertWithGeneratedKeys(user);
        //userDao.insert(user);
        System.out.println("id is" + user.getId());
    }
    /**
     * 获取新增主键方式2
     */
    @Test
    public void insertWithSelectKey(){
        User user = new User();
        user.setAge(10);
        user.setEmail("fw@r23.cn");
        user.setName("haha");
        userDao.insertWithSelectKey(user);
        //userDao.insert(user);
        System.out.println("id is" + user.getId());
    }

}

