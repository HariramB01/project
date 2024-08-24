package com.project.UserService.Service;


import com.project.UserService.DTO.UserDTO;
import com.project.UserService.Entity.User;
import com.project.UserService.Mapper.ObjectConverter;
import com.project.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public User registerService(UserDTO userDTO) {
        try {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            return userRepository.save(ObjectConverter.convertUserDTO(userDTO));
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("User with this email or contact already exists");
        }
    }


    public String generateToken(String username){
        return jwtService.generateToken(username);
    }


    public String vaidateToken(String token){
        jwtService.validateToken(token);
        return token;
    }

}
