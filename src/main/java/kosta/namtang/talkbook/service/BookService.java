package kosta.namtang.talkbook.service;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.BookCount;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CategoryRepository;
import kosta.namtang.talkbook.repository.CountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepo;
    @Autowired
    CategoryRepository catgRepo;
    @Autowired
    CountRepository countRepo;

    public Page<Book> selectAll(int PageNum, int Ordering) {
        Page<Book> book = null;

        List<String> list = new ArrayList<String>();
        list.add("Title");
        Pageable page = PageRequest.of(PageNum - 1, 9, Sort.Direction.ASC, "bookTitle");
//        PageRequest page = new PageRequest(PageNum-1,9,new Sort(Sort.Direction.ASC,list));
        if(Ordering==1) {
            page = PageRequest.of(PageNum-1,9,Sort.Direction.ASC,"bookTitle");
            book = bookRepo.findAll(page);
        }
        else if(Ordering==2){
            page = PageRequest.of(PageNum-1,9,Sort.Direction.DESC,"bookTitle");
            book = bookRepo.findAll(page);
        }
        else if(Ordering==3){
            page = PageRequest.of(PageNum-1, 9, Sort.by("bookPrice").ascending().and(Sort.by("bookTitle")));
            book = bookRepo.findAll(page);
        }
        else if(Ordering==4){
            page = PageRequest.of(PageNum-1, 9, Sort.by("bookPrice").descending().and(Sort.by("bookTitle")));
            book = bookRepo.findAll(page);
        }
        else if(Ordering==5){
            page = PageRequest.of(PageNum-1, 9, Sort.by("bookPubdate").ascending().and(Sort.by("bookTitle")));
            book = bookRepo.findAll(page);
        }
        return book;
    }

    public List<Book> selectAll(){
        Iterable<Book> it = bookRepo.findAll();

        List<Book> dtoList = new ArrayList<Book>();
        Iterator<Book> ito = it.iterator();
        while(ito.hasNext()) {
        	Book go = ito.next();
        	dtoList.add(go);
//            System.out.println(go);
        }

        return dtoList;
    }

    public List<Integer> countAll(){
        List<Integer> list = new ArrayList<Integer>();
        for(long i = 1L; i<= 10 ; i++) {
            list.add(bookRepo.countBookByCategory(catgRepo.findById(i)));
        }
        list.add((int) bookRepo.count());

        return list;
     }
     public Page<Book> CatgCall(Long CatgIdx, int PageNum, int Ordering){
         Page<Book> book=null;
         Pageable page = null;
        if(Ordering==1){
            page = PageRequest.of(PageNum-1,9,Sort.Direction.ASC,"bookTitle");
            book = bookRepo.findAllByCategory(catgRepo.findById(CatgIdx),page);
        }
        else if(Ordering==2) {
            page = PageRequest.of(PageNum-1,9,Sort.Direction.DESC,"bookTitle");
            book = bookRepo.findAllByCategory(catgRepo.findById(CatgIdx),page);
        }
        else if(Ordering==3){
            System.out.println("여기왔다.....................................");
            System.out.printf(CatgIdx + " | " + PageNum + " | " + Ordering + " | " );

            page = PageRequest.of(PageNum-1, 9, Sort.by("bookPrice").ascending().and(Sort.by("bookTitle")));
            book = bookRepo.findAllByCategory(catgRepo.findById(CatgIdx), page);
        }
        else if(Ordering==4){
            page = PageRequest.of(PageNum-1, 9, Sort.by("bookPrice").descending().and(Sort.by("bookTitle")));
            book = bookRepo.findAllByCategory(catgRepo.findById(CatgIdx),page);
        }
        else if(Ordering==5) {
            page = PageRequest.of(PageNum-1, 9, Sort.by("bookPubdate").ascending().and(Sort.by("bookTitle")));
            book = bookRepo.findAllByCategory(catgRepo.findById(CatgIdx),page);
        }
        return book;
     }
        public Page<Book> SearchWord(int PageNum, int Ordering, String Word) {
            Page<Book> book=null;
            Pageable page = PageRequest.of(PageNum-1,9);;
            Word = "%"+Word+"%";
            if(Ordering==6) book=bookRepo.searchByTitleAll(Word,page);
            else if(Ordering==7) book=bookRepo.searchByAuthorAll(Word,page);
            else if(Ordering==8) book=bookRepo.searchByPublisherAll(Word,page);
            return book;
        }

        public Page<Book> SearchWordByCatg(Long CatgIdx, int PageNum, int Ordering, String Word){
            Page<Book> book=null;
            Pageable page = PageRequest.of(PageNum-1,9);;
            Word = "%"+Word+"%";
            if(Ordering==6) book = bookRepo.searchByTitle(catgRepo.findById(CatgIdx), Word, page);
            else if(Ordering==7) book=bookRepo.searchByAuthor(catgRepo.findById(CatgIdx),Word,page);
            else if(Ordering==8) book=bookRepo.searchByPublisher(catgRepo.findById(CatgIdx),Word,page);
            return book;

        }

        @Transactional
        public Optional<Book> BookDetail(Long bookIdx){
            countRepo.totalCountup(bookIdx);
            countRepo.MonthCountup(bookIdx);
             return bookRepo.findById(bookIdx);
        }

    @Transactional
    public List<Book>findBestSeller(){
        Pageable page = PageRequest.of(0,8);
        Page<BookCount> bookCount = countRepo.findBestSeller(page);
        List<Long> idList= new ArrayList<>();
        for(BookCount b : bookCount.getContent()) {
            idList.add(b.getBookIdx());
        }
        List<Book> book = bookRepo.findBestCount(idList);
//            List<Book> book = bookRepo.findById
        return book;
    }
    @Transactional
    public List<Book>findMonthBestSeller(){
        Pageable page = PageRequest.of(0,8);
        Page<BookCount> bookCount = countRepo.findMonthbestSeller(page);
        List<Long> idList= new ArrayList<>();
        for(BookCount b : bookCount.getContent()) {
            idList.add(b.getBookIdx());
        }
        List<Book> book = bookRepo.findBestCount(idList);
//            List<Book> book = bookRepo.findById
        return book;
    }

    public Page<Book> findThemeCatg(int type){
        Pageable page = PageRequest.of(0,50);
        Page<Book> themeBook = bookRepo.findThemeCatg(type,page);
        return themeBook;
    }


    }
