<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Aroma Shop - Checkout</title>

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <script type="text/javascript" >
        const IMP = window.IMP; // 생략해도 괜찮습니다.
        IMP.init("imp13182886"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.'

        $(window).load(function() {
            console.log("start");

            $("#purchase").click( async () => {

                await purchaseProcess().then( (ret) => {
                    console.log(ret);

                    if(ret.statusCode === "Success") {
                        const key = ret.message;
                        if (key !== undefined) {
                            let total = $("#totalPrice").text();
                            // 구매하는 user 정보 필요
                            // billkey 정보 필요
                            // 총금액 and 구매 이름 필요
                            let user = {
                                email: "gildong@gmail.com",
                                name: "홍길동",
                                tel: "010-4242-4242",
                                addr: "서울특별시 강남구 신사동",
                                postcode: "01181"
                            };
                            let shop = {
                                pg: "kakaopay",
                                pay_method: "card",
                            };
                            let param = {
                                pg: shop.pg,
                                pay_method: shop.pay_method,
                                merchant_uid: key,
                                name: "임시로 사용하는 이름이다",
                                amount: total,
                                buyer_email: user.email,
                                buyer_name: user.name,
                                buyer_tel: user.tel,
                                buyer_addr: user.addr,
                                buyer_postcode: user.postcode
                            };

                            const ret = setPuchase();
                            if (ret === 1) {
                                purchase(param);
                            }
                        }
                    }




                });
            });
        });


        const purchaseProcess = async () => {
            const result = await $.ajax({
                type: "post",
                url: "/api/v1/purchase/getBillKey",
                dataType: "json",
                //contentType: 'application/json; charset=utf-8',
                success: function (result) {
                    console.log(result);
                    if (result.statusCode === "Success") {
                        alert("구매시작");

                    } else {
                        alert("입력이 잘못되었습니다");
                    }
                },
                error: function (error) {
                    console.log(error);
                    alert("오류 발생");
                }
            });//ajax끝

            return result;
        }

        const setPuchase = async () => {

            const book = [
                            { bookIdx: 1, bookPrice: 1000, bookCount: 3, bookTitle: "test", bookImg: "" },
                            { bookIdx: 2, bookPrice: 1000, bookCount: 3, bookTitle: "test", bookImg: "" },
                            { bookIdx: 3, bookPrice: 1000, bookCount: 3, bookTitle: "test", bookImg: "" }
                         ]
            const purchaseOrder = {
                deliveryAddress: "dddddddddddd"
            }

            const purchasePayment = {

            }

            const user = {}
            // axios로 HTTP 요청
            axios({
                url: "/api/v1/purchase", // 가맹점 서버
                method: "post",
                headers: { "Content-Type": "application/json" },
                data: {
                    book: book,
                    purchaseOrder: purchaseOrder,
                    purchasePayment: purchasePayment
                }
            }).then((data) => {
                // 가맹점 서버 결제 API 성공시 로직
                // 이제 DB 입력을 해도 된다
                alert("헬로 success");
                return 1;
            })
        }

        function purchase(param) {
            // IMP.request_pay(param, callback) 호출
            IMP.request_pay(param,
                rsp => {
                // callback
                if (rsp.success) {
                    console.log(rsp);
                    // axios로 HTTP 요청
                    axios({
                        url: "/api/v1/completePurchase", // 가맹점 서버
                        method: "post",
                        headers: { "Content-Type": "application/json" },
                        data: {
                            imp_uid: rsp.imp_uid,
                            merchant_uid: rsp.merchant_uid,
                            paid_amount: rsp.paid_amount,
                        }
                    }).then((data) => {
                        // 가맹점 서버 결제 API 성공시 로직
                        // 이제 DB 입력을 해도 된다
                        console.log(data);
                        alert("success");



                    })
                } else {
                    alert("bbb");
                    //...,
                    // 결제 실패 시 로직,
                    //...
                }
            });
        }




    </script>
</head>
<body>

	<!-- ================ start banner area ================= -->	
	<section class="blog-banner-area" id="category">
		<div class="container h-100">
			<div class="blog-banner">
				<div class="text-center">
					<h1>Product Checkout</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">Checkout</li>
            </ol>
          </nav>
				</div>
			</div>
    </div>
	</section>
	<!-- ================ end banner area ================= -->
  
  
  <!--================Checkout Area =================-->
  <section class="checkout_area section-margin--small">
    <div class="container">

        <div class="billing_details">
            <div class="row">
                <div class="col-lg-8">
                    <h3>Billing Details</h3>
                    <form class="row contact_form" action="#" method="post" novalidate="novalidate">
                        <div class="col-md-6 form-group p_star">
                            <%--<input type="text" class="form-control" id="first" name="name">--%>
                            <span class="placeholder" data-placeholder="First name">주문인</span>
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="last" name="name">
                            <span class="placeholder" data-placeholder="Last name"></span>
                        </div>
                        <%--<div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="company" name="company" placeholder="Company name">
                        </div>--%>
                        <div class="col-md-6 form-group p_star">
                            <%--<input type="text" class="form-control" id="number" name="number">--%>
                            <span class="placeholder" data-placeholder="Phone number">받는 사람</span>
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="email" name="compemailany">
                            <span class="placeholder" data-placeholder="Email Address"></span>
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <select class="country_select">
                                <option value="1">Address1</option>
                                <option value="2">Address2</option>
                                <option value="4">Address3</option>
                            </select>
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <input type="text" class="form-control" id="city" name="city">
                            <span class="placeholder" data-placeholder="Town/City"></span>
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <span class="placeholder" data-placeholder="Phone number">연락처</span>
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="add1" name="add1">
                            <span class="placeholder" data-placeholder="Address line 01"></span>
                        </div>


                        <div class="col-md-12 form-group p_star">
                            <select class="country_select">
                                <option value="1">District</option>
                                <option value="2">District</option>
                                <option value="4">District</option>
                            </select>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="zip" name="zip" placeholder="Postcode/ZIP">
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="creat_account">
                                <input type="checkbox" id="f-option2" name="selector">
                                <label for="f-option2">Create an account?</label>
                            </div>
                        </div>
                        <div class="col-md-12 form-group mb-0">
                            <div class="creat_account">
                                <h3>Shipping Details</h3>
                                <input type="checkbox" id="f-option3" name="selector">
                                <label for="f-option3">Ship to a different address?</label>
                            </div>
                            <textarea class="form-control" name="message" id="message" rows="1" placeholder="Order Notes"></textarea>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4">
                    <div class="order_box">
                        <h2>Your Order</h2>
                        <ul class="list">
                            <li><a href="#"><h4>Product <span>Total</span></h4></a></li>
                            <li><a href="#">Fresh Blackberry <span class="middle">x 02</span> <span class="last">$720.00</span></a></li>
                            <li><a href="#">Fresh Tomatoes <span class="middle">x 02</span> <span class="last">$720.00</span></a></li>
                            <li><a href="#">Fresh Brocoli <span class="middle">x 02</span> <span class="last">$720.00</span></a></li>
                        </ul>
                        <ul class="list list_2">
                            <li><a href="#">Subtotal <span id="totalPrice">$2160.00</span></a></li>
                            <li><a href="#">Shipping <span>Flat rate: $50.00</span></a></li>
                            <li><a href="#">Total <span>$2210.00</span></a></li>
                        </ul>
                        <div class="payment_item">
                            <div class="radion_btn">
                                <input type="radio" id="f-option5" name="selector">
                                <label for="f-option5">Check payments</label>
                                <div class="check"></div>
                            </div>
                            <p>Please send a check to Store Name, Store Street, Store Town, Store State / County,
                                Store Postcode.</p>
                        </div>
                        <div class="payment_item active">
                            <div class="radion_btn">
                                <input type="radio" id="f-option6" name="selector">
                                <label for="f-option6">Paypal </label>
                                <img src="/static/img/product/card.jpg" alt="">
                                <div class="check"></div>
                            </div>
                            <p>Pay via PayPal; you can pay with your credit card if you don’t have a PayPal
                                account.</p>
                        </div>
                        <div class="creat_account">
                            <input type="checkbox" id="f-option4" name="selector">
                            <label for="f-option4">I’ve read and accept the </label>
                            <a href="#">terms & conditions*</a>
                        </div>
                        <div class="text-center">
                          <input type="button" id="purchase" class="button button-paypal" value="Proceed to Paypal"></input>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </section>
  <!--================End Checkout Area =================-->





</body>
</html>