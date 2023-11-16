package com.example.fooddelivery.service;

import com.example.fooddelivery.dao.UserDao;
import com.example.fooddelivery.dto.LoginDto;
import com.example.fooddelivery.dto.UserRegistrationDto;
import com.example.fooddelivery.exception.UserAlreadyExistsException;
import com.example.fooddelivery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegistrationService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private JWTService jstService;
    public void registrateUser(UserRegistrationDto userRegistrationDto)throws UserAlreadyExistsException {
        if(userDao.findByUsername(userRegistrationDto.getUsername()).isPresent() || userDao.findByEmail(userRegistrationDto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(encryptionService.encryptPassword(userRegistrationDto.getPassword()));
        user.setUsername(userRegistrationDto.getUsername());
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        userDao.save(user);
    }


    public String loginUser(LoginDto loginDto){
        Optional<User> optionalUser = userDao.findByUsername(loginDto.getUsername());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (encryptionService.verifyPassword(loginDto.getPassword(), user.getPassword())){
                return jstService.generateJWT(user);
            }
        }
        return null;
    }
}
