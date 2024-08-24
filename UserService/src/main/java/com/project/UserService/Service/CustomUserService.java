package com.project.UserService.Service;

import com.project.UserService.Entity.User;
import com.project.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userCred = userRepository.findByUsername(username);
        return userCred.map(CustomUser::new).orElseThrow(()->new UsernameNotFoundException("user not found with name : "+username));
    }
}
