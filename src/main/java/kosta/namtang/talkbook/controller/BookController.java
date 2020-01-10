package kosta.namtang.talkbook.controller;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("aroma/category")
    String category(Model model){
        Pageable page = PageRequest.of(0, 9);
        Page<Book> pageList = bookService.selectAll(page);

        model.addAttribute("booklist",pageList.getContent());
        model.addAttribute("list", bookService.countAll());

        model.addAttribute("maxPage", pageList.getTotalPages());
        model.addAttribute("startPage",1);
        model.addAttribute("curPage",1);
        return "aroma/category";
    }

    @RequestMapping("/catgCall")
    @ResponseBody
    List<Object> catgCall(Long CatgIdx, int PageNum, HttpSession request){
        Pageable page = PageRequest.of(PageNum-1, 9);

        Page<Book> bookList;
        List<Integer> pageInfo = new ArrayList<Integer>();
        List<Object> list = new ArrayList<Object>();
        if(CatgIdx==0L) {
            bookList = bookService.selectAll(page);
        } else {
            bookList = bookService.CatgCall(CatgIdx, page);
        }
        int maxPage = bookList.getTotalPages();
        int startPage = PageNum - (PageNum%10) + 1;
        pageInfo.add(startPage);
        pageInfo.add(maxPage);
        pageInfo.add(PageNum);

        list.add(bookList.getContent());
        list.add(pageInfo);

        return list;
    }
}
