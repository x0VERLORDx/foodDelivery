package com.example.fooddelivery.service;

import com.example.fooddelivery.dao.UserDao;
import com.example.fooddelivery.dto.UserRegistrationDto;
import com.example.fooddelivery.exception.UserAlreadyExistsException;
import com.example.fooddelivery.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    @Autowired
    private UserDao userDao;
    public void registrateUser(UserRegistrationDto userRegistrationDto)throws UserAlreadyExistsException {
        if(userDao.findByUsername(userRegistrationDto.getUsername()).isPresent() || userDao.findByEmail(userRegistrationDto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setEmail(userRegistrationDto.getEmail());
        //TODO: Encrypt password
        user.setPassword(userRegistrationDto.getPassword());
        user.setUsername(userRegistrationDto.getUsername());
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        userDao.save(user);
    }
}
