package kosta.namtang.talkbook.model.dto.cart;

import kosta.namtang.talkbook.model.domain.CartId;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartSetRequest {
    //장바구니 추가
    private long bookIdx;
    private int qty;
    private int bookPrice;
    private String bookImg;
    private String bookTitle;

}
