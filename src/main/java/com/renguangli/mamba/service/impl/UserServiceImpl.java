package com.renguangli.mamba.service.impl;

import com.renguangli.mamba.entity.User;
import com.renguangli.mamba.mapper.UserMapper;
import com.renguangli.mamba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author renguangli 2018/2/11 23:30
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.login(username);
    }
}
