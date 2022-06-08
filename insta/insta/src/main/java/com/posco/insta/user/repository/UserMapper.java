package com.posco.insta.user.repository;

import com.posco.insta.user.model.UserDto;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDto> getUser();
    UserDto getUserById(UserDto userDto);
    Integer postUser(UserDto userDto);
    Integer deleteUser(UserDto userDto);
    Integer updateUser(UserDto userDto);
    UserDto getUserByUserIdAndPassword(UserDto userDto);
}
