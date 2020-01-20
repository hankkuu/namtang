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

            let para = document.location.href.split("?");
            console.log(para);

            $.ajax({
                type: "get",
                url: "/api/v1/purchase/myPurchaseDetail",
                data: { id: para[1] },
                dataType:"json",
                success: function (result) {

                    if(result.statusCode === "Success") {
                        let str = "";
                        let list = JSON.parse( result.message);
                        for(let i = 0 ; i < list.length ; i++) {

                            console.log(list[i]);


                            str += '<tr>'
                            str += '<td>' + list[i].bookName + '</td>'
                            str += '<td>' + list[i].count + '</a></td>'
                            str += '<td>' + list[i].price + '</td>'
                            if(list[i].isReview === true) {
                                str += '<td>' + '<button type="button" id="writeReview" value="review" onclick="showStatusPopup();">리뷰보기</button>'  + '</td>'
                            } else {
                                str += '<td>' + '<button type="button" id="readReview" value="viewReview" onclick="showRefundPopup();">리뷰작성</button>'
                            }
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

    function showStatusPopup() {
            window.open("/user/purchase/popupStatus?"+$("#orderIdx").val() , "a", "width=400, height=300, left=100, top=50");
    }

    function showRefundPopup() {
        window.open("/user/purchase/popupRefund?id="+$("#orderIdx").val() , "a", "width=400, height=300, left=100, top=50");
    }

<%--        function qty(qty, bookIdx) {--%>
<%--            let sum;--%>
<%--            let tt = bookIdx;--%>

<%--            console.log(tt);--%>
<%--            let cart = {--%>
<%--                qty: qty,--%>
<%--                bookIdx: tt--%>
<%--            }--%>

<%--            console.log(cart);--%>
<%--                $.ajax({--%>
<%--                    async: false,--%>
<%--                    url: "/user/cart/update",--%>
<%--                    dataType:"json",--%>
<%--                    data: cart,--%>
<%--                    //contentType:"application/json",--%>
<%--                    success: function (result) {--%>


<%--                    },--%>
<%--                    error: function (request, status, error) {--%>
<%--                        console.log('code:' + request.status + "\n" + 'message : ' + request.responseText + "\n" + 'error:' + error);--%>
<%--                    }--%>

<%--                });--%>
<%--            return "￦"+sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");//천단위 콤마 찍기;--%>
<%--        }--%>




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
                              <th scope="col">상품명</th>
                              <th scope="col">수량</th>
                              <th scope="col">가격</th>
                              <th scope="col">리뷰 보기/작성</th>

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