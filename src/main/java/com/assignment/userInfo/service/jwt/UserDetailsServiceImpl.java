package com.assignment.userInfo.service.jwt;

import com.assignment.userInfo.data.SignUpRepository;
import com.assignment.userInfo.dto.SingUpDTO;
import com.assignment.userInfo.entity.SignUp;
import com.assignment.userInfo.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SignUpRepository signUpRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //database logic to retrive data
        SignUp signUp = signUpRepository.findById(username).orElseThrow(()->new UserNotFoundException("User Name or password Incorrect"));
        //creating authorities to add role
        //Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        //authorities.add(new SimpleGrantedAuthority(signUp.getRole()));

        Set<SimpleGrantedAuthority> authorities = Arrays.stream(signUp.getRole().split(","))
                                                    .map(SimpleGrantedAuthority::new)
                                                    .collect(Collectors.toSet());

        // adding role
        return new User(signUp.getUserName(),signUp.getPassword(),authorities);
    }
}
