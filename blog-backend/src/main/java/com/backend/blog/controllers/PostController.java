package com.backend.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.config.AppConstatnts;
import com.backend.blog.payloads.ApiResponse;
import com.backend.blog.payloads.PostDto;
import com.backend.blog.payloads.PostResponse;
import com.backend.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
                PostDto createdPost =this.postService.createPost(postDto, userId, categoryId);
                return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
                
    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(
        @PathVariable Integer userId
    ){
        List<PostDto> posts =this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(
        @PathVariable Integer categoryId
    ){
        List<PostDto> posts =this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
        @RequestParam(value ="pageNumber",defaultValue = AppConstatnts.PAGE_NUMBER ,required = false) Integer pageNumber,
        @RequestParam(value ="pageSize",defaultValue = AppConstatnts.PAGE_SIZE ,required = false) Integer pageSize,
        @RequestParam(value ="sortBy",defaultValue=AppConstatnts.SORT_BY,required=false) String sortBy

    ){

        PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize,sortBy);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getAllPost(@PathVariable Integer postId){

        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(post,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted" , true);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId){
        PostDto updatedPost = this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
       List<PostDto> result =  this.postService.searchPosts(keywords);
       return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }

}