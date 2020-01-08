package kosta.namtang.talkbook.model.dto.cart;

import kosta.namtang.talkbook.model.domain.CartId;
import lombok.Getter;

@Getter
public class CartResponseDto {
    //select로 뿌려줌
    private CartId cartId;

    public CartResponseDto(CartId cartId) {
        this.cartId = cartId;
    }
}
