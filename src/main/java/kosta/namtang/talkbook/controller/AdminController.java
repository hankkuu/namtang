package kosta.namtang.talkbook.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import kosta.namtang.talkbook.model.dto.response.PurchaseOrderStatReponse;
import kosta.namtang.talkbook.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import kosta.namtang.talkbook.model.domain.Admin;
import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.Statistics;
import kosta.namtang.talkbook.model.domain.bill.PurchaseOrder;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.bill.PurchaseOrderRepository;
import kosta.namtang.talkbook.service.AdminService;
import kosta.namtang.talkbook.service.BookService;
import lombok.Data;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;
    @Autowired
    private BookService bookService;
    
    @Autowired
    private PurchaseOrderRepository purchaseOrder;
    
    @RequestMapping("/getlist")
    @ResponseBody
    public List<PurchaseOrderStatReponse> getlist() throws Exception {
    	Iterable<PurchaseOrder> list = purchaseOrder.findAll();
    	List<PurchaseOrder> l = Lists.newArrayList(list);
        List<PurchaseOrderStatReponse> list2 = new ArrayList<>();
    	for(PurchaseOrder order : l) {
            PurchaseOrderStatReponse response = JsonUtil.fromJson(JsonUtil.toJson(order), PurchaseOrderStatReponse.class);
            response.setStatusName(order.getStateCode().name());
            list2.add(response);
    	}
    	
    	
		return list2;
    	
    }
    
    @RequestMapping("/getlist2")
    @ResponseBody
    public List<Book> getlist2(){
    	List<Book> list = bookService.findBestSeller();
		return list;
    	
    }
    
    @RequestMapping("/getlist3")
    @ResponseBody
    public List<Book> getlist3(){
    	List<Book> list = bookService.findMonthBestSeller();
		return list;
    	
    }

    @RequestMapping("dashBoard")
    public String dashBoard(Model model) {
        List<Statistics> list = service.selectAll();
        model.addAttribute("list", list);
        
        return "admin/dashBoard";
    }
    
    @RequestMapping("dashBoard2")
    public String dashBoard2(Model model) {
        List<Statistics> list = service.selectAll();
        model.addAttribute("list", list);
        
        return "admin/dashBoard2";
    }
    @RequestMapping("dashBoard3")
    public String dashBoard3(Model model) {
        List<Statistics> list = service.selectAll();
        model.addAttribute("list", list);
        
        return "admin/dashBoard3";
    }

    @RequestMapping("adminDOM")
    public String adminDOM(Model model) {
        return "admin/adminDOM";
    }

    @RequestMapping("/adminLoginForm")
    public String adminlogin(Admin admin) {

        System.out.println(admin + "불꽅남자");
        //service.selectById(admin);
        return "/adminLoginForm";

    }
}

