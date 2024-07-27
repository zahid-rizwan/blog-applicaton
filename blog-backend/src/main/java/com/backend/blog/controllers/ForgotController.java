package com.backend.blog.controllers;

import com.backend.blog.entities.ForgotPassword;
import com.backend.blog.payloads.MailBody;
import com.backend.blog.payloads.UserDto;
import com.backend.blog.repositories.ForgotPasswordRepo;
import com.backend.blog.services.UserService;
import com.backend.blog.services.impl.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotController {

//    private static final Logger logger = Logger.getLogger(ForgotController.class.getName());

    @Autowired
    private UserService userService;
    @Autowired
    private EmailServices emailServices;
    @Autowired
    private ForgotPasswordRepo forgotPasswordRepo;

    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        UserDto userDto = userService.getUserByEmail(email);
        int otp = otpGenerator();

        // Log the OTP for debugging
//        logger.info("Generated OTP: " + otp);

        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("This is the OTP for your forgot password: " + otp)
                .subject("OTP for Forgot Password")
                .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                .user(userService.dtoToUser(userDto))
                .build();

        emailServices.SendSimpleMessage(mailBody);
        forgotPasswordRepo.save(forgotPassword);

        return ResponseEntity.ok("Email sent for verification");
    }

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
