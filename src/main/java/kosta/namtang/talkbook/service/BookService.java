package kosta.namtang.talkbook.service;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.Category;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepo;
    @Autowired
    CategoryRepository catgRepo;

    public Page<Book> selectAll(int PageNum, int Ordering){
        Page<Book> book = null;

        List<String> list = new ArrayList<String>();
        list.add("Title");
        Pageable page = PageRequest.of(PageNum-1,9,Sort.Direction.ASC,"bookTitle");
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
            page = PageRequest.of(PageNum-1,9,Sort.Direction.ASC,"bookPrice");
            book = bookRepo.findAll(page);
        }
        else if(Ordering==4){
            page = PageRequest.of(PageNum-1,9,Sort.Direction.DESC,"bookPrice");
            book = bookRepo.findAll(page);
        }
        else if(Ordering==5){
            page = PageRequest.of(PageNum-1,9,Sort.Direction.ASC,"bookPubdate");
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
            page = PageRequest.of(PageNum-1,9,Sort.Direction.ASC,"bookPrice");
            book = bookRepo.findAllByCategory(catgRepo.findById(CatgIdx),page);
        }
        else if(Ordering==4){
            page = PageRequest.of(PageNum-1,9,Sort.Direction.DESC,"bookPrice");
            book = bookRepo.findAllByCategory(catgRepo.findById(CatgIdx),page);
        }
        else if(Ordering==5) {
            page = PageRequest.of(PageNum-1,9,Sort.Direction.ASC,"bookPubdate");
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
    }
