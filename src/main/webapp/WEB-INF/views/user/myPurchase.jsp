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



                $.ajax({
                    async: false,
                    url: "/user/cart/update",
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
                              <th scope="col">주문일</th>
                              <th scope="col">주문번호</th>
                              <th scope="col">수령인</th>
                              <th scope="col">주문상품</th>
                              <th scope="col">구매금액</th>
                              <th scope="col">반품 및 교환신청</th>
                          </tr>
                      </thead>

                      <tbody>

                              <c:forEach items="${cartList}" var="cartVO">

                                  <tr>
                                      <td>
                                          <span>주문일</span>
                                      </td>
                                      <td>
                                          <span>주문번호</span>
                                      </td>
                                      <td class="card-title">수령인</td>
                                      <td id="price"><fmt:formatNumber value="${cartVO.bookPrice}" type="currency"/></td>
                                      <td>
                                          <form name="form1" method="post" action="/user/cart/update">
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