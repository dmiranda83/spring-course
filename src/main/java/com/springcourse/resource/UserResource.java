package com.springcourse.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLoginDto;
import com.springcourse.dto.UserSaveDto;
import com.springcourse.dto.UserUpdateDto;
import com.springcourse.dto.UserUpdateRoleDto;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;
	@Autowired
	private RequestService requestService;

	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserSaveDto userSaveDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userSaveDto.transformToUser()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id,
			@RequestBody @Valid UserUpdateDto userUpdateDto) {
		User user = userUpdateDto.transformToUser();
		user.setId(id);
		return ResponseEntity.ok(userService.update(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(userService.getById(id));
	}

	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestsById(@PathVariable(name = "id") Long id,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return ResponseEntity.ok(requestService.listAllByOwnerOnLazyModel(id, new PageRequestModel(page, size)));
	}

	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return ResponseEntity.ok(userService.listAllOnLazyModel(new PageRequestModel(page, size)));
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody @Valid UserLoginDto user) {
		User userLoggerd = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(userLoggerd);
	}

	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id,
			@RequestBody @Valid UserUpdateRoleDto userUpdateRoleDto) {
		User user = new User();
		user.setId(id);
		user.setRole(userUpdateRoleDto.getRole());
		userService.updateRole(user);
		return ResponseEntity.ok().build();

	}
}
