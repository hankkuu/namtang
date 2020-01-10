package kosta.namtang.talkbook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {


    private Long reviewIdx;
    private Long userIdx;
    private String reviewTitle;
    private String reviewContent;
    private Long bookIdx;
    private int reviewScore;
    private String reviewDate;
    private String reviewModify;


//    리뷰테이블
//
//    리뷰인덱스
//    작성자
//    제목
//    내용
//    상품인덱스
//    별점
//    등록일
//    수정일


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
