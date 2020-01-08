package kosta.namtang.talkbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.namtang.talkbook.model.domain.Statistics;
import kosta.namtang.talkbook.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
    private	AdminService service;
	
	@RequestMapping("dashBoard")
	public String dashBoard(Model model) {
		List<Statistics> list = service.selectAll();
		System.out.println(list.size());
		model.addAttribute("list",list);
//		list.get(0).getStatIdx();
//		list.get(0).getSalePrice()
//		list.get(0).getUserCount()
		return "admin/dashBoard";
	}
	
	@RequestMapping("adminDOM")
	public String adminDOM(Model model) {
		return "admin/adminDOM";
	}
	
	

}
