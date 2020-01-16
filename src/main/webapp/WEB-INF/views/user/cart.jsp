<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Aroma Shop - Cart</title>
    <script>

        var Total_Sum;
        var Total_qty;
        var Total_price;
        <%! int test =1;%>

        <%--function amount_change(){--%>
        <%--    var qtySelect = document.getElementById("amount<%=test%>");--%>

        <%--    var qty=qtySelect.options[qtySelect.selectedIndex].value;--%>
        <%--    console.log(qty);--%>

        <%--    document.getElementById("qty").innerHTML=qty;--%>

        <%--}--%>

        $(document).on('click', ".nice-select .list li", function () {
            // alert($(".nice-select ul .option.selected").attr('data-value'));
            var result = qty($(this).attr('data-value'), $(this).parent().parent().next().val());
            $(this).parent().parent().parent().parent().next().text(result);
            $(this).parent().eq(0).parent().eq(0).parent().eq(0).parent().eq(0).parent().eq(0).children().eq(0).children().eq(0).text(Total_Sum);
            Sum();
        });
        function Sum(){

            var checkbox = $("input:checkbox[name=check]:checked");//체크된 모든박스
            var Sum=0;
            checkbox.each(function(i) {
                var tr = checkbox.parent().parent().eq(i);
                var td = tr.children();
                var total = td.eq(0).children().eq(0).text();
                console.log(total);
                total = total * 1;
                Sum=Sum+total;

            });
            Sum="￦"+Sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");//천단위 콤마 찍기;
            $("#priceSum").text(Sum);
        }

        function qty(qty, bookIdx) {
            let sum;
            let tt = bookIdx;

            console.log(tt);
            let cart = {
                qty: qty,
                bookIdx: tt
            }

            console.log(cart);
                $.ajax({
                    async: false,
                    url: "/cart/update",
                    dataType:"json",
                    data: cart,
                    //contentType:"application/json",
                    success: function (result) {
                        // let a=$("#"+id+" option").val(result.qty);
                        console.log(result);
                        console.log(result.qty);
                        Total_qty = result.qty;
                        Total_price = result.bookPrice;
                        CartId = result.cartId;
                        $("#Total").text(Total_qty*Total_price);
                        sum = (Total_qty*Total_price);
                        Total_Sum = sum;

                    },
                    error: function (request, status, error) {
                        console.log('code:' + request.status + "\n" + 'message : ' + request.responseText + "\n" + 'error:' + error);
                    }

                });
            return "￦"+sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");//천단위 콤마 찍기;
        }


        // $(function () {
        //     $("#purchase_btn").on('click' , function(){
        //         let ex=sessionStorage.getItem("tp")
        //         alert(ex);
        //
        //
        //
        //         $.ajax({
        //             type : "get",
        //             url : "/cart/insert",
        //             dataType : "json",
        //             data : add_to,
        //             error : function(err){
        //                 // alert("전송실패");
        //                 console.log(err);
        //             },
        //             success : function(result){
        //                 // $("#content").html(Parse_data); //div에 받아온 값을 넣는다.
        //                 if(result.statusCode === "Success") {
        //                     alert(result.message);
        //                 }
        //
        //             }
        //
        //         });
        //     });
        //
        // })

        function check() {
            <%--let checkbox = document.getElementById("check<%=test%>");--%>
            // let checkbox2 = document.getElementsByName("check").length;
            // console.log(checkbox);
            // console.log(checkbox2);
            //check이름을 가진 check중에서 체크된 것만 값 가져오기
            // var size = document.getElementsByName("check").length;



            var checkbox = $("input:checkbox[name=check]:checked");//체크된 모든박스
            var list = [];
            checkbox.each(function(i) {

                var tr = checkbox.parent().parent().eq(i);
                var td = tr.children();

                let book = {
                     img : td.eq(1).children().attr('src'), // td의 0번째는 이름
                     title : td.eq(2).text(), // td의 1번째는 아이디
                     price : td.eq(3).text(),
                     id : td.eq(4).children().children().next().next().next().val(),
                     qty : td.eq(4).children().children().next().next().children().eq(0).text(),
                    // var qty=td.eq(4).children().children().next().children('selected').text();
                    total : td.eq(5).text()
            }

                list.push(book);

                console.log(list);
            });

            sessionStorage.setItem("cartlist",JSON.stringify(list));

        }

    </script>

</head>
<body>

<!-- ================ start banner area ================= -->
<section class="blog-banner-area" id="category">
    <div class="container h-100">
        <div class="blog-banner">
            <div class="text-center">
                <h1>Shopping Cart</h1>
                <nav aria-label="breadcrumb" class="banner-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Shopping Cart</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
	</section>
	<!-- ================ end banner area ================= -->

  <!--================Cart Area =================-->
  <section class="cart_area">
      <div class="container">
          <div class="cart_inner">
              <div class="table-responsive">
                  <table class="table">
                      <thead>
                          <tr>
                              <th scope="col">delete</th>
                              <th scope="col">Product</th>
                              <th scope="col">ProductName</th>
                              <th scope="col">Price</th>
                              <th scope="col">Quantity</th>
                              <th scope="col">Total</th>
                          </tr>
                      </thead>

                      <tbody>
                      <c:choose>
                          <c:when test="${cartList.size() ==0 }">
                              <tr>
                                  <td colspan="10">장바구니가 비었습니다.</td>
                              </tr>
                          </c:when>
                          <c:otherwise>
<%--                              <c:set var="test" value="1"/>--%>
<%--                              <%! int test =1;%>--%>

                              <c:forEach items="${cartList}" var="cartVO">

                                  <tr>
                                      <td>
                                          <a id="hiddenSum"hidden>${cartVO.bookPrice*cartVO.qty}</a>
                                          <a href="${path}/cart/delete?userIdx=${cartVO.cartId.userIdx}&bookIdx=${cartVO.cartId.bookIdx}">[삭제]</a>
                                      </td>
                                      <td>
                                          <img class="card-img" src="${cartVO.bookImg}">
                                      </td>
                                      <td class="card-title">${cartVO.bookTitle}</td>
                                      <td id="price"><fmt:formatNumber value="${cartVO.bookPrice}" type="currency"/></td>
                                      <td>
                                          <form name="form1" method="post" action="/cart/update">
                                              <input type="hidden" name="userIdx" value="${cartVO.cartId.userIdx}">
<%--                                              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >--%>
<%--                                              <input type="text" list='<%=test%>'>--%>
                                              <select name="amount" id='<%=test%>'>
                                                  <c:forEach begin="1" end="10" var="i">
                                                      <c:choose>
                                                          <c:when test="${cartVO.qty eq i}">
                                                              <option value="${i}" selected>${i}</option>
                                                          </c:when>
                                                          <c:otherwise>
                                                              <option value="${i}">${i}</option>
                                                          </c:otherwise>
                                                      </c:choose>
                                                  </c:forEach>
                                                  <input type="hidden" name="bookIdx" id=bookIdx<%=test%> value="${cartVO.cartId.bookIdx}">
                                              </select>
                                          </form>
<%--                                          <input class="aa-cart-quantity" type="number" value="${ cartVO.quantity}"  min="1" max="100" readonly="readonly">--%>
                                      </td>
                                      <td id=Total<%=test%>><fmt:formatNumber  value="${cartVO.bookPrice*cartVO.qty}"  type="currency" /></td>
                                      <td>
                                          <input type="checkbox" id="check<%=test%>" name="check" value="<%=test%>" onclick="Sum()">
                                      </td>
                                      <% test += 1;%>
                                  </tr>


                              </c:forEach>
                          </c:otherwise>
                      </c:choose>
                      <tr>
                          <td>

                          </td>
                          <td>

                          </td>
                          <td>
                              <h5>Subtotal</h5>
                          </td>
                          <td>
                              <h5 id="priceSum">￦0</h5>
                          </td>
                      </tr>

                      </tbody>
                  </table>
              </div>
              <tr class="out_button_area">
                  <td class="d-none-l">

                  </td>
                  <td class="">

                  </td>
                  <td>

                  </td>
                  <td>

                      <div class="checkout_btn_inner d-flex align-items-center">
                          <a class="button primary-btn" href="#">계속 쇼핑하기</a>
                          <a class="button primary-btn ml-2" href="/user/purchase" onclick="check()" id="purchase_btn">결제하기</a>

                      </div>
                  </td>
              </tr>
          </div>
      </div>
  </section>
  <!--================End Cart Area =================-->

</body>
</html>