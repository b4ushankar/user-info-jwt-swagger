package com.assignment.userInfo.service.auth;

import com.assignment.userInfo.data.SignUpRepository;
import com.assignment.userInfo.dto.SingUpDTO;
import com.assignment.userInfo.entity.SignUp;
import com.assignment.userInfo.userconstants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSerivceImpl implements AuthService{

    @Autowired
    private SignUpRepository singUpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public SignUp createUser(SingUpDTO signupDto) {
        SignUp signup = new SignUp();
        signup.setUserName(signupDto.getUsername());
        signup.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        signup.setRole(signupDto.getRole()==null? Roles.ROLE_USER.getRole():signupDto.getRole());

        singUpRepository.save(signup);

        return signup;
    }
}
