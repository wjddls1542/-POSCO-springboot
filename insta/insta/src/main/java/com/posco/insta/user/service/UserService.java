package com.posco.insta.user.service;

import com.posco.insta.user.model.UserDto;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<UserDto> findUser();
    UserDto findUserById(UserDto userDto);
    Integer insertUser(UserDto userDto);
    Integer deleteUser(UserDto userDto);
    Integer updateUser(UserDto userDto);
    UserDto loginUser(UserDto userDto);
}
