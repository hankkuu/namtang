package kosta.namtang.talkbook.repository;

import kosta.namtang.talkbook.model.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByUserIdx(Long idx);

    List<Review> findByBookIdx(Long idx);

    List<Review> findByReviewTitle(String title);

    List<Review> findByReviewContent(String content);

    void deleteByUserIdx(Long idx);

    void deleteByBookIdx(Long idx);

    Review findByUserIdxAndBookIdx(long userIdx, long bookIdx);

}
