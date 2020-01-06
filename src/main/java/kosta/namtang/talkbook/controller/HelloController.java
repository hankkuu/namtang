package kosta.namtang.talkbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
//    @RequestMapping("/")
//    public String index() {
//        return "index";
//    }

    @RequestMapping("/")
    public String aa(){
        System.out.println("여기까지는 옴!");
        return "aroma/indexx";}


}
