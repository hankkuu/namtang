package kosta.namtang.talkbook.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Cart {
    @EmbeddedId
    private CartId cartId;
}
