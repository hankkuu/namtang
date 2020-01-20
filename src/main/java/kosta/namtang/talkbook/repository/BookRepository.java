package kosta.namtang.talkbook.repository;
import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    public int countBookByCategory(Optional<Category> category);

    public Page<Book> findAllByCategory(Optional<Category> category, Pageable pageable);

    public Page<Book> findAll(Pageable pageable);

    public Page<Book> findAllByBookTitle(String word,Pageable pageable);

    @Query("select b from Book As b where b.category = ?1 And b.bookTitle like ?2")
    public Page<Book> searchByTitle(Optional<Category> category, String Word,Pageable pageable);

    @Query("select b from Book As b where b.category = ?1 And b.bookAuthor like ?2")
    public Page<Book> searchByAuthor(Optional<Category> category, String Word,Pageable pageable);

    @Query("select b from Book As b where b.category = ?1 And b.bookPublisher like ?2")
    public Page<Book> searchByPublisher(Optional<Category> category, String Word,Pageable pageable);

    @Query("select b from Book As b where b.bookTitle like ?1")
    public Page<Book> searchByTitleAll(String Word,Pageable pageable);

    @Query("select b from Book As b where b.bookAuthor like ?1")
    public Page<Book> searchByAuthorAll(String Word,Pageable pageable);

    @Query("select b from Book As b where b.bookPublisher like ?1")
    public Page<Book> searchByPublisherAll(String Word,Pageable pageable);

    //누적Top10,월간Top10
    @Query("select b from Book As b where b.bookIdx in(?1)")
    public List<Book> findBestCount(List<Long> idList);

    //테마 카테고리
    @Query("select b from Book As b where b.bookType = ?1")
    public Page<Book> findThemeCatg(int type,Pageable pageable);
}

