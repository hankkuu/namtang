package kosta.namtang.talkbook.model.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseBookResponse {
    private String bookName;
    private int count;
    private BigDecimal price;
    private boolean isReview;
}
