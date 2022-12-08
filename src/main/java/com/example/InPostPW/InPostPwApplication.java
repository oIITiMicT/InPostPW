package com.example.InPostPW;

import com.example.InPostPW.builder.NewUserBuilder;
import com.example.InPostPW.dto.RegistrationFormDto;
import com.example.InPostPW.model.Role;
import com.example.InPostPW.model.User;
import com.example.InPostPW.repository.RoleRepository;
import com.example.InPostPW.repository.UserRepository;
import com.example.InPostPW.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@SpringBootApplication
public class InPostPwApplication {
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    NewUserBuilder userBuilder;


    public static void main(String[] args) {
        SpringApplication.run(InPostPwApplication.class, args);
    }

    @EventListener
    public void initDB(ApplicationReadyEvent e) {
        Role adminRole = roleRepository.save(Role.builder().name("admin").build());
        Role userRole = roleRepository.save(Role.builder().name("user").build());

        User user = userBuilder.createNewUser(new RegistrationFormDto("TestTest", "12345678aA!", "sample@gmail.com"));
        user = userService.saveUser(user);
    }
}
