package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.model.domain.Review;
import kosta.namtang.talkbook.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

	@Autowired
	ReviewService reviewService;
	
	@RequestMapping("/")
	public String mainIndex() {
		
		return "guest/index";
	}

	
	@RequestMapping("detail")
	public ModelAndView detail() {
		System.out.println("메인 디테일 들어옴.....");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("aroma/productDetail");

		List<Review> rv = reviewService.selectUser(1L);


		mv.addObject("reviewcc", rv);

		return mv;
	}




}
