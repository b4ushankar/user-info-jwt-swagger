package com.assignment.userInfo.resources;

import com.assignment.userInfo.dto.AuthenticationResponse;
import com.assignment.userInfo.dto.SingUpDTO;
import com.assignment.userInfo.service.jwt.UserDetailsServiceImpl;
import com.assignment.userInfo.userconstants.Messages;
import com.assignment.userInfo.userconstants.UrlMappings;
import com.assignment.userInfo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping(UrlMappings.AUTHENTICATE)
    public AuthenticationResponse createAuthenticationToken(@RequestBody SingUpDTO signUpDto, HttpServletResponse response) throws BadCredentialsException, DisabledException, IOException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signUpDto.getUsername(), signUpDto.getPassword()));
        }catch (BadCredentialsException badCredentialsException){
            throw new BadCredentialsException(Messages.INCORRECT_USER_NAME_PASSWORD);
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,Messages.USER_NOT_ACTIVATED);
            return null;
        }
        logger.info("authentication done");
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(signUpDto.getUsername());

        //generate token
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        logger.info("token created");
        return new AuthenticationResponse(jwt);
    }
}
