package com.springcourse.dto;

import com.springcourse.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRoleDto {
	private Role role;
}
