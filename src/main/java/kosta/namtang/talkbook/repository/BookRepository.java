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

    public Page<Book> findAllByCategory(Optional<Category> category, Pageable pageable);
//    public Page<Book> findAllByCategoryOrderByBookTitleDesc(Optional<Category> category, Pageable pageable);
//    public Page<Book> findAllByCategoryOrderByBookPriceAsc(Optional<Category> category, Pageable pageable);
//    public Page<Book> findAllByCategoryOrderByBookPriceDesc(Optional<Category> category, Pageable pageable);
//    public Page<Book> findAllByCategoryOrderByBookPubdate(Optional<Category> category, Pageable pageable);


    public Page<Book> findAll(Pageable pageable);
//    public Page<Book> findAllByOrderByBookTitleDesc(Pageable pageable);
//    public Page<Book> findAllByOrderByBookPrice(Pageable pageable);
//    public Page<Book> findAllByOrderByBookPriceDesc(Pageable pageable);
//    public Page<Book> findAllByOrderByBookPubdate(Pageable pageable);

    public Page<Book> findAllByBookTitle(String Word, Pageable pageable);

    public Page<Book> findAllByBookAuthor(String Word, Pageable pageable);

    public Page<Book> findAllByBookPublisher(String Word, Pageable pageable);

    public Page<Book> findByCategoryAndBookTitleLike(Optional<Category> category, String Word, Pageable pageable);

    public Page<Book> findByCategoryAndBookAuthorLike(Optional<Category> category, String Word, Pageable pageable);

    public Page<Book> findByCategoryAndBookPublisherLike(Optional<Category> category, String Word, Pageable pageable);

    @Query("select b from Book As b where b.category = ?1 And b.bookTitle like ?2")
    public Page<Book> searchByTitle(Optional<Category> category, String Word, Pageable pageable);

    @Query("select b from Book As b where b.category = ?1 And b.bookAuthor like ?2")
    public Page<Book> searchByAuthor(Optional<Category> category, String Word, Pageable pageable);

    @Query("select b from Book As b where b.category = ?1 And b.bookPublisher like ?2")
    public Page<Book> searchByPublisher(Optional<Category> category, String Word, Pageable pageable);

    @Query("select b from Book As b where b.bookTitle like ?1")
    public Page<Book> searchByTitleAll(String Word, Pageable pageable);

    @Query("select b from Book As b where b.bookAuthor like ?1")
    public Page<Book> searchByAuthorAll(String Word, Pageable pageable);

    @Query("select b from Book As b where b.bookPublisher like ?1")
    public Page<Book> searchByPublisherAll(String Word, Pageable pageable);


}

