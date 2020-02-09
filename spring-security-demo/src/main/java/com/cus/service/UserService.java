package com.cus.service;

import com.cus.dto.UserDO;

public interface UserService {
    /**
     * 查询用户信息
     * @param username 账号
     * @return UserEntity
     */
    UserDO getByUsername(String username);
}
