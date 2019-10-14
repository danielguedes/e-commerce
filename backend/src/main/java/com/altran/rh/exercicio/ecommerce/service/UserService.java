package com.altran.rh.exercicio.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.rh.exercicio.ecommerce.exception.BadRequestException;
import com.altran.rh.exercicio.ecommerce.exception.NotFoundException;
import com.altran.rh.exercicio.ecommerce.exception.ObjectExistsException;
import com.altran.rh.exercicio.ecommerce.model.User;
import com.altran.rh.exercicio.ecommerce.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getById(String id) {
		return userRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User create(User user) {
		normalize(user);
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new ObjectExistsException();
		}
		return userRepository.save(user);
	}

	public User updateById(String id, User user) {
		normalize(user);
		this.getById(id);
		user.setId(id);
		return userRepository.save(user);
	}

	public void removeById(String id) {
		userRepository.deleteById(id);
	}

	public void validateInexistentUser(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new BadRequestException();
		}
	}

	private void normalize(User user) {
		user.setName(user.getName().trim());
		user.setEmail(user.getEmail().toLowerCase().trim());
	}
}
