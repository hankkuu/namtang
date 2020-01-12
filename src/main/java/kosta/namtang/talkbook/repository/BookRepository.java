package kosta.namtang.talkbook.repository;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    public int countBookByCategory(Optional<Category> category);

    public Page<Book> findAllByCategoryOrderByBookTitle(Optional<Category> category, Pageable pageable);
    public Page<Book> findAllByCategoryOrderByBookTitleDesc(Optional<Category> category, Pageable pageable);
    public Page<Book> findAllByCategoryOrderByBookPubdate(Optional<Category> category, Pageable pageable);

    public Page<Book> findAllByOrderByBookTitle(Pageable pageable);
    public Page<Book> findAllByOrderByBookTitleDesc(Pageable pageable);
    public Page<Book> findAllByOrderByBookPubdate(Pageable pageable);
}

