package com.posco.insta.post.controller;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.service.PostService;
import com.posco.insta.post.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    PostServiceImpl postService;

    @GetMapping("/")
    public List<PostDto> getPost() {return postService.findPost();}

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable String id){
        PostDto postDto = new PostDto();
        postDto.setId(Integer.valueOf(id));
        return postService.findPostById(postDto);
    }

}
