package kosta.namtang.talkbook.service;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepo;

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

}
