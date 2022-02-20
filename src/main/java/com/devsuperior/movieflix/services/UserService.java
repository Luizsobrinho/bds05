package com.devsuperior.movieflix.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class UserService  implements UserDetailsService{

	@Autowired
	private UserRepository repository;

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Transactional(readOnly = true)
	public UserDTO getProfile() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = repository.findByEmail(username);

		return new UserDTO(user);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByEmail(username);
		if (user == null) {
			logger.error("User not found {0}. ", username);
			throw new UsernameNotFoundException("Email não encontrado");
		}
		logger.info("User found: " + username);
		return user;
	}

}
