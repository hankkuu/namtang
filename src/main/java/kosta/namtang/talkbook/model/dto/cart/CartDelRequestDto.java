package kosta.namtang.talkbook.model.dto.cart;

import kosta.namtang.talkbook.model.domain.CartId;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartDelRequestDto {
    //장바구니 삭제
    private CartId cartId;

    private String bookTitle;
    private String bookImg;
    private int bookPrice;

    public CartDelRequestDto(CartId cartId, String bookTitle, String bookImg, int bookPrice) {
        this.cartId = cartId;
        this.bookTitle = bookTitle;
        this.bookImg = bookImg;
        this.bookPrice = bookPrice;
    }

}
