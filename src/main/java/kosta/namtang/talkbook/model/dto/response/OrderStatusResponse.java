package kosta.namtang.talkbook.model.dto.response;

import lombok.Data;

@Data
public class OrderStatusResponse {
    private String address;
    private String phone;
    private String purchaseStatus;
}
