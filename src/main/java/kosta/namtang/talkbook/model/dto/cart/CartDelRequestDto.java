package kosta.namtang.talkbook.model.dto.cart;

import kosta.namtang.talkbook.model.domain.CartId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDelRequestDto {
    //장바구니 삭제
    private CartId cartId;


}
