<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Aroma Shop - MyPruchase</title>
    <script>

    $(window).on('load', function () {
        $(function () {

            $.ajax({
                type: "get",
                url: "/api/v1/purchase/myPurchase",
                dataType:"json",
                success: function (result) {

                    if(result.statusCode === "Success") {
                        let str = "";
                        let list = JSON.parse( result.message);
                        for(let i = 0 ; i < list.length ; i++) {

                            //console.log(list[i].orderDate);

                            str += '<tr>'
                            str += '<td>' + list[i].orderDate + '</td>'
                            str += '<td><a href=/user/myPurchaseDetail?orderIdx=' + list[i].orderIdx + '> ' +  list[i].billKey + '</a></td>'
                            str += '<td>' + list[i].receiveName + '</td>'
                            str += '<td>'
                            str +=      list[i].productName
                            str += '</td>'
                            str += '<td>'+ list[i].totalPrice +'</td>'

                            str += '<td>' + '<button type="button" id="checkStatus" value=' + list[i].orderIdx + ' onclick="showStatusPopup(this);">상태확인</button>'  + '</td>'
                            str += '<td>' + '<button type="button" id="refund" value=' + list[i].orderIdx + ' onclick="showRefundPopup(this);">신청</button>'
                            str += '<div><input type="hidden" id="orderIdx" value='+ list[i].orderIdx +' /><div>'
                            str += '</td>'
                            str += '</tr>'

                        }
                        $("#cartList").html(str);
                    }

                },
                error: function (request, status, error) {
                    console.log('code:' + request.status + "\n" + 'message : ' + request.responseText + "\n" + 'error:' + error);
                }
            });
        });
    });

    function showStatusPopup(idx) {
        window.open("/user/purchase/popupStatus?orderIdx="+idx.value , "a", "width=400, height=300, left=100, top=50");
    }

    function showRefundPopup(idx) {
        window.open("/user/purchase/popupRefund?orderIdx="+idx.value , "a", "width=700, height=300, left=100, top=50");
    }

    </script>

</head>
<body>

<!-- ================ start banner area ================= -->
<%--<section class="blog-banner-area" id="category">--%>
<%--    <div class="container h-100">--%>
<%--        <div class="blog-banner">--%>
<%--            <div class="text-center">--%>
<%--                <h1>Shopping Cart</h1>--%>
<%--                <nav aria-label="breadcrumb" class="banner-breadcrumb">--%>
<%--                    <ol class="breadcrumb">--%>
<%--                        <li class="breadcrumb-item"><a href="#">Home</a></li>--%>
<%--                        <li class="breadcrumb-item active" aria-current="page">Shopping Cart</li>--%>
<%--                    </ol>--%>
<%--                </nav>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--	</section>--%>
	<!-- ================ end banner area ================= -->

  <!--================Cart Area =================-->
  <section class="cart_area">
      <div class="container">
          <div class="cart_inner">
              <div class="table-responsive">
                  <table class="table">
                      <thead>
                          <tr>
                              <th scope="col">주문일</th>
                              <th scope="col">주문번호</th>
                              <th scope="col">수령인</th>
                              <th scope="col">주문상품</th>
                              <th scope="col">구매금액</th>
                              <th scope="col">주문상태</th>
                              <th scope="col">반품 및 교환신청</th>
                          </tr>
                      </thead>

                    <tbody id="cartList">

                    </tbody>

                  </table>
              </div>

          </div>
      </div>
  </section>
  <!--================End Cart Area =================-->

</body>
</html>