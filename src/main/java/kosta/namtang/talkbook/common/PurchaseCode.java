package kosta.namtang.talkbook.common;


public enum PurchaseCode {
	None(0)
  , Success(1)
  
  // 결제 관련
  , Payment_Success(10)
  , Delivery_Ing(11)
  , Delivery_Success(12)
  , Purchase_Success(13)
  
  // 환불 관련
  , Cancel_Ing(20)
  , Cancel_Success(21);
  
	
  private int value;
  private PurchaseCode(int value) {
    this.value = value;
  }
  public int getValue() {
    return value;
  }
};
