package com.gallifrey.outcode;

import com.gallifrey.outcode.dao.DiscussPostMapper;
import com.gallifrey.outcode.dao.LoginTicketMapper;
import com.gallifrey.outcode.entity.DiscussPost;
import com.gallifrey.outcode.entity.LoginTicket;
import com.gallifrey.outcode.dao.UserMapper;
import com.gallifrey.outcode.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;


@SpringBootTest
@ContextConfiguration(classes = OutCodeApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);
        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser() {
        int rows = userMapper.updateStatus(150, 0);
        System.out.println(rows);
        rows = userMapper.updateHeaderUrl(150, "http://www.nowcoder.com/101.png");
        System.out.println(rows);
        rows = userMapper.updatePassword(150, "23523");
        System.out.println(rows);
    }

    @Test
    public void testSelectDiscussPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 20);
        for (DiscussPost post : list) {
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }


    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setTicket("abd");
        loginTicket.setId(101);
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {

        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abd");
        System.out.println(loginTicket);
        loginTicketMapper.updateStatus("abd", 1);
        loginTicket = loginTicketMapper.selectByTicket("abd");
        System.out.println(loginTicket);

    }

}
