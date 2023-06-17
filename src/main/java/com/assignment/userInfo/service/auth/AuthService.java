package com.assignment.userInfo.service.auth;

import com.assignment.userInfo.dto.SingUpDTO;
import com.assignment.userInfo.entity.SignUp;

public interface AuthService {
    SignUp createUser(SingUpDTO signupDto);
}
