package kosta.namtang.talkbook.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("user")
public class AccountViewController {

    @RequestMapping("/login")
    public ModelAndView login(){
        log.debug("login");
        ModelAndView view = new ModelAndView("guest/login");
        return view;
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        log.debug("register");
        ModelAndView view = new ModelAndView("guest/register");
        return view;
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        log.debug("register");
        ModelAndView view = new ModelAndView("guest/index");
        return view;
    }
}
