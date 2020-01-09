package kosta.namtang.talkbook.controller;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("aroma/category")
    String category(Model model){
        Pageable page = PageRequest.of(0, 9);
        Page<Book> pageList = bookService.selectAll(page);

        model.addAttribute("booklist",pageList.getContent());
//        model.addAttribute("booklist",bookService.selectAll());
        model.addAttribute("list", bookService.countAll());
        return "aroma/category";
    }

    @RequestMapping("/catgCall")
    @ResponseBody
    List<Book> catgCall(Long CatgIdx, int PageNum){
        System.out.println(CatgIdx);
        System.out.println(PageNum);
        Pageable page = PageRequest.of(PageNum-1, 9);
        if(CatgIdx==0L){
            return bookService.selectAll(page).getContent();
        }
        return bookService.CatgCall(CatgIdx,page).getContent();
    }
}
