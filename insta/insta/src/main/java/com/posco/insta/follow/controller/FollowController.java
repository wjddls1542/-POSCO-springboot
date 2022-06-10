package com.posco.insta.follow.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.follow.model.FollowDto;
import com.posco.insta.follow.service.FollowService;
import com.posco.insta.follow.service.FollowServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@TokenRequired
@RequestMapping("follow")

public class FollowController {
    @Autowired
    FollowDto followDto;
    @Autowired
    SecurityService securityService;
    @Autowired
    FollowServiceImpl followService;

    @Operation(description = "나의 follower 받아온다")
    @GetMapping("/my/follower")
    public List<FollowDto> getMyFollower(){
        followDto.setFollowing(securityService.getIdAtToken());
        return followService.getFollower(followDto);
    }

    @GetMapping("/follower/{id}")
    @Operation(description = "follwer 받아온다")
    public List<FollowDto> getMyFollowerById(@PathVariable String id){
        followDto.setFollowing(Integer.valueOf(id));
        return followService.getFollower(followDto);
    }
    @Operation(description = "나의 following 받아온다")
    @GetMapping("/my/following")
    public List<FollowDto> getMyFollowing(){
        followDto.setFollower(securityService.getIdAtToken());
        return followService.getFollowing(followDto);
    }
    @Operation(description = "following 받아온다")
    @GetMapping("/following/{id}")
    public List<FollowDto> getMyFollowingById(@PathVariable String id){
        followDto.setFollower(Integer.valueOf(id));
        return followService.getFollowing(followDto);
    }
    @Operation(description = "내가 상대방의 following이 된다")
    @PostMapping("/{id}")
    public Integer postFollow(@PathVariable String id){
        followDto.setFollowing(securityService.getIdAtToken());
        followDto.setFollower(Integer.valueOf(id));
        return followService.insertFollow(followDto);
    }
    @DeleteMapping("/{id}")
    @Operation(description = "내가 상대방의 follow가 삭제된다")
    public Integer deleteFollow(@PathVariable String id){
        followDto.setFollower(securityService.getIdAtToken());
        followDto.setFollowing(Integer.valueOf(id));
        return followService.deleteFollow(followDto);
    }

}
