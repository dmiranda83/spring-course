package com.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

	@Query("UPDATE request SET state = ?2 WHERE id = ?1")
	public Request updateState(Long id, RequestState state);
}
