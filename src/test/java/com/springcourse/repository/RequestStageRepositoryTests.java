package com.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class RequestStageRepositoryTests {

	@Autowired
	private RequestStageRepository repository;

	@Test
	@Order(1)
	public void saveTest() {
		User owner = new User();
		owner.setId(1L);
		Request request = new Request();
		request.setId(1L);
		RequestStage resquestStage = new RequestStage(null, "Novo laptop HP", new Date(), RequestState.CLOSE, request,
				owner);
		RequestStage createdRequest = repository.save(resquestStage);
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}

	@Test
	public void getByIdTest() {
		Optional<RequestStage> result = repository.findById(1L);
		RequestStage stage = result.get();
		assertThat(stage.getDescription()).isEqualTo("Novo laptop HP");
	}

	@Test
	public void listByResquestIdTest() {
		List<RequestStage> stages = repository.findAllByRequestId(1L);
		assertThat(stages.size()).isEqualTo(1);
	}
}
