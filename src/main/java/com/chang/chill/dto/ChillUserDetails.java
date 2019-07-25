package com.chang.chill.dto;

import com.chang.chill.model.ChillPermission;
import com.chang.chill.model.ChillUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 * Created by ChangHeXiang on 2019/7/24.
 */
public class ChillUserDetails implements UserDetails {

    private ChillUser chillUser;
    private List<ChillPermission> permissionList;

    public ChillUserDetails(ChillUser chillUser, List<ChillPermission> permissionList) {
        this.chillUser = chillUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return chillUser.getPassword();
    }

    @Override
    public String getUsername() {
        return chillUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return chillUser.getStatus().equals(1);
    }
}
