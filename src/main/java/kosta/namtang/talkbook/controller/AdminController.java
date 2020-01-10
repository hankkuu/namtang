package kosta.namtang.talkbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import kosta.namtang.talkbook.model.domain.Admin;
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
		model.addAttribute("list",list);
		
		return "admin/dashBoard"; 
	}
	/*
	@RequestMapping("dashBoardList")
	@ResponseBody
	public String dashBoardList(HttpServletRequest request,Model model) throws JsonGenerationException, JsonMappingException, IOException { //?address
		List<Statistics> list = service.selectAll();
		
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        // Serialize Object to JSON.
        return mapper.writeValueAsString(list);
	}
	*/

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
		
		System.out.println(admin +"불꽅남자");
		//service.selectById(admin); 
		return "/adminLoginForm";
		
	}
	

}
