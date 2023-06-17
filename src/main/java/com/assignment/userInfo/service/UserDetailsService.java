package com.assignment.userInfo.service;

import com.assignment.userInfo.data.UserDetailsRepository;
import com.assignment.userInfo.dto.UserDetailsDto;
import com.assignment.userInfo.entity.UserDetails;
import com.assignment.userInfo.exceptions.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetails findUser(String userId){

        return userDetailsRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundException("user Not found"));

    }

    public List<UserDetails> getAllUsers() {
        return userDetailsRepository.findAll();
    }

    public UserDetails saveUser(UserDetailsDto userDetailsDto){
        UserDetails user = new UserDetails();
        BeanUtils.copyProperties(userDetailsDto,user);
        return userDetailsRepository.save(user);
    }

    public void updateUser(UserDetailsDto userDetailsDto){
        UserDetails entityUserDetails = findUser(userDetailsDto.getUserId());
        entityUserDetails.setCity(userDetailsDto.getCity()!=null ? userDetailsDto.getCity() : entityUserDetails.getCity());
        entityUserDetails.setName(userDetailsDto.getName()!=null? userDetailsDto.getName(): entityUserDetails.getName());

        userDetailsRepository.save(entityUserDetails);

    }

    public void deleteUser(String userId){
        UserDetails user = findUser(userId);
        userDetailsRepository.delete(user);
    }


}
