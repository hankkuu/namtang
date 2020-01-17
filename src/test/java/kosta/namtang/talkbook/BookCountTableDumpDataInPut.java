package kosta.namtang.talkbook;

import com.google.common.collect.Lists;
import kosta.namtang.talkbook.common.BookCode;
import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.BookCount;
import kosta.namtang.talkbook.model.domain.BookCountHistory;
import kosta.namtang.talkbook.model.domain.BookCountHistoryId;
import kosta.namtang.talkbook.repository.CountHistoryRepository;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class BookCountTableDumpDataInPut {


    @Autowired
    BookRepository bookRepo;
    @Autowired
    CountRepository countRepo;
    @Autowired
    CountHistoryRepository countHistoryRepo;

    @Test
    void inputData(){
       Iterable<Book> book = bookRepo.findAll();
        List<Book> myList = Lists.newArrayList(book);

        for(Book b : myList){
            Long id = b.getBookIdx();
            countRepo.save(new BookCount(id,0,0));
        }
    }

    @Test
    public void scheduleTest() {
        System.out.println("***************************MonthCount 스케줄러 동작 확인******************************");

        //새로운 달이 되면
        Pageable page = PageRequest.of(0,8);
        Page<BookCount> bookCount = countRepo.findMonthbestSeller(page);
        List<Book> list= new ArrayList<>();

        //현재 년/월 구하기
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM");
        String strDate = format.format(date);
        System.out.println(strDate);

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
