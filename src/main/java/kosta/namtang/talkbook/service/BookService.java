package kosta.namtang.talkbook.service;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.Category;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Book> selectAll(Pageable page, int Ordering){
        Page<Book> book = null;
        if(Ordering==1) book = bookRepo.findAllByOrderByBookTitle(page);
        else if(Ordering==2) book = bookRepo.findAllByOrderByBookTitleDesc(page);
        else if(Ordering==3) book = bookRepo.findAllByOrderByBookPubdate(page);
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
     public Page<Book> CatgCall(Long CatgIdx, int Ordering,Pageable page){
         Page<Book> book=null;
        if(Ordering==1) book=bookRepo.findAllByCategoryOrderByBookTitle(catgRepo.findById(CatgIdx),page);
        else if(Ordering==2) book=bookRepo.findAllByCategoryOrderByBookTitleDesc(catgRepo.findById(CatgIdx),page);
        else if(Ordering==3) book=bookRepo.findAllByCategoryOrderByBookPubdate(catgRepo.findById(CatgIdx),page);
        return book;
     }
    }
