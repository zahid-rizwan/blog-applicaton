
package com.backend.blog.services;

import java.util.List;

import com.backend.blog.entities.Post;
import com.backend.blog.payloads.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    PostDto getPostById(Integer id);

    List<PostDto> getAllPosts();

    void deletePost(Integer postId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);

}