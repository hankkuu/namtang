package kosta.namtang.talkbook.repository;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.BookCount;
import kosta.namtang.talkbook.model.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountRepository extends CrudRepository<BookCount,Long> {
    @Modifying
    @Query("update BookCount As b SET b.totalCount = b.totalCount+1 where b.bookIdx = ?1")
    public void totalCountup(Long bookIdx);

    @Modifying
    @Query("update BookCount As b SET b.monthCount = b.monthCount+1 where b.bookIdx = ?1")
    public void MonthCountup(Long bookIdx);

    @Query("select b from BookCount As b order by b.totalCount DESC")
    public Page<BookCount> findBestSeller(Pageable pageable);

    @Query("select b from BookCount As b order by b.monthCount DESC")
    public Page<BookCount> findMonthbestSeller(Pageable pageable);
}

