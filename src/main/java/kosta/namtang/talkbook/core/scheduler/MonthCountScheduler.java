package kosta.namtang.talkbook.core.scheduler;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.BookCount;
import kosta.namtang.talkbook.model.domain.BookCountHistory;
import kosta.namtang.talkbook.model.domain.BookCountHistoryId;
import kosta.namtang.talkbook.repository.CountHistoryRepository;
import kosta.namtang.talkbook.repository.CountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Component
public class MonthCountScheduler{

    @Autowired
    CountRepository countRepo;
    @Autowired
    CountHistoryRepository countHistoryRepo;

    @Scheduled(cron = "0 0 0 1 * *")
    public void scheduleTest() {
        System.out.println("***************************MonthCount 스케줄러 동작 확인******************************");

        //새로운 달이 되면
        Pageable page = PageRequest.of(0,8);
        Page<BookCount> bookCount = countRepo.findMonthbestSeller(page);
        List<Book> list= new ArrayList<Book>();

        //현재 년/월 구하기
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM");
        String strDate = format.format(date);
        System.out.println("현재 년/월은 : " + strDate + "입니다.");

        //Month날짜 받기
        for(BookCount b : bookCount.getContent()) {
            BookCountHistory bh = countHistoryRepo.save(new BookCountHistory(new BookCountHistoryId(b.getBookIdx(),strDate),b.getMonthCount()));
        }


        //Month날짜 초기화
        for(BookCount b: bookCount.getContent()){
            b.setMonthCount(0);
            countRepo.save(b);
        }
    }
}