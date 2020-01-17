package kosta.namtang.talkbook.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {

    @RequestMapping("/mypage")
    String mypage() {
        return "/user/mypage";
    }

    @RequestMapping("/mypage/recentBook")
    String recentBook() {
        return "/user/recentBook";
    }
}
