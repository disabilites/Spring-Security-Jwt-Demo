package com.spring_security_jwt_demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasAuthority('ROLE_NORMAL')")
    @RequestMapping( value="/normal/test", method = RequestMethod.GET )
    public String normal() {
        return "ROLE_NORMAL /normal/test接口调用成功！";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping( value = "/admin/test", method = RequestMethod.GET )
    public String admin() {
        return "ROLE_ADMIN /admin/test接口调用成功！";
    }
}
