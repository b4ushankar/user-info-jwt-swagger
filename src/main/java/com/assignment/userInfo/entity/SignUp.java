package com.assignment.userInfo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Register")
@Data
public class SignUp {
    @Id
    private String userName;
    private String password;
    private String role;
}
