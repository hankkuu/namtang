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

            let params = window.location.search;
            //let decode = decodeURI(params);
            let str = params.substring(params.indexOf("=") + 1);
            //let replaceStr = str.slice(0, -1);
            //replaceStr = replaceStr.replace(/\\/ig, "").replace("\"\"{", "{").replace("}\"\"", "}");
            //let json = JSON.parse(replaceStr);

            console.log("json : " + str);

            //let para = document.location.href.split("?");
            //console.log(para);

            $.ajax({
                type: "get",
                url: "/api/v1/purchase/myPurchaseDetail",
                data: { id: str },
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
                            str += '<td>' + list[i].state + '</td>'
                            if(list[i].isReview === true) {
                                str += '<td>' + '<button type="button" id="writeReview" value="'+list[i].bookIdx+'" onclick="showStatusPopup(this);">리뷰보기</button>'  + '</td>'
                            } else {
                                str += '<td>' + '<button type="button" id="readReview" value="'+list[i].bookIdx+'" onclick="showRefundPopup(this);">리뷰작성</button>'
                            }
                            str += '<input type="hidden" id="bookIdx" value='+list[i].bookIdx+' />'
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
    //location.href="/BookDetail?id="+id;
    function showStatusPopup(idx) {
        window.open("/BookDetail?id="+idx.value , '_blank');
    }

    function showRefundPopup(idx) {
        window.open("/BookDetail?id="+idx.value , '_blank');
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
                              <th scope="col">상품명</th>
                              <th scope="col">수량</th>
                              <th scope="col">가격</th>
                              <th scope="col">상태</th>
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