package com.backend.blog.payloads;

import java.util.Date;
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

}
