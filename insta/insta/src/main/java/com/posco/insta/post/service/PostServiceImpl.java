package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectJoinDto;
import com.posco.insta.post.repository.PostMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostMapper postMapper;

    @Override
    public List<PostDto> getPosts(){
        return postMapper.getPosts();
    }
    @Override
    public  List<SelectJoinDto> getPostByUserId(PostDto postDto){
        return postMapper.findPostsByUserId(postDto);
    }
    public Integer deletePost(PostDto postDto){
        return postMapper.deletePost(postDto);
    }


}
