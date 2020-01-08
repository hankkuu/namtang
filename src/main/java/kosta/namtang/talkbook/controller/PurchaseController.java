package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.service.bill.PurchaseService;
import kosta.namtang.talkbook.util.UserSessionUtil;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class PurchaseController {

	private static PurchaseService purchaseService;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		boolean isLogin = UserSessionUtil.isLogined(request.getSession());
		if(isLogin == false) {
			//throw new GlobalException("로그인되지 않은 사용자입니다", StatusCode.Not_Access_User);
		}

		// json 변환이 필요함
		String route = request.getParameter("route");
		String data = request.getParameter("data");
		String httpMethod = request.getMethod();
		
		//Logback.debug(httpMethod + " => route : " + route + " ||  isLogin : " + isLogin);

		ModelAndView mv = new ModelAndView();
		

		// result를 json으로 만들어 줘야 함

		return mv;
	}

}
