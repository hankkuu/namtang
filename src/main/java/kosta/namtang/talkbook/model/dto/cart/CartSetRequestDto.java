package kosta.namtang.talkbook.model.dto.cart;

import kosta.namtang.talkbook.model.domain.CartId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartSetRequestDto {
    //장바구니 추가
    private CartId cartId;

    private String bookTitle;
    private String bookImg;
    private int bookPrice;

    public CartSetRequestDto(CartId cartId, String bookTitle, String bookImg, int bookPrice) {
        this.cartId = cartId;
        this.bookTitle = bookTitle;
        this.bookImg = bookImg;
        this.bookPrice = bookPrice;
    }

}
