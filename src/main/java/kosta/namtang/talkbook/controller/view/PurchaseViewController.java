package kosta.namtang.talkbook.controller.view;

import kosta.namtang.talkbook.service.bill.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("user")
public class PurchaseViewController {

	@RequestMapping("/purchase")
	public ModelAndView checkout(){
		log.debug("purchase");
		ModelAndView view = new ModelAndView("/user/checkout");
		return view;
	}

	@RequestMapping("/confirmation")
	public ModelAndView confirmation(String param){
		log.debug("confirmation");
		ModelAndView view = new ModelAndView("/user/confirmation", "params", param);
		return view;
	}

}
