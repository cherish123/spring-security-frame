//package com.cus.service.impl;
//
//import com.cus.dto.UserDO;
//import com.cus.security.core.properties.LoginType;
//import com.cus.security.core.properties.SecurityProperties;
//import com.cus.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.social.security.SocialUser;
//import org.springframework.social.security.SocialUserDetails;
//import org.springframework.social.security.SocialUserDetailsService;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//public abstract class AbstractUserDetailsService implements UserDetailsService, SocialUserDetailsService {
//
////    @Autowired
////    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("表单登录用户名:" + username);
//        return buildUser(username);
//    }
//
//    @Override
//    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
//        log.info("设计登录用户Id:" + userId);
//        return buildUser(userId);
//    }
//
//    private SocialUserDetails buildUser(String userId) {
//        String encrpyPwd = getPwdByPwd(userId);
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
//        grantedAuthorities.add(grantedAuthority);
//        return new SocialUser(userId,encrpyPwd,grantedAuthorities);
//    }
//
//    protected abstract String getPwdByPwd(String userId);
//
//}
