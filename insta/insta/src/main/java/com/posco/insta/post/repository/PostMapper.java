package com.posco.insta.post.repository;

import com.posco.insta.post.model.PostDto;
import com.posco.insta.post.model.SelectJoinDto;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDto> getPosts();
    List<SelectJoinDto> findPostsByUserId(PostDto postDto);
    Integer deletePost(PostDto postDto);
    PostDto insertPost(PostDto postDto);


}
