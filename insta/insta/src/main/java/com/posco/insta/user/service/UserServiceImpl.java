package com.posco.insta.user.service;

import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.repository.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public List<UserDto> findUser(){
        return userMapper.getUser();
    }

    @Override
    public UserDto findUserById(UserDto userDto){
        return userMapper.getUserById(userDto);
    }

    @Override
    public Integer insertUser(UserDto userDto){
        return  userMapper.postUser(userDto);
    }

    @Override
    public  Integer deleteUser(UserDto userDto){
        return  userMapper.deleteUser(userDto);
    }

    @Override
    public Integer updateUser(UserDto userDto){
        return userMapper.updateUser(userDto);
    }
    @Override
    public UserDto loginUser(UserDto userDto){
        return userMapper.getUserByUserIdAndPassword(userDto);
    }
}
