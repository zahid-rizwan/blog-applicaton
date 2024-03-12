package com.backend.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotNull
    @Size(min=4,message ="Username must be minimum of 4 characters" )
    private String name;
    @Email(message = "Your email address is not valid !!")
    private String email;
    @NotEmpty
    @Size(min=3,max = 10 , message = "password must be minimum of 3 chars and max of 10 chars")
    private String password;
    @NotEmpty
    private String about;
    private Set<RoleDto> roles = new HashSet<>();
}
