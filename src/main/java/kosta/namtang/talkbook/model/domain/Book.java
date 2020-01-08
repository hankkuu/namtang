package kosta.namtang.talkbook.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Book_seq_no")
    @SequenceGenerator(sequenceName = "Book_seq_no",name="Book_seq_no",allocationSize = 1)
    private Long bookIdx;
    private String bookTitle;
    private String bookImg;
    private String bookAuthor;
    private BigDecimal bookPrice;
    private String bookPublisher;
    private String bookDesc;
    private String bookPubdate;
    private int bookCount;
    private String bookIsbn;



    @ManyToOne
    @JoinColumn(name="catgIdx")
    private Category category;

}
