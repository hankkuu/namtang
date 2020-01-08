package kosta.namtang.talkbook.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cart {
    @EmbeddedId
    private CartId cartId;

    private String bookTitle;
    private String bookImg;
    private int bookPrice;
    private LocalDateTime createDate;
}
