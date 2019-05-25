package com.spring_security_jwt_demo.service;


import com.spring_security_jwt_demo.pojo.User;

public interface AuthService {

    User register(User registerUser);

    String login(String username, String password);
}
