package kosta.namtang.talkbook.controller;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.Review;
import kosta.namtang.talkbook.service.BookService;
import kosta.namtang.talkbook.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping("/category")
    String category(@RequestParam Long catg, Model model) {
        Page<Book> pageList = null;
        if (catg == 0) {
            pageList = bookService.selectAll(1, 1);
        } else {
            pageList = bookService.CatgCall(catg, 1, 1);
        }

        model.addAttribute("CatgIdx", catg);
        model.addAttribute("booklist", pageList.getContent());
        model.addAttribute("list", bookService.countAll());

        model.addAttribute("maxPage", pageList.getTotalPages());
        model.addAttribute("startPage", 1);
        model.addAttribute("curPage", 1);
        return "/guest/category";
    }


    @RequestMapping("/catgCall")
    @ResponseBody
    List<Object> catgCall(Long CatgIdx, int PageNum, int Ordering, String Word) {

        //System.out.printf(CatgIdx + " | " + PageNum + " | " + Ordering + " | " + Word);

        Page<Book> bookList;
        List<Integer> pageInfo = new ArrayList<Integer>();
        List<Object> list = new ArrayList<Object>();

        //카테고리 : 전체목록 선택
        if (CatgIdx == 0L) {
            if (Ordering >= 6) {
                bookList = bookService.SearchWord(PageNum, Ordering, Word);
            } else {
                bookList = bookService.selectAll(PageNum, Ordering);
            }
        }
        //카테고리 : 특정 카테고리 선택
        else {
            if (Ordering >= 6) {
                bookList = bookService.SearchWordByCatg(CatgIdx, PageNum, Ordering, Word);
            } else {
                System.out.printf(CatgIdx + " | " + PageNum + " | " + Ordering + " | " + Word);
                bookList = bookService.CatgCall(CatgIdx, PageNum, Ordering);
            }
        }
        int maxPage = bookList.getTotalPages();
        if (PageNum % 10 == 0) PageNum -= 10;
        int startPage = PageNum - (PageNum % 10) + 1;

        pageInfo.add(startPage);
        pageInfo.add(maxPage);
        pageInfo.add(PageNum);

        list.add(bookList.getContent());
        list.add(pageInfo);

        return list;
    }

    @RequestMapping("/BookDetail")
    ModelAndView catgCall(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();

        Optional<Book> book = bookService.BookDetail(id);
        mv.addObject("book",book);
        mv.setViewName("/guest/productDetail");

        //리뷰
        List<Review> rv = reviewService.selectUser(1L);
        mv.addObject("reviewcc", rv);

        return mv;
    }
}
