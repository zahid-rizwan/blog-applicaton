package com.backend.blog.repositories;

import com.backend.blog.entities.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordRepo extends JpaRepository<ForgotPassword, Integer> {
}
