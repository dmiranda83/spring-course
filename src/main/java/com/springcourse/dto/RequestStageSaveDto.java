package com.springcourse.dto;

import javax.validation.constraints.NotBlank;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestStageSaveDto {
	private String description;
	@NotBlank(message = "State required")
	private RequestState state;
	@NotBlank(message = "Request required")
	private Request request;
	@NotBlank(message = "Owner required")
	private User owner;

	public RequestStage transformToRequestStage() {
		return new RequestStage(null, this.description, null, this.state, this.request, this.owner);
	}
}
