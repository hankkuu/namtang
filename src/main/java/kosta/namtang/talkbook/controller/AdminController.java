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
		System.out.println(list.get(1).getSaleDate());
		model.addAttribute("list",list);
		return "admin/dashBoard";
	}
	
	@RequestMapping("adminDOM")
	public String adminDOM(Model model) {
		return "admin/adminDOM";
	}
	
	

}
