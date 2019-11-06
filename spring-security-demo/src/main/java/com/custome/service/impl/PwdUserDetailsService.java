package com.custome.service.impl;

import com.custome.dto.UserDO;
import com.custome.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("pwdUserDetailsService")
@Slf4j
public class PwdUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("表单登录用户名:" + userId);
        return buildUser(userId,getPwdByPwd(userId));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("设计登录用户Id:" + userId);
        return buildUser(userId,"");
    }

    private SocialUser buildUser(String userId,String encrpyPwd) {
//        String encrpyPwd = getPwdByPwd(userId);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        grantedAuthorities.add(grantedAuthority);
        return new SocialUser(userId,encrpyPwd,grantedAuthorities);
    }

    private String getPwdByPwd(String userId) {
        //对应的是用户名密码登录逻辑
        UserDO user = userService.getByUsername(userId);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        log.info("登录用户名："+user.getUsername()+"  加密后密码： "+user.getPassword());
        return user.getPassword();
    }

}
