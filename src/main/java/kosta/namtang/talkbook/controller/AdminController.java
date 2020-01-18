package kosta.namtang.talkbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kosta.namtang.talkbook.model.domain.Admin;
import kosta.namtang.talkbook.model.domain.Statistics;
import kosta.namtang.talkbook.model.domain.bill.PurchaseOrder;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.bill.PurchaseOrderRepository;
import kosta.namtang.talkbook.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;
    
    BookRepository bookRepository;
    
    @Autowired
    private PurchaseOrderRepository purchaseOrder;
    
    @RequestMapping("/getlist")
    @ResponseBody
    public List<PurchaseOrder> getlist(){
    	List<PurchaseOrder> list = (List<PurchaseOrder>) purchaseOrder.findAll();
    	
		return list;
    	
    }

    @RequestMapping("dashBoard")
    public String dashBoard(Model model) {
        List<Statistics> list = service.selectAll();
        model.addAttribute("list", list);
        
        return "admin/dashBoard";
    }

    @RequestMapping("adminDOM")
    public String adminDOM(Model model) {
        return "admin/adminDOM";
    }

    //	@RequestMapping("adminloginCheck")
//	public String adminloginCheck(Admin admin) {
//		
//		System.out.println(admin +"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
//		//service.selectById(admin); 
//		return "/";
//		
//	}
    @RequestMapping("/adminLoginForm")
    public String adminlogin(Admin admin) {

        System.out.println(admin + "불꽅남자");
        //service.selectById(admin);
        return "/adminLoginForm";

    }


}
