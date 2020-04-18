package com.springcourse.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSaveDto {
	@NotBlank(message = "Subject required")
	private String subject;
	private String description;
	@NotBlank(message = "Owner required")
	private User owner;
	private List<RequestStage> stages = new ArrayList<>();

	public Request transformToRequest() {
		return new Request(null, this.subject, this.description, null, null, this.owner, this.stages);

	}
}
