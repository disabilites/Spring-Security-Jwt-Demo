package com.spring_security_jwt_demo.repository;

import com.spring_security_jwt_demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserRepository {

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into (username, password) values(#{username}, #{password})")
    int save(User user);
}
