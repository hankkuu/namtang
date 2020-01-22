<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script type="text/javascript">
        $(document).ready(function () {
            let para = document.location.href.split("?");
            console.log(para);


            let params = window.location.search;
            let decode = decodeURI(params);
            let str = decode.substring(decode.indexOf("=") + 2);
            let replaceStr = str.slice(0, -1);
            replaceStr = replaceStr.replace(/\\/ig, "").replace("\"\"{", "{").replace("}\"\"", "}");
            let json = JSON.parse(replaceStr);
            
            console.log("json : " + json);
            
            $("#orderNumber").html(json.billKey);
            $("#orderDate").html(json.orderDate);            
            if(json.paymentCode == "1"){
            	$("#orderInfo").html("가상계좌");
            } else {
            	$("#orderInfo").html("카드");
            }
            
            $("#receiverName").html(json.receiverName);
            $("#phone").html(json.phone);
            
            $("#address").html(json.address);
            $("#addressDetail").html(json.addressDetail);
            $("#totalPrice").html(json.totalPrice);
            
            
            // let sum = 0;
            //
            // /*  for(let i = 0 ; i < json.length; i++) {
            //
			// 	strg += '<tr>'
            //     strg += '<td>'+ json[i].title +'</td>'
            //     strg += '<td>'+ json[i].qty +'</td>'
            //     strg += '<td>'+ json[i].total +'</td>'
            //     strg += '</tr>'
            //
            //     strCut = json[i].total.substr(1,json[i].price.length);
            //     strCut = strCut.split(',');
            //     nb = parseInt(strCut[0]+strCut[1]);
            //     sum += nb;
            // }  */
            //
            // $("#product").html(strg);
            //
            // if(sum > 10000){
            // 	$("#orderShipping").html(0);
            // 	$(".totalPrice").html(sum);
            // 	$(".totalPrice").html(sum);
            // } else {
            // 	$("#orderShipping").html(2500);
            // 	let s = 2500;
            // 	$(".totalPrice").html(sum + s);
            // 	$(".totalPrice").html(sum + s);
            // }
        })
    </script>

</head>
<body>

<!-- ================ start banner area ================= -->
<section class="blog-banner-area" id="category">
    <div class="container h-100">
        <div class="blog-banner">
            <div class="text-center">
                <h1>Order Confirmation</h1>
                <nav aria-label="breadcrumb" class="banner-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Shop Category</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- ================ end banner area ================= -->

<!--================Order Details Area =================-->
<section class="order_details section-margin--small">
    <div class="container">
        <p class="text-center billing-alert">주문이 완료되었습니다. 감사합니다.</p>
        <div class="row mb-5">
            <div class="col-md-6 col-xl-4 mb-4 mb-xl-0">
                <div class="confirmation-card">
                    <h3 class="billing-title">주문 정보</h3>
                    <table class="order-rable">
                        <tr>
                            <td>주문번호</td>
                            <td>:</td>
                            <td id="orderNumber"></td>
                        </tr>
                        <tr>
                            <td>날짜</td>
                            <td>:</td>
                            <td id="orderDate"></td>
                        </tr>
                        <tr>
                            <td>총 금액</td>
                            <td>:</td>
                            <td id="totalPrice"></td>
                        </tr>
                        <tr>
                            <td>주문방식</td>
                            <td>:</td>
                            <td id="orderInfo"></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="col-md-6 col-xl-4 mb-4 mb-xl-0">
                <div class="confirmation-card">
                    <h3 class="billing-title">회원 정보</h3>
                    <table class="order-rable">
                        <tr>
                            <td>받는사람</td>
                            <td>:</td>
                            <td id="receiverName"></td>
                        </tr>
                        <tr>
                            <td>핸드폰 번호</td>
                            <td>:</td>
                            <td id="phone"></td>
                        </tr>
                    </table>
                </div>
            </div> 
            <div class="col-md-6 col-xl-4 mb-4 mb-xl-0">
                <div class="confirmation-card">
                    <h3 class="billing-title">배송 정보</h3>
                    <table class="order-rable">
                        <tr>
                            <td>주소</td>
                            <td>:</td>
                            <td id="address"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td id="addressDetail"></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <!-- <div class="order_details_table">
            <h2>Order Details</h2>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Total</th>
                    </tr>
                    </thead>
                    <tbody id="product">
                    <tr>
                        <td>
                            <h4>순수책값</h4>
                        </td>
                        <td>
                            <h5></h5>
                        </td>
                        <td>
                            
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4>배송비</h4>
                        </td>
                        <td>
                            <h5></h5>
                        </td>
                        <td id="shipping">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h4>총 금액</h4>
                        </td>
                        <td>
                            <h5></h5>
                        </td>
                        <td>
                            <h4 class="totalPrice"></h4>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div> -->
    </div>
</section>
<!--================End Order Details Area =================-->
</body>
</html>