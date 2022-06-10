package com.posco.insta.post.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.follow.model.FollowDto;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectJoinDto;
import com.posco.insta.post.service.PostService;
import com.posco.insta.post.service.PostServiceImpl;
import com.posco.insta.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("post")
@TokenRequired
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @Autowired
    SecurityService securityService;
    @Autowired
    PostDto postDto;
    @Autowired
    FollowDto followDto;

    @GetMapping("/")
    public List<PostDto> getPosts() {return postService.getPosts();}

    @GetMapping("/my")
    public List<SelectJoinDto> getMyPosts(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostByUserId(postDto);
    }
    @GetMapping("/{id}")
    public SelectJoinDto getPostsById(@PathVariable String id){
        postDto.setId(Integer.valueOf(id));
        return postService.getPostsById(postDto);
    }
    @DeleteMapping("/{id}")
    public Integer deletePost(@PathVariable String id){
        postDto.setId(Integer.valueOf(id));
        postDto.setUserId(securityService.getIdAtToken());
        return postService.deletePost(postDto);
    }
    @GetMapping("/other")
    public List<SelectJoinDto> getOtherPosts(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostByNotUserId(postDto);
    }
    @PutMapping("/{id}")
    public Integer updatePost(@RequestBody PostDto postDto, @PathVariable String id){
        postDto.setUserId(securityService.getIdAtToken());
        postDto.setId(Integer.valueOf(id));
        return postService.updatePost(postDto);
    }
    @PostMapping("/")
    public Integer writePost(@RequestBody PostDto postDto){
        postDto.setUserId(securityService.getIdAtToken());

        return postService.writePost(postDto);
    }
    @GetMapping("/key/{key}")
    public List<SelectJoinDto> getPostsLikeKey(@PathVariable String key){
        return postService.getPostsLikeKey(key);
    }
    @GetMapping("/following")
    @Operation(description = "내가 팔로잉이고, 팔로워인 애들 getpost")
    public List<SelectJoinDto> getPostsByMyFollowing(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostsByMyFollowing(postDto);



    }

}
