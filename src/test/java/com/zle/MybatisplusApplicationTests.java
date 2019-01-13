package com.zle;

import com.zle.dao.UserDao;
import com.zle.entity.User;
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

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userDao.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(10);
        user.setEmail("fw@r23.cn");
        user.setName("haha");
        userDao.insert(user);
        System.out.println("id is" + user.getId());
    }


}

