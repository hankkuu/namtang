package kosta.namtang.talkbook.model.dto.cart;

import kosta.namtang.talkbook.model.domain.CartId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CartResponseDto {
    //select로 뿌려줌
    private CartId cartId;
    private String bookTitle;
    private String bookImg;
    private int bookPrice;
}
