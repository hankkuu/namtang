package kosta.namtang.talkbook.model.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class PurchaseOrderResponse {

    private long orderIdx;
    private Date orderDate;
    private String billKey;
    private String receiveName;
    private String productName;
    private BigDecimal totalPrice;

}
