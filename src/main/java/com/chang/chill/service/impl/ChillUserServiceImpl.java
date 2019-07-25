package com.chang.chill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chang.chill.mapper.ChillUserMapper;
import com.chang.chill.mapper.ChillUserRolePermissionMapper;
import com.chang.chill.model.ChillPermission;
import com.chang.chill.model.ChillUser;
import com.chang.chill.service.ChillUserService;
import com.chang.chill.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by ChangHeXiang on 2019/7/24.
 */
@Slf4j
@Service
public class ChillUserServiceImpl implements ChillUserService {

    @Resource
    private ChillUserMapper mapper;
    @Resource
    private ChillUserRolePermissionMapper permissionMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ChillUser getUserByUsername(String username) {
        QueryWrapper<ChillUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public List<ChillPermission> getPermissionList(Long id) {
        return permissionMapper.getPermissionList(id);
    }

    @Override
    public ChillUser register(ChillUser chillUserParam) {
        ChillUser user = new ChillUser();
        BeanUtils.copyProperties(chillUserParam, user);
        user.setCreateTime(new Date());
        user.setStatus(1);
        //查询是否有相同用户名的用户
        QueryWrapper<ChillUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        List<ChillUser> list = mapper.selectList(queryWrapper);
        if(list!=null && !list.isEmpty()){
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        mapper.insert(user);
        return user;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
}
