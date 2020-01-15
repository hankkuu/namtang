<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Aroma Shop - Cart</title>
    <script>
        var Total_qty;
        var Total_price;
        <%! int test =1;%>


        <%--for (var i=<%test%>)--%>

        function amount_change(){
            var qtySelect = document.getElementById("amount<%=test%>");

            var qty=qtySelect.options[qtySelect.selectedIndex].value;
            console.log(qty);

            document.getElementById("qty").innerHTML=qty;

        }

        $(document).on('click',".nice-select .list li",function(){
            // alert($(".nice-select ul .option.selected").attr('data-value'));
            var result = qty($(this).attr('data-value'),$(this).parent().parent().next().val());
            $(this).parent().parent().parent().parent().next().text(result);
        });

        function qty(qty,bookIdx){
            let sum;
            let tt = bookIdx;

            console.log(tt);
            let cart = {
                qty : qty,
                bookIdx : tt
            }
            //$("#id option:eq(2)").attr("selected", "selected");


            console.log(cart);
                $.ajax({
                    async: false,
                    type: "get",
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
                        //$("#Total").text(Total_qty*Total_price);
                        sum = (Total_qty*Total_price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");//천단위 콤마 찍기
                    },
                    error: function (request, status, error) {
                        console.log('code:' + request.status + "\n" + 'message : ' + request.responseText + "\n" + 'error:' + error);
                    }

                });
            return "￦"+sum;
        }

        $(document).ready(function(){

            $("from[name=form1] input[name=bookIdx]").attr(id)
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
                              <th scope="col">delete</th>
                              <th scope="col">Product</th>
                              <th scope="col">ProductName</th>
                              <th scope="col">Price</th>
                              <th scope="col">Quantity</th>
                              <th scope="col">Total</th>
                          </tr>
                      </thead>

<%--                      <tbody>--%>
<%--                          <tr>--%>
<%--                              <td>--%>
<%--                                  <div class="media">--%>
<%--                                      <div class="d-flex">--%>
<%--                                          <img src="/img/cart/cart1.png" alt="">--%>
<%--                                      </div>--%>
<%--                                      <div class="media-body">--%>
<%--                                          <p>Minimalistic shop for multipurpose use</p>--%>
<%--                                      </div>--%>
<%--                                  </div>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <h5>$360.00</h5>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <div class="product_count">--%>
<%--                                      <input type="text" name="qty" id="sst" maxlength="12" value="1" title="Quantity:"--%>
<%--                                          class="input-text qty">--%>
<%--                                      <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"--%>
<%--                                          class="increase items-count" type="button"><i class="lnr lnr-chevron-up"></i></button>--%>
<%--                                      <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"--%>
<%--                                          class="reduced items-count" type="button"><i class="lnr lnr-chevron-down"></i></button>--%>
<%--                                  </div>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <h5>$720.00</h5>--%>
<%--                              </td>--%>
<%--                          </tr>--%>
<%--                          <tr>--%>
<%--                              <td>--%>
<%--                                  <div class="media">--%>
<%--                                      <div class="d-flex">--%>
<%--                                          <img src="/img/cart/cart2.png" alt="">--%>
<%--                                      </div>--%>
<%--                                      <div class="media-body">--%>
<%--                                          <p>Minimalistic shop for multipurpose use</p>--%>
<%--                                      </div>--%>
<%--                                  </div>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <h5>$360.00</h5>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <div class="product_count">--%>
<%--                                      <input type="text" name="qty" id="sst" maxlength="12" value="1" title="Quantity:"--%>
<%--                                          class="input-text qty">--%>
<%--                                      <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"--%>
<%--                                          class="increase items-count" type="button"><i class="lnr lnr-chevron-up"></i></button>--%>
<%--                                      <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"--%>
<%--                                          class="reduced items-count" type="button"><i class="lnr lnr-chevron-down"></i></button>--%>
<%--                                  </div>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <h5>$720.00</h5>--%>
<%--                              </td>--%>
<%--                          </tr>--%>
<%--                          <tr>--%>
<%--                              <td>--%>
<%--                                  <div class="media">--%>
<%--                                      <div class="d-flex">--%>
<%--                                          <img src="/img/cart/cart3.png" alt="">--%>
<%--                                      </div>--%>
<%--                                      <div class="media-body">--%>
<%--                                          <p>Minimalistic shop for multipurpose use</p>--%>
<%--                                      </div>--%>
<%--                                  </div>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <h5>$360.00</h5>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <div class="product_count">--%>
<%--                                      <input type="text" name="qty" id="sst" maxlength="12" value="1" title="Quantity:"--%>
<%--                                          class="input-text qty">--%>
<%--                                      <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"--%>
<%--                                          class="increase items-count" type="button"><i class="lnr lnr-chevron-up"></i></button>--%>
<%--                                      <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"--%>
<%--                                          class="reduced items-count" type="button"><i class="lnr lnr-chevron-down"></i></button>--%>
<%--                                  </div>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <h5>$720.00</h5>--%>
<%--                              </td>--%>
<%--                          </tr>--%>
<%--&lt;%&ndash;                          <tr class="bottom_button">&ndash;%&gt;--%>
<%--&lt;%&ndash;                              <td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                  <a class="button" href="#">Update Cart</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              <td>&ndash;%&gt;--%>

<%--&lt;%&ndash;                              </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              <td>&ndash;%&gt;--%>

<%--&lt;%&ndash;                              </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              <td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                  <div class="cupon_text d-flex align-items-center">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      <input type="text" placeholder="Coupon Code">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      <a class="primary-btn" href="#">Apply</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      <a class="button" href="#">Have a Coupon?</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                  </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                          </tr>&ndash;%&gt;--%>
<%--                          <tr>--%>
<%--                              <td>--%>

<%--                              </td>--%>
<%--                              <td>--%>

<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <h5>Subtotal</h5>--%>
<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <h5>$2160.00</h5>--%>
<%--                              </td>--%>
<%--                          </tr>--%>
<%--&lt;%&ndash;                          <tr class="shipping_area">&ndash;%&gt;--%>
<%--&lt;%&ndash;                              <td class="d-none d-md-block">&ndash;%&gt;--%>

<%--&lt;%&ndash;                              </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              <td>&ndash;%&gt;--%>

<%--&lt;%&ndash;                              </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              <td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                  <h5>Shipping</h5>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              <td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                  <div class="shipping_box">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      <ul class="list">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <li><a href="#">Flat Rate: $5.00</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <li><a href="#">Free Shipping</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <li><a href="#">Flat Rate: $10.00</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <li class="active"><a href="#">Local Delivery: $2.00</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      </ul>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      <h6>Calculate Shipping <i class="fa fa-caret-down" aria-hidden="true"></i></h6>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      <select class="shipping_select">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <option value="1">Bangladesh</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <option value="2">India</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <option value="4">Pakistan</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      </select>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      <select class="shipping_select">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <option value="1">Select a State</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <option value="2">Select a State</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                          <option value="4">Select a State</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      </select>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      <input type="text" placeholder="Postcode/Zipcode">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                      <a class="gray_btn" href="#">Update Details</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                  </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                              </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                          </tr>&ndash;%&gt;--%>
<%--                          <tr class="out_button_area">--%>
<%--                              <td class="d-none-l">--%>

<%--                              </td>--%>
<%--                              <td class="">--%>

<%--                              </td>--%>
<%--                              <td>--%>

<%--                              </td>--%>
<%--                              <td>--%>
<%--                                  <div class="checkout_btn_inner d-flex align-items-center">--%>
<%--                                      <a class="gray_btn" href="#">계속 쇼핑하기</a>--%>
<%--                                      <a class="primary-btn ml-2" href="#">결제하기</a>--%>
<%--                                  </div>--%>
<%--                              </td>--%>
<%--                          </tr>--%>
<%--                      </tbody>--%>
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
                                          <a href="${path}/cart/delete?userIdx=${cartVO.cartId.userIdx}&bookIdx=${cartVO.cartId.bookIdx}">[삭제]</a>
                                      </td>
                                      <td>
                                          <img class="card-img" src="${cartVO.bookImg}">
                                      </td>
                                      <td>
                                          ${cartVO.bookTitle}
                                      </td>
                                      <td id="price">
                                          <fmt:formatNumber value="${cartVO.bookPrice}" type="currency"/>
                                      </td>
                                      <td>
                                          <form name="form1" method="post" action="/cart/update">
                                              <input type="hidden" name="userIdx" value="${cartVO.cartId.userIdx}">
<%--                                              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >--%>
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
<%--                                                  <select id="searchOption" name="searchOption">--%>
<%--                                                      <option value="">구분</option>--%>
<%--                                                      <option value="name" <c:if test="${param.searchOption}">selected="selected"</c:if>>작성자</option>--%>
<%--                                                      <option value="subject" <c:if test="${param.searchOption}">selected="selected"</c:if>>제목</option>--%>
<%--                                                  </select>--%>


<%--                                                 <c:set target="test" value=--%>
                                                  <% test += 1;%>
                                              </select>

                                          </form>
<%--                                          <input class="aa-cart-quantity" type="number" value="${ cartVO.quantity}"  min="1" max="100" readonly="readonly">--%>
                                      </td>
                                      <td id=Total<%=test%>>

                                          <fmt:formatNumber  value="${cartVO.bookPrice*cartVO.qty}"  type="currency" />
                                      </td>
                                  </tr>


                              </c:forEach>
                          </c:otherwise>
                      </c:choose>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
  </section>
  <!--================End Cart Area =================-->

<%--  <script type="text/javascript">--%>
<%--	  //장바구니 에 담기--%>

<%--	  function go_cart(){--%>
<%--		  var loginUser='${sessionScope.loginUser}';--%>
<%--		  if(loginUser==null || loginUser==''){--%>
<%--			  alert("로그인을 먼저 하세요.");--%>
<%--			  return;--%>
<%--		  }--%>
<%--		  if(document.formm.quantity.value==""){--%>
<%--			  alert("수량을 입력하여 주세요.");--%>
<%--			  document.formm.quantity.focus();--%>
<%--		  }else{--%>
<%--			  $("input[name=command]").val("cart_insert");--%>
<%--			  document.formm.submit();--%>

<%--		  }--%>

<%--	  }--%>

<%--	  $(function(){--%>

<%--		  $(".cart-ajax").click(function(event){--%>
<%--			  event.preventDefault();--%>
<%--			  pseq=$(this).attr("data-pseq");--%>

<%--			  $.ajax({--%>
<%--				  url:"MacaronicsServlet?command=cart_ajax",--%>
<%--				  type:"post",--%>
<%--				  dataType:"text",--%>
<%--				  data : {--%>
<%--					  pseq:pseq,--%>
<%--					  quantity:1--%>
<%--				  },--%>
<%--				  success:function(result){--%>

<%--					  if($.trim(result)=='success'){--%>
<%--						  if(confirm("장바구니에 담았습니다. 장바구니로 이동하시겠습니까?")){--%>

<%--							  location.href="/MacaronicsServlet?command=cart_list";--%>
<%--						  }--%>
<%--					  }else{--%>
<%--						  alert($.trim(result));--%>
<%--					  }--%>
<%--				  }--%>
<%--			  });--%>
<%--		  });--%>



<%--	  });--%>
<%--	  function go_cart_delete(cseq){--%>

<%--		  if(confirm("정말 삭제하시겠습니까?")){--%>
<%--			  location.href="MacaronicsServlet?command=cart_delete&cseq="+cseq;--%>
<%--		  }--%>

<%--	  }--%>

<%--	  function go_cart_all_delete(){--%>

<%--		  if(confirm("정말 삭제하시겠습니까?")){--%>
<%--			  document.form1.submit();--%>
<%--		  }--%>

<%--	  }--%>

<%--	  function go_cart_ajax_delete(cseq){--%>

<%--		  if(confirm("정말 삭제하시겠습니까?")){--%>

<%--			  $.ajax({--%>
<%--				  url:"MacaronicsServlet?command=cart_delete_ajax",--%>
<%--				  type:"post",--%>
<%--				  data:{cseq:cseq},--%>
<%--				  success:function(result){--%>
<%--					  if($.trim(result)=='success'){--%>
<%--						  alert("삭제 했습니다.")--%>
<%--						  location.reload();--%>
<%--					  }--%>
<%--				  }--%>

<%--			  });--%>

<%--		  }--%>

<%--	  }--%>

<%--  </script>--%>




</body>
</html>