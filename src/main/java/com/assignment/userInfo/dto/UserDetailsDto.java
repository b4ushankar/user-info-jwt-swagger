package com.assignment.userInfo.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
public class UserDetailsDto {


    @NotBlank(message = "userid is missing")
    private String userId;

    @NotBlank(message = "name is missing")
    private String name;

    @NotBlank(message = "city is missing")
    private String city;
}
