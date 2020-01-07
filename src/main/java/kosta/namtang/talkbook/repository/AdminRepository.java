package kosta.namtang.talkbook.repository;

import org.springframework.data.repository.CrudRepository;

import kosta.namtang.talkbook.model.domain.Statistics;

public interface AdminRepository extends CrudRepository<Statistics, Long> {
	

}
