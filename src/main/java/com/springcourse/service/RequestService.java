package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
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
		return result.orElseThrow(() -> new NotFoundException("There are not request with id " + id));
	}

	public List<Request> listAll() {
		return repository.findAll();
	}

	public PageModel<Request> listAllOnLazyModel(PageRequestModel model) {
		Pageable pageable = PageRequest.of(model.getPage(), model.getSize());
		Page<Request> page = repository.findAll(pageable);
		return new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
	}

	public List<Request> listAllByOwner(Long ownerId) {
		return repository.findAllByOwnerId(ownerId);
	}

	public PageModel<Request> listAllByOwnerOnLazyModel(Long ownerId, PageRequestModel model) {
		Pageable pageable = PageRequest.of(model.getPage(), model.getSize());
		Page<Request> page = repository.findAllByOwnerId(ownerId, pageable);
		return new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
	}
}
