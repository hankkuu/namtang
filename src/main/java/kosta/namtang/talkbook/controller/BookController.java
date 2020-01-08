package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("aroma/category")
    String category(Model model){
        model.addAttribute("booklist",bookService.selectAll());
        System.out.println(3);
        return "aroma/category";
    }
}
