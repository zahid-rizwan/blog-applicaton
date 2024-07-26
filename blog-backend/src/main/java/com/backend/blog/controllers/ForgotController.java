package com.backend.blog.controllers;

import com.backend.blog.entities.ForgotPassword;
import com.backend.blog.payloads.MailBody;
import com.backend.blog.payloads.UserDto;
import com.backend.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotController {

//send mail for email verification
    @Autowired
    private UserService userService;
    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        UserDto userDto=userService.getUserByEmail(email);
        int otp=otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("this is the otp for your forgot password")
                .subject("Otp for forgot password")
                .build();
        ForgotPassword forgotPassword =  ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis()+70*1000))
                .user(userService.dtoToUser(userDto))
                .build();
        return ResponseEntity.ok("userDto");
    }
    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000,999_999);
    }

}
