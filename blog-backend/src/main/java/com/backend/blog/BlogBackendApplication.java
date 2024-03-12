package com.backend.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.blog.config.AppConstatnts;
import com.backend.blog.entities.Role;
import com.backend.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogBackendApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogBackendApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception{
		
		try {
			Role role=new Role();
			role.setId(AppConstatnts.ADMIN_USER);
			role.setName("ADMIN_USER");
			Role role1=new Role();
			role1.setId(AppConstatnts.NORMAL_USER);
			role1.setName("NORMAL_USER");
			this.roleRepo.saveAll(List.of(role,role1)).forEach(r->{
				System.out.println(r.getName());
			});
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
