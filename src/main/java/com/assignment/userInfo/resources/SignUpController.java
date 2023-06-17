package com.assignment.userInfo.resources;

import com.assignment.userInfo.dto.SingUpDTO;
import com.assignment.userInfo.entity.SignUp;
import com.assignment.userInfo.service.auth.AuthService;
import com.assignment.userInfo.userconstants.Messages;
import com.assignment.userInfo.userconstants.UrlMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @Autowired
    private AuthService authService;

    @PostMapping(UrlMappings.SIGN_UP)
    public ResponseEntity<String> register(@RequestBody SingUpDTO signupDto){
        SignUp signUp = authService.createUser(signupDto);
        return new ResponseEntity<String>(signUp.getUserName() + Messages.SUCCESSFULLY_CREATED, HttpStatus.CREATED);

    }
}
