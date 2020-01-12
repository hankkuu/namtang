package kosta.namtang.talkbook.controller;


import kosta.namtang.talkbook.model.domain.Review;
import kosta.namtang.talkbook.model.dto.ReviewDTO;
import kosta.namtang.talkbook.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ReviewController {

    @Autowired
    ReviewService service;

    @RequestMapping("reviewReg")
    public ModelAndView insert(ReviewDTO dto){
        System.out.println("들어오나?");
        String title = dto.getReviewTitle();
        String content = dto.getReviewContent();

        Review review = new Review(null, 1L, title, content, 100L, 5, null, null);

        service.insert(review);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("aroma/productDetail");

        return mv;

    }

}
