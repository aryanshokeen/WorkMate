package net.project.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.project.springboot.dto.UserRegistrationDto;
import net.project.springboot.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
