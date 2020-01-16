package kosta.namtang.talkbook.core.bill;

import kosta.namtang.talkbook.core.bill.request.payco.OrderStatusData;
import kosta.namtang.talkbook.core.bill.response.IamportResponse;
import kosta.namtang.talkbook.core.bill.response.payco.OrderStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaycoImpl {

    @POST("/payco/orders/status/{imp_uid}")
    Call<IamportResponse<OrderStatus>> updateStatus(
            @Header("Authorization") String token,
            @Path("imp_uid") String imp_uid,
            @Body OrderStatusData statusData
    );

}
