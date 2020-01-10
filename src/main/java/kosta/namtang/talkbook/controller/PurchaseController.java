package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.service.bill.PurchaseService;
import kosta.namtang.talkbook.util.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@RequestMapping("purchase")
	public void purchase(){

	}

}
