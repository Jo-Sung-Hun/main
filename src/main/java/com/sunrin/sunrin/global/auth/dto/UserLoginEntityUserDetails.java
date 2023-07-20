package com.sunrin.sunrin.global.auth.dto;

import com.sunrin.sunrin.global.auth.domain.UserLoginEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserLoginEntityUserDetails implements UserDetails{
    private boolean locked;	//계정 잠김 여부
    private boolean enabled;	//계정 활성화 여부
    private Collection<GrantedAuthority> authorities;	//권한 목록
    private UserLoginEntity userLoginEntity;	//로그인 계정 정보

    public UserLoginEntityUserDetails(UserLoginEntity userLoginEntity) {
        this.userLoginEntity = userLoginEntity;
        this.locked = false;
        this.enabled = true;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return userLoginEntity.getUserLoginPassword();
    }

    @Override
    public String getUsername() {
        return this.userLoginEntity.getUserLoginEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }


    @Override
    public boolean isEnabled() {
        return false;

    }
}
