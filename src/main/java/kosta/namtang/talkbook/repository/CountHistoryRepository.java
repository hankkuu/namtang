package kosta.namtang.talkbook.repository;

import kosta.namtang.talkbook.model.domain.BookCount;
import kosta.namtang.talkbook.model.domain.BookCountHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountHistoryRepository extends CrudRepository<BookCountHistory,Long> {

}

