package kosta.namtang.talkbook.model.dto.cart;

import kosta.namtang.talkbook.model.domain.CartId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartSetRequest {
    //장바구니 추가
    private long bookIdx;
    private int qty;

}
