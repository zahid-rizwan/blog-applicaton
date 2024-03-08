package com.backend.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.backend.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private String id;
    private String title;
    private String content;
    private String  imageName;
    private Date addDate;
    private CategoryDto category;
    private UserDto user;
    private Set<Comment> comments = new HashSet<>();

}
