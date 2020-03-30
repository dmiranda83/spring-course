package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repository;

	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		Request createdRequest = repository.save(request);
		return createdRequest;
	}

	public Request update(Request request) {
		Request updatedRequest = repository.save(request);
		return updatedRequest;
	}

	public Request getById(Long id) {
		Optional<Request> result = repository.findById(id);
		return result.get();
	}

	public List<Request> listAll() {
		return repository.findAll();
	}

	public List<Request> listAllByOwner(Long ownerId) {
		return repository.findAllByOwnerId(ownerId);
	}
}
