package com.posco.insta.post.service;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectJoinDto;

import java.util.List;

public interface PostService {
    List<PostDto> getPosts();
    List<SelectJoinDto> getPostByUserId(PostDto postDto);
    Integer deletePost(PostDto postDto);
    List<SelectJoinDto> getPostByNotUserId(PostDto postDto);

    Integer updatePost(PostDto postDto);
    Integer writePost(PostDto postDto);
    SelectJoinDto getPostsById(PostDto postDto);
    List<SelectJoinDto> getPostsLikeKey(String key);

    List<SelectJoinDto> getPostsByMyFollowing(PostDto postDto);
}
