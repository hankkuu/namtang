package kosta.namtang.talkbook.service;

import kosta.namtang.talkbook.model.domain.Review;
import kosta.namtang.talkbook.model.dto.ReviewDTO;
import kosta.namtang.talkbook.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repo;


    public void insert(Review review){

        repo.save(review);

    }

    public void update(Review review){

        List<Review> reviews = repo.findByBookIdx(review.getBookIdx());
        for(Review a : reviews){
            if (a.getUserIdx() == review.getUserIdx()) {
                String reTitle = review.getReviewTitle();
                String reContent = review.getReviewContent();
                int reScore = review.getReviewScore();

                a.setReviewTitle(reTitle);
                a.setReviewContent(reContent);
                a.setReviewScore(reScore);

                repo.save(a);
            }
        }
    }


    public List<Review> selectUser(Long idx){

       List<Review> rv = repo.findByUserIdx(idx);


        return rv;
    }

    public List<Review> selectReview(Long idx){

        List<Review> rv = repo.findByBookIdx(idx);

        return rv;
    }

    public void delete(Review review) {

        List<Review> reviews = repo.findByBookIdx(review.getBookIdx());
        for (Review a : reviews) {
            if (a.getUserIdx() == review.getUserIdx()) {

                    repo.delete(a);

            }
        }
    }



}
