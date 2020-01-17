package kosta.namtang.talkbook.controller;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.service.BookService;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kosta.namtang.talkbook.model.domain.Review;
import kosta.namtang.talkbook.service.ReviewService;

@Controller
public class MainController {

	@Autowired
	ReviewService reviewService;
	@Autowired
	BookService bookService;
	
	@RequestMapping("/")
	public String mainIndex(Model model) {
		List<Book> bestSeller = bookService.findBestSeller();
		List<Book> MonthbestSeller = bookService.findMonthBestSeller();

		model.addAttribute("booklist", bestSeller);
		model.addAttribute("booklist2", MonthbestSeller);

		return "/guest/index";
	}

	@RequestMapping("/Contact")
	public String contactUs(){
		return "/guest/contact";
	}
	
	@RequestMapping("BGM")
	public String BGM() {
		return "guest/BGM";
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail() {
		System.out.println("메인 디테일 들어옴.....");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("aroma/productDetail");

		List<Review> rv = reviewService.selectUser(1L);

		mv.addObject("reviewcc", rv);

		return mv;
	}
	
	
	@RequestMapping("/weather")
	@ResponseBody
	//@CrossOrigin("https://samples.openweathermap.org/data/2.5/weather")
	public Object aa(Double lat ,Double lon) {
		System.out.println("lat " + lat +", lon="+ lon);
	    Object obj=null;
		//String url = "https://samples.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=16cc01d3837e13da9db7e6bdf5fc6c8c";
		try{     
			String url = "https://samples.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=16cc01d3837e13da9db7e6bdf5fc6c8c";
			URL postUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection)postUrl.openConnection();
			 obj = JSONValue.parse(new InputStreamReader(con.getInputStream()));		
			
			
			//org.json.simple.JSONArray jObj = (org.json.simple.JSONArray) obj;			
			//ObjectMapper om = new ObjectMapper();	
			System.out.println("11111" + obj);
			/*
			System.out.println("jObj.size() = " + jObj.size());	
			for (int i = 0; i < jObj.size(); i++) {
				//받고싶은 타입 ex)Map dbparams  = new Map();
				//넣고 싶은 타입ex) Map
				 //om.readValue(jObj.get(i).toString(), new TypeReference<Map>(){});
				// data insert dao.dbinsert ( dbpras); 이런식으로 해당 배열을 각 맵에 담아서 db insert할 수 있음
			}*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//URL url = UR
		return obj;
	}
}







