package kosta.namtang.talkbook.controller;


import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.Review;
import kosta.namtang.talkbook.model.dto.ReviewDTO;
import kosta.namtang.talkbook.service.BookService;
import kosta.namtang.talkbook.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Controller
public class ReviewController {

    @Autowired
    ReviewService service;
    @Autowired
    BookService bookService;

    @RequestMapping("reviewReg")
    public ModelAndView insert(ReviewDTO dto, HttpServletRequest req) {

        Object idxobj = req.getSession().getAttribute("userIdx");
        String idxstr = (String) idxobj;
        Long uIdx = Long.valueOf(idxstr);

        // 리뷰 저장
        String title = dto.getReviewTitle();
        String content = dto.getReviewContent();
        Long bookIdx = dto.getBookIdx();
        int reviewScore = dto.getReviewScore();

        Review review = new Review(null, uIdx, title, content, bookIdx, reviewScore, null, null);

        service.insert(review);

        // 저장 후 페이지 돌아오기
        ModelAndView mv = new ModelAndView();
        Optional<Book> book = bookService.BookDetail(bookIdx);
        mv.addObject("book", book);
        mv.setViewName("/guest/productDetail");

        //리뷰 뿌리기
        List<Review> rv = service.selectReview(bookIdx);
        mv.addObject("reviewcc", rv);

        return mv;

    }

    @RequestMapping("reviewModi")
    public ModelAndView update(ReviewDTO dto, HttpServletRequest req) {

        Object idxobj = req.getSession().getAttribute("userIdx");
        String idxstr = (String) idxobj;
        Long uIdx = Long.valueOf(idxstr);

        // 리뷰 저장
        String title = dto.getReviewTitle();
        String content = dto.getReviewContent();
        Long bookIdx = dto.getBookIdx();
        int reviewScore = dto.getReviewScore();

        Review review = new Review(null, uIdx, title, content, bookIdx, reviewScore, null, null);

        service.update(review);

        // 저장 후 페이지 돌아오기
        ModelAndView mv = new ModelAndView();
        Optional<Book> book = bookService.BookDetail(bookIdx);
        mv.addObject("book", book);
        mv.setViewName("/guest/productDetail");

        //리뷰 뿌리기
        List<Review> rv = service.selectReview(bookIdx);
        mv.addObject("reviewcc", rv);

        return mv;

    }

    @RequestMapping("/reviewDel")
    public String delete(@RequestParam String reviewTitle, @RequestParam String reviewContent, Long bookIdx, HttpServletRequest req){

        Object idxobj = req.getSession().getAttribute("userIdx");
        String idxstr = (String) idxobj;
        Long uIdx = Long.valueOf(idxstr);

        // 리뷰 저장
        Review review = new Review(null, uIdx, reviewTitle, reviewContent, bookIdx, 2, null, null);

        service.delete(review);

        // 저장 후 페이지 돌아오기
        ModelAndView mv = new ModelAndView();
        Optional<Book> book = bookService.BookDetail(bookIdx);
        mv.addObject("book", book);
        mv.setViewName("/guest/productDetail");

        //리뷰 뿌리기
        List<Review> rv = service.selectReview(bookIdx);
        mv.addObject("reviewcc", rv);

        return "good";

    }

}