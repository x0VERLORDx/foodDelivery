package com.example.fooddelivery.controller;

import com.example.fooddelivery.dto.LoginDto;
import com.example.fooddelivery.dto.LoginResponseDto;
import com.example.fooddelivery.dto.UserRegistrationDto;
import com.example.fooddelivery.exception.UserAlreadyExistsException;
import com.example.fooddelivery.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserRegistrationService userRegistrationService;
    @PostMapping("/registration")
    public ResponseEntity registerUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto){
        try {
            userRegistrationService.registrateUser(userRegistrationDto);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @PostMapping("/login")
    public  ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody LoginDto loginDto){
        String jwt = userRegistrationService.loginUser(loginDto);
        if (jwt == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            LoginResponseDto response = new LoginResponseDto();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }
}
