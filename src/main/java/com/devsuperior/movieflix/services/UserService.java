package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public UserDTO getProfile() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = repository.findByEmail(username);

		return new UserDTO(user);

	}

}
