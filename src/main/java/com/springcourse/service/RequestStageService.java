package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.RequestStage;
import com.springcourse.exception.NotFoundException;
import com.springcourse.repository.RequestRepository;
import com.springcourse.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired
	private RequestStageRepository requestStageRepository;
	@Autowired
	private RequestRepository requestRepository;

	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());
		RequestStage createdStage = requestStageRepository.save(stage);
		requestRepository.updateState(stage.getId(), stage.getState());
		return createdStage;
	}

	public RequestStage getById(Long id) {
		Optional<RequestStage> result = requestStageRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("There are not request stage with id " + id));
	}

	public List<RequestStage> listAllByRequestId(Long requestId) {
		return requestStageRepository.findAllByRequestId(requestId);
	}
}
