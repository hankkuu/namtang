package kosta.namtang.talkbook.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class AccountViewController {

    @RequestMapping("login")
    public ModelAndView login(){
        log.debug("login");
        ModelAndView view = new ModelAndView("/guest/login");
        return view;
    }

    @RequestMapping("register")
    public ModelAndView register(){
        log.debug("register");
        ModelAndView view = new ModelAndView("/guest/register");
        return view;
    }
    
    @RequestMapping("findPassword")
    public ModelAndView findPassword(){
        log.debug("findPassword");
        ModelAndView view = new ModelAndView("/guest/findPassword");
        return view;
    }
 
    @RequestMapping(value = "user/logout")
    public ModelAndView logout() {
        log.debug("logout");
        ModelAndView view = new ModelAndView("/guest/index");
        return view;
    }
    
//    @RequestMapping("update")
//	public ModelAndView update(){
//		log.debug("update");
//		ModelAndView view = new ModelAndView("/guest/update");
//		return view;
//	}
}
