package com.springcourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
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

	public PageModel<User> listAllOnLazyModel(PageRequestModel model) {
		Pageable pageable = PageRequest.of(model.getPage(), model.getSize());
		Page<User> page = repository.findAll(pageable);
		return new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
	}

	public int updateRole(User user) {
		return repository.updateRole(user.getId(), user.getRole());
	}
}
