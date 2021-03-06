package kosta.namtang.mail.common;


public enum StatusCode {
    None(0), Success(1), Fail(2)

    // Account 관련
    , Not_Found_Id(10001), Not_Matched_Password(10002), Fail_Add_Account(10003), Fail_Find_ID(10004), Fail_Find_Password(10005), Not_Access_User(10006)

    // Goods 관련
    , Not_Found_GoodsId(20001)

    // Purchase 관련
    , Not_Found_PurchaseId(30001), Fail_Add_PurchseBasket(30002), Fail_Add_PurchasePayment(30003), Fail_Add_PurchaseGoods(30004), Fail_Issue_BillKey(30005), Fail_Update_RefundState(3006), Fail_Add_RefundGoods(30007), Fail_Cancel_BillKey(30008), Fail_Not_Exist_Goods(30009), Fail_Update_BaksetState(30010), Fail_Add_CancelGoods(30011);

    private int value;

    private StatusCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StatusCode fromInteger(int x) {
        switch (x) {
            case 0:
                return StatusCode.None;
            case 1:
                return StatusCode.Success;
            case 2:
                return StatusCode.Fail;
        }
        return StatusCode.None;
    }
};