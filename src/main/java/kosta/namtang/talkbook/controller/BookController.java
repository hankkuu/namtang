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
import org.springframework.web.bind.annotation.RequestParam;
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
    String category(@RequestParam Long catg, Model model){
        Pageable page = PageRequest.of(0, 9);
        Page<Book> pageList = null;
        if(catg==0) {
           pageList = bookService.selectAll(page,1);
        }else {
            pageList = bookService.CatgCall(catg,1,page);
        }

        model.addAttribute("CatgIdx",catg);
        model.addAttribute("booklist",pageList.getContent());
        model.addAttribute("list", bookService.countAll());

        model.addAttribute("maxPage", pageList.getTotalPages());
        model.addAttribute("startPage",1);
        model.addAttribute("curPage",1);
        return "aroma/category";
    }


    @RequestMapping("/catgCall")
    @ResponseBody
    List<Object> catgCall(Long CatgIdx, int PageNum, int Ordering, HttpSession request){
        Pageable page = PageRequest.of(PageNum-1, 9);
        System.out.printf(CatgIdx + "|"+ PageNum +" | " + Ordering);

        Page<Book> bookList;
        List<Integer> pageInfo = new ArrayList<Integer>();
        List<Object> list = new ArrayList<Object>();

        //카테고리 : 전체목록 선택
        if(CatgIdx==0L) {
            bookList = bookService.selectAll(page,Ordering);
        }
        //카테고리 : 특정 카테고리 선택
        else {
            bookList = bookService.CatgCall(CatgIdx,Ordering, page);
        }
        int maxPage = bookList.getTotalPages();
        if(PageNum % 10 ==0) PageNum -=10;
        int startPage = PageNum - (PageNum%10) + 1;

        pageInfo.add(startPage);
        pageInfo.add(maxPage);
        pageInfo.add(PageNum);

        list.add(bookList.getContent());
        list.add(pageInfo);

        return list;
    }
}
