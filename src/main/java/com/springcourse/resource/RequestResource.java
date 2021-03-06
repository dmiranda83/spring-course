package com.springcourse.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.dto.RequestSaveDto;
import com.springcourse.dto.RequestUpdateDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
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
	public ResponseEntity<Request> save(@RequestBody @Valid RequestSaveDto requestSaveDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(requestService.save(requestSaveDto.transformToRequest()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id,
			@RequestBody @Valid RequestUpdateDto requestUpdateDto) {
		Request request = requestUpdateDto.transformToRequest();
		request.setId(id);
		return ResponseEntity.ok(requestService.update(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(requestService.getById(id));
	}

	@GetMapping("/{id}/request-stage")
	public ResponseEntity<PageModel<RequestStage>> listAllStagesById(@PathVariable(name = "id") Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return ResponseEntity
				.ok(requestStageService.listAllByRequestIdOnLazyModel(id, new PageRequestModel(page, size)));
	}

	@GetMapping
	public ResponseEntity<PageModel<Request>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return ResponseEntity.ok(requestService.listAllOnLazyModel(new PageRequestModel(page, size)));
	}

}
