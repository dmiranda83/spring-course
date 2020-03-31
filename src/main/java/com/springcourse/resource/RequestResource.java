package com.springcourse.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;

@RestController
@RequestMapping(value = "resquests")
public class RequestResource {

	@Autowired
	private RequestService requestService;
	@Autowired
	private RequestStageService requestStageService;

	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(requestService.save(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody Request request) {
		request.setId(id);
		return ResponseEntity.ok(requestService.update(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(requestService.getById(id));
	}

	@GetMapping("/{id}/request-stage")
	public ResponseEntity<List<RequestStage>> listAllStagesById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(requestStageService.listAllByRequestId(id));
	}

	@GetMapping
	public ResponseEntity<List<Request>> getAll() {
		return ResponseEntity.ok(requestService.listAll());
	}

}
