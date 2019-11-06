package com.custome.service.impl;

import com.custome.dto.UserDO;
import com.custome.mapper.UserMapper;
import com.custome.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO getByUsername(String username) {
        UserDO userDo = userMapper.selectByPrimaryKey(username);
        return userDo;
    }
}
