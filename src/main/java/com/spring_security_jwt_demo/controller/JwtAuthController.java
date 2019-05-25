package com.spring_security_jwt_demo.controller;

import com.spring_security_jwt_demo.pojo.User;
import com.spring_security_jwt_demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public String createToken( String username,String password ) throws AuthenticationException {
        return authService.login( username, password );
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public User register(@RequestBody User registerUser) throws AuthenticationException {
        return authService.register(registerUser);
    }
}