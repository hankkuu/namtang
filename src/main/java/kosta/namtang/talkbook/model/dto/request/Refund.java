package kosta.namtang.talkbook.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Refund {
    private long orderIdx;
    private long bookIdx;
    private String bookName;
    private int bookCount;
    private String reason;
    private int refundCode;
}