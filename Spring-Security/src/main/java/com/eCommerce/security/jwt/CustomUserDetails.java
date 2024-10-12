package com.eCommerce.security.jwt;



import com.eCommerce.UserService.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
//    private Set<GrantedAuthority> authorities;

    public CustomUserDetails(User userCredential) {
        this.username = userCredential.getUsername();
        this.password = userCredential.getPassword();
//        this.authorities = userCredential.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRole())) // Prefix role
//                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}