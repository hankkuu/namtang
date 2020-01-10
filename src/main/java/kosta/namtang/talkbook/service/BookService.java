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

    public Page<Book> selectAll(Pageable page){
        Page<Book> it = bookRepo.findAll(page);
        return it;
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
     public Page<Book> CatgCall(Long CatgIdx,Pageable page){
        Page<Book> book = bookRepo.findAllByCategory(catgRepo.findById(CatgIdx),page);
        return book;
     }
    }
