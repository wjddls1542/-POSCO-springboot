package com.posco.insta.user.controller;


import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.user.model.UserDto;
import com.posco.insta.user.service.UserService;
import com.posco.insta.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    @TokenRequired
    public List<UserDto> getUser(){
        return userService.findUser();
    }
    @GetMapping("/{id}")
    @TokenRequired
    public UserDto getUserById(@PathVariable String id){
        UserDto userDto = new UserDto();
        userDto.setId(Integer.valueOf(id));
        return userService.findUserById(userDto);
    }
    @PostMapping("/")
    public ResponseEntity<?> postUser(@RequestBody UserDto userDto){
        HttpStatus httpStatus = userService.insertUser(userDto)==1
                ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(httpStatus);
    }

    @DeleteMapping("/")
    @TokenRequired
    public Integer deleteUser(){

        UserDto userDto = new UserDto();
        userDto.setId(securityService.getIdAtToken());
        return  userService.deleteUser(userDto);
    }
    @PutMapping("/")
    @TokenRequired
    @Operation(description = "정보수정")
    public Integer updateUser(@RequestBody UserDto userDto){

        userDto.setId(securityService.getIdAtToken());
        return userService.updateUser(userDto);
    }
    @PostMapping("/login")
    @Operation(description = "로그인")
    public Map getUserByUserIdAndPassword(@RequestBody UserDto userDto){
        UserDto loginUser = userService.loginUser(userDto);
        String token = securityService.createToken(loginUser.getId().toString());
        Map<String ,Object> map = new HashMap<>();
        map.put("token", token);
        map.put("name", loginUser.getName());
        map.put("img",loginUser.getImg());

        return map;
    }
    @GetMapping("/token")
    @TokenRequired
    public  String getToken(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        String tokenBearer = request.getHeader("Authorization"); //header에서 얻어오기
        String subject = securityService.getSubject(tokenBearer);
        return subject;
    }
    @GetMapping("/me")
    @TokenRequired
    public UserDto getUserByMe(){

        //실행 로직
        UserDto userDto = new UserDto();
        userDto.setId(securityService.getIdAtToken());
        return userService.findUserById(userDto);
    }
    @TokenRequired
    @GetMapping("/check")
    public Boolean check(){
        return true;
    }

}
