package kosta.namtang.talkbook.model.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {

    @Id
    @SequenceGenerator(name = "review_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "review_seq")
    private Long reviewIdx;

    private Long bookIdx;

    private String reviewTitle;
    private String reviewContent;

    @CreationTimestamp
    private Date reviewDate;

    @UpdateTimestamp
    private Date reviewModify;
    private int reviewScore;

    private String reviewerId;


//    리뷰테이블
//
//    작성자
//    제목
//    내용
//    상품인덱스
//    등록일
//    수정일
//    별점


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
