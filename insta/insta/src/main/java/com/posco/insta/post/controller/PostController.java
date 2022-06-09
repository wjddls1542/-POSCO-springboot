package com.posco.insta.post.controller;

import com.posco.insta.aspect.TokenRequired;
import com.posco.insta.config.SecurityService;
import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectJoinDto;
import com.posco.insta.post.service.PostService;
import com.posco.insta.post.service.PostServiceImpl;
import com.posco.insta.user.model.UserDto;
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

    @GetMapping("/")
    public List<PostDto> getPosts() {return postService.getPosts();}

    @GetMapping("/my")

    public List<SelectJoinDto> getMyPosts(){
        postDto.setUserId(securityService.getIdAtToken());
        return postService.getPostByUserId(postDto);
    }
    @DeleteMapping("/{id}")
    public Integer deletePost(@PathVariable String id){
        postDto.setId(Integer.valueOf(id));
        postDto.setUserId(securityService.getIdAtToken());
        return postService.deletePost(postDto);
    }

}
