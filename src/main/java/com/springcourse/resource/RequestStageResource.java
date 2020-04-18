package com.springcourse.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.RequestStage;
import com.springcourse.dto.RequestStageSaveDto;
import com.springcourse.service.RequestStageService;

@RestController
@RequestMapping(value = "resquest-stage")
public class RequestStageResource {

	@Autowired
	private RequestStageService service;

	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSaveDto requestStageSaveDto) {
		RequestStage transformToRequestStage = requestStageSaveDto.transformToRequestStage();
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(transformToRequestStage));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(service.getById(id));
	}
}
