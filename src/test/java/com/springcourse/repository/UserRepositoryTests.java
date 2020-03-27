package com.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository repository;

	@Test
	@Order(1)
	public void saveTest() {
		User user = new User(null, "Diego", "diegoetata@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User createdUser = repository.save(user);
		assertThat(createdUser.getId()).isEqualTo(1);
	}

	@Test
	public void updateTest() {
		User user = new User(1L, "Diego Miranda", "diegoetata@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User updatedUser = repository.save(user);
		assertThat(updatedUser.getName()).isEqualTo("Diego Miranda");
	}

	@Test
	public void getByIdTest() {
		Optional<User> userLocated = repository.findById(1L);
		User user = userLocated.get();
		assertThat(user.getPassword()).isEqualTo("123");

	}

	@Test
	public void listTest() {
		List<User> users = repository.findAll();
		assertThat(users.size()).isEqualTo(1);
	}

	@Test
	public void loginTest() {
		Optional<User> login = repository.login("diegoetata@gmail.com", "123");
		User user = login.get();
		assertThat(user.getId()).isEqualTo(1L);
	}
}
