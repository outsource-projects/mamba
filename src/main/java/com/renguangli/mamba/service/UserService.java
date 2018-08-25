package com.renguangli.mamba.service;

import com.renguangli.mamba.entity.User;

/**
 * UserService
 *
 * @author renguangli 2018/2/11 23:30
 * @since JDK 1.8
 */
public interface UserService {

    User findByUsername(String username);
}
