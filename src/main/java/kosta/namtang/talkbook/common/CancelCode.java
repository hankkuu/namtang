package kosta.namtang.talkbook.common;

public enum CancelCode {
    // 환불사유
    None(0), Buyer(1), Seller(2);


    private int value;

    private CancelCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
