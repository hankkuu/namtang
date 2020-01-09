package kosta.namtang.talkbook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @SequenceGenerator(name = "seq", allocationSize = 1)
    private Long reviewIdx;
    private String reviewTitle;
    private String reviewContent;
    private String reviewDate;
    private String reviewModify;
    private int reviewScore;
    private String reviewerId;



//    리뷰 테이블
//    인덱스 pk - review_idx (number)
//    리뷰 제목 - review_title (varchar2)
//    리뷰 내용 - review_content (varchar2)
//    리뷰 작성일 - review_date (date)
//    리뷰 수정일 - review_modify (date)
//    리뷰 평점 - review_score (number)
//    리뷰 작성자 ID FK - reviewer_id (varchar2)
//    (user 테이블의 user_id 사용)

}
