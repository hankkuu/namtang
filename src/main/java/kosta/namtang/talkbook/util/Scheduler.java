package kosta.namtang.talkbook.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.ws.spi.http.HttpContext;

import org.apache.velocity.runtime.directive.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kosta.namtang.talkbook.model.domain.Statistics;
import kosta.namtang.talkbook.model.domain.bill.PurchasePayment;
import kosta.namtang.talkbook.repository.bill.PurchasePaymentRepository;
import kosta.namtang.talkbook.service.AdminService;

@Component
public class Scheduler {
	@Autowired
	AdminService adminService;
	
	@Autowired
	PurchasePaymentRepository purchasePaymentRepository;
	
	   @Scheduled(cron = "1 * * * * ?")
	   public void cronJobSch() {
		  BigDecimal total = new BigDecimal("0");
	      SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
	      List<PurchasePayment> list = (List<PurchasePayment>)purchasePaymentRepository.findAll();
	      for(int i=0;i<=list.size()-1;i++) {
	    	total = total.add(list.get(i).getTotalPrice());
	      }
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      System.out.println("오늘 마감 시작 : " + strDate+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	      //adminService.insertStatistics(new Statistics(total.intValue(), strDate, 222));
	   }
}
