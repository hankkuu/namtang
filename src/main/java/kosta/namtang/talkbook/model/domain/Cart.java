package kosta.namtang.talkbook.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Cart {
    @EmbeddedId
    private CartId cartId;

    private LocalDateTime createDate;
}
