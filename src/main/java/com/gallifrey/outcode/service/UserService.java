package com.gallifrey.outcode.service;

import com.gallifrey.outcode.dao.UserMapper;
import com.gallifrey.outcode.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public User findUserById(int id) {
        return userMapper.selectById(id);
    }

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
