package com.springcourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.repository.UserRepository;
import com.springcourse.service.util.HashUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User save(User user) {
		user.setPassword(getSecureHash(user.getPassword()));
		User createdUser = repository.save(user);
		return createdUser;
	}

	public User update(User user) {
		user.setPassword(getSecureHash(user.getPassword()));
		User updatedUser = repository.save(user);
		return updatedUser;
	}

	public User getById(Long id) {
		Optional<User> result = repository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("There are not user with id " + id));
	}

	public List<User> listAll() {
		return repository.findAll();
	}

	public User login(String email, String password) {
		Optional<User> result = repository.login(email, getSecureHash(password));
		return result.get();
	}

	private String getSecureHash(String password) {
		return HashUtil.getSecureHash(password);
	}
}
