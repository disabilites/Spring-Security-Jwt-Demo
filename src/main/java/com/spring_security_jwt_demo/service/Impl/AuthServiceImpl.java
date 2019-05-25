package com.spring_security_jwt_demo.service.Impl;


import com.spring_security_jwt_demo.pojo.User;
import com.spring_security_jwt_demo.repository.UserRepository;
import com.spring_security_jwt_demo.service.AuthService;
import com.spring_security_jwt_demo.service.UserService;
import com.spring_security_jwt_demo.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String username, String password ) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public User register(User registerUser) {
        final String username = registerUser.getUsername();
        if (userRepository.findByUsername(username)!=null ) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = registerUser.getPassword();
        registerUser.setPassword(encoder.encode(rawPassword) );
        userRepository.save(registerUser);
        return registerUser;
    }
}
