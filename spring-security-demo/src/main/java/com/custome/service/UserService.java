package com.custome.service;

import com.custome.dto.UserDO;

public interface UserService {
    /**
     * 查询用户信息
     * @param username 账号
     * @return UserEntity
     */
    UserDO getByUsername(String username);
}
