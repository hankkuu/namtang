package kosta.namtang.talkbook.controller;


import kosta.namtang.talkbook.model.dto.ReviewDTO;
import kosta.namtang.talkbook.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ReviewController {

    @Autowired
    ReviewService service;

    @PostMapping("/reviewReg")
    public void insert(ReviewDTO dto){

//        dto.get
//
//
//        service.insert(dto);
    }

}
