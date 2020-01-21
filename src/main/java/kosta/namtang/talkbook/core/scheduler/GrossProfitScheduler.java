package kosta.namtang.talkbook.core.scheduler;

import kosta.namtang.talkbook.model.domain.bill.PurchasePayment;
import kosta.namtang.talkbook.repository.bill.PurchasePaymentRepository;
import kosta.namtang.talkbook.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class GrossProfitScheduler {
    @Autowired
    AdminService adminService;
    @Autowired
    ServletContext servletContext;

    @Autowired
    PurchasePaymentRepository purchasePaymentRepository;

    @Scheduled(cron = "59 59 23 * * ?")
    public void cronJobSch() {
        BigDecimal total = new BigDecimal("0");
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        List<PurchasePayment> list = (List<PurchasePayment>) purchasePaymentRepository.findAll();
        for (int i = 0; i <= list.size() - 1; i++) {
            total = total.add(list.get(i).getTotalPrice());
        }
        Date now = new Date();
        String strDate = sdf.format(now);
        if (servletContext.getAttribute("userCount") == null) {
            servletContext.setAttribute("userCount", 0);
        }
        int usercounttotal = (int) servletContext.getAttribute("userCount");
        System.out.println("오늘 마감 시작 : " + strDate + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //adminService.insertStatistics(new Statistics(total.intValue(), strDate, usercounttotal));
        servletContext.setAttribute("userCount", 0);
    }
}
