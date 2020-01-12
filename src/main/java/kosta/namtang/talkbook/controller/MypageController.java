package kosta.namtang.talkbook.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {

    @RequestMapping("/aroma/mypage")
    void mypage(){
    }

    @RequestMapping("/aroma/recentBook")
    void recentBook(){
    }
}
