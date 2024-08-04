package com.project.UserService.Service;


import com.project.UserService.DTO.UserDTO;
import com.project.UserService.Entity.User;
import com.project.UserService.Mapper.ObjectConverter;
import com.project.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.fasterxml.jackson.databind.ObjectMapper.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerService(UserDTO userDTO) {
        try {
            return userRepository.save(ObjectConverter.convertUserDTO(userDTO));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User with this email or contact already exists");
        }
    }
}
