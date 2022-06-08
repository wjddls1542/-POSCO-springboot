package com.posco.insta.user.controller;


import com.posco.insta.config.SecurityService;
import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserService;
import com.posco.insta.user.service.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public List<UserDto> getUser(){
        return userService.findUser();
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id){
        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
        return userService.findUserById(userDto);
    }
    @PostMapping("/")
    public Integer postUser(@RequestBody UserDto userDto){
        return userService.insertUser(userDto);
    }
    @DeleteMapping("/{id}")
    public Integer deleteUser(@PathVariable String id){
        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
        return  userService.deleteUser(userDto);
    }
    @PutMapping("/{id}")
    public Integer updateUser(@RequestBody UserDto userDto, @PathVariable String id){
        userDto.setId(Integer.valueOf(id));
        return userService.updateUser(userDto);
    }
    @PostMapping("/login")
    public Map getUserByUserIdAndPassword(@RequestBody UserDto userDto){
        UserDto loginUser = userService.loginUser(userDto);
        String token = securityService.createToken(loginUser.getUserId().toString(), 3*60*60*1000);
        Map<String ,Object> map = new HashMap<>();
        map.put("token", token);
        map.put("name", loginUser.getName());
        map.put("img",loginUser.getImg());

        return map;
    }
    @GetMapping("/token")
    public  String getToken(@RequestParam(value = "token")String token){
        String subject = securityService.getSubject(token);
        return subject;
    }

}
