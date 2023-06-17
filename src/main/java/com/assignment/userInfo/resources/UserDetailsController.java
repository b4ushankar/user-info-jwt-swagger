package com.assignment.userInfo.resources;

import com.assignment.userInfo.data.UserDetailsRepository;
import com.assignment.userInfo.dto.UserDetailsDto;
import com.assignment.userInfo.entity.UserDetails;

import com.assignment.userInfo.service.UserDetailsService;
import com.assignment.userInfo.userconstants.Messages;
import com.assignment.userInfo.userconstants.UrlMappings;
import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value= UrlMappings.USERS)
public class UserDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping(UrlMappings.GET_USER)
    public ResponseEntity<?> getUser(@RequestParam(value = "userId",required = false) String userId){
        logger.debug("user id is "+userId);
        if(userId==null){
            List<UserDetails> usersList = userDetailsService.getAllUsers();
          return new ResponseEntity<List<UserDetails>>(usersList, HttpStatus.OK);
        }
        else{
            UserDetails user = userDetailsService.findUser(userId);
            return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
        }

    }

    @PostMapping(UrlMappings.CREATE_USER)
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDetailsDto userDetailsDto){
        userDetailsService.saveUser(userDetailsDto);
        return new ResponseEntity<String>(Messages.SUCCESS,HttpStatus.CREATED);
    }

    @PutMapping(UrlMappings.UPDATE_USER)
    public ResponseEntity<String> updateUser(@RequestBody UserDetailsDto userDetailsDto){
        userDetailsService.updateUser(userDetailsDto);
        return new ResponseEntity<String>(Messages.SUCCESS,HttpStatus.OK);
    }

    @DeleteMapping(UrlMappings.DELETE_USER)
    public ResponseEntity<String> deleteUser(@RequestParam("userId") String userId){
        userDetailsService.deleteUser(userId);
        return new ResponseEntity<String>(Messages.SUCCESS,HttpStatus.ACCEPTED);
    }





}
