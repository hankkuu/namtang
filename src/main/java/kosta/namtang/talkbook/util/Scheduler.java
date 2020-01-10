package kosta.namtang.talkbook.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kosta.namtang.talkbook.model.domain.Statistics;
import kosta.namtang.talkbook.service.AdminService;

@Component
public class Scheduler {
	@Autowired
	AdminService adminService;
	
	   @Scheduled(cron = "* * 23 * * ?")
	   public void cronJobSch() {
	      SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      System.out.println("Java cron job expression:: " + strDate+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	      adminService.insertStatistics(new Statistics(2502, strDate, 222));
	   }
}
