<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Aroma Shop - Checkout</title>

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <script type="text/javascript">
        const IMP = window.IMP; // 생략해도 괜찮습니다.
        IMP.init("imp13182886"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.'

        $(window).load(function () {
            $(function () {
                let list = JSON.parse(sessionStorage.getItem("cartlist"));
                let str = "";
                let strCut = "";
                let nb = 0;
                let sum = 0;
                
                for(let i = 0 ; i < list.length; i++) {
                    console.log(i);
                    
					str += '<tr>'
                    str += '<td>'
                    /* str += '<img class=\"card-img\" src=\" \">'list[i].img */
                    str += '<img id="list[' + i + '].img" src=' + list[i].img + '>'
                    str += '<input type="hidden" name="bookIdx" id="bookIdx' + i + '" value=' + i + '>'
                    str += '</td>'
                    str += '<td id="list[' + i + '].title" + >' + list[i].title + '</td>'
                    str += '<td id="list[' + i + '].price">' + list[i].price + '</td>'
                    str += '<td id="list[' + i + '].qty">&emsp;'+ list[i].qty +'</td>'
                    str += '<td id="list[' + i + '].total">'+ list[i].total +'</td>'
                    str += '</tr>'
                    
                    strCut = list[i].total.substr(1,list[i].price.length);
                    strCut = strCut.split(',');
                    nb = parseInt(strCut[0]+strCut[1]);
                    sum += nb;
                    
                    // str += '<tr>'
                    // str += '<td></td>'
                    // str += '<td></td>'
                    // str += '<td><h5>Subtotal</h5></td>'
                    // str += '<td> <h5 id="priceSum">￦0</h5> </td>'
                    // str += '</tr>'
                }
                $("#cartList").html(str);
                
                var receiverName = $("#receiverName").val();
            	console.log(receiverName);
                
                if(sum > 10000){
                	$("#shipping").html(0);
                	$("#totalPrice").html(sum);
                } else {
                	$("#shipping").html(2500);
                	let s = 2500;
                	$("#totalPrice").html(sum + s);
                }
                //$("#list").html(product);
            });

            console.log("start");

            $("#purchase").click(async () => {
            	
            	let list = JSON.parse(sessionStorage.getItem("cartlist"));
            	console.log(list);
            	let purchaseBook = [];
                    let priceList = []
                    for(let i = 0; i < list.length; i++) {




                    }
            		for(let i = 0; i < list.length; i++) {
                        let regExp = /[\{\}\[\]\/?.,;:|\)*￦~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
                        let price = "";
                        if(regExp.test(list[i].price)) {
                            price = list[i].price.replace(regExp, "");
                            //특수문자를 대체. ""
                        }
            		    let item = {
            				purchaseBookId : {
            					bookIdx : list[i].id
            				},
            			price: price,
            			name: list[i].title, 
            			count: list[i].qty, 
            			imagePath: list[i].img
                	}
            		purchaseBook.push(item);
            	}	 
            	
            	await purchaseProcess().then(async (result) => {

                    if (result.statusCode === "Success") {
                        const key = result.message;
                        
                        console.log("key: " + key);
                        
                        if (key !== undefined) {
                            let purchaseObj = {
                                purchasePayment: {
                                	receiverName: "tester", // $("#receiverName").val(),
                                    totalPrice: "30000", //$("#totalPrice").text(),
                                    receiverPhone: "123456", // $("#phoneNumber").val(),
                                    deliveryComment: "18181818", //$("#deliveryComment").val(),
                                    shippingPrice: "100",  //$("#shipping").text(),
                                    paymentCode: "1" //$("input[type=radio][name=selector]:checked").val()
                                },
                                
                                purchaseOrder: {
                                    deliveryAddress: $("#userAddress").val()
                                },
                                purchaseBook: purchaseBook
                                // [{
                                //     purchaseBookId: {
                                //         bookIdx: "1"
                                //     },
                                //     price: "10000",
                                //     name: "난책1",
                                //     count: "3",
                                //     imagePath: " "
                                // }, {
                                //     purchaseBookId: {
                                //         bookIdx: "20"
                                //     },
                                //     price: "20000",
                                //     name: "난책2",
                                //     count: "1",
                                //     imagePath: "test"
                                // }]
                                ,
                                billKey: key
                            }                           
                            //var uui = $("#list[0]price").val();
                            //console.log(uui);
                            
                            //console.log("purchaseObj??" + purchaseObj);

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
                                escrow: true,
                                currency: "KRW",
                                custom_data: purchaseObj
                            };
                            let param = {
                                pg: shop.pg,
                                pay_method: shop.pay_method,
                                escrow: shop.escrow,
                                currency: shop.currency,
                                custom_data: shop.custom_data,
                                merchant_uid: key,
                                name: "임시로 사용하는 이름이다",
                                amount: "30000", //total값 넣기
                                buyer_email: user.email,
                                buyer_name: user.name,
                                buyer_tel: user.tel,
                                buyer_addr: user.addr,
                                buyer_postcode: user.postcode
                            };

                            const test = await setPuchase(purchaseObj).then(async (dbResult) => {
                                console.log(dbResult);
                                if (dbResult.data.statusCode === "Success") {
                                    // const test2 = await verifyPurchase(param).then( (confirmResult) => {
                                    //
                                    //     if(confirmResult.data.statusCode === "Success") {
                                    //         console.log(confirmResult);
                                    //         alert("진짜 최종 완료");
                                    //     }
                                    // });
                                    IMP.request_pay(param,
                                        (rsp) => {
                                            // callback
                                            if (rsp.success) {
                                                console.log(rsp);
                                                // axios로 HTTP 요청
                                                axios({
                                                    url: "/api/v1/purchase/complete", // 가맹점 서버
                                                    method: "post",
                                                    headers: {"Content-Type": "application/json"},
                                                    data: {
                                                        imp_uid: rsp.imp_uid,
                                                        merchant_uid: rsp.merchant_uid,
                                                        paid_amount: rsp.paid_amount,
                                                    }
                                                }).then((result) => {
                                                    // 가맹점 서버 결제 API 성공시 로직
                                                    // 이제 DB 입력을 해도 된다
                                                    //console.log(data);

                                                    if (result.data.statusCode === "Success") {
                                                        alert("최종 구매 성공 success");
                                                        let param = JSON.stringify(result.data.message);
                                                        location.replace('/user/confirmation?msg=' + encodeURI(param));
                                                        //location.href='/confirmation';
                                                    } else {
                                                        alert("최종 구매 실패");
                                                    }
                                                    return result;
                                                })
                                            } else {
                                                alert("검증실패 data 확인하세요");
                                                //...,
                                                // 결제 실패 시 로직,
                                                //...
                                            }
                                        });
                                }
                            });
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

        const setPuchase = async (purchaseObj) => {
            // 필요함 session
            const user = {}
            // axios로 HTTP 요청
            const result = await axios({
                url: "/api/v1/purchase", // 가맹점 서버
                method: "post",
                headers: {"Content-Type": "application/json"},
                data: {
                    book: purchaseObj.purchaseBook,
                    order: purchaseObj.purchaseOrder,
                    payment: purchaseObj.purchasePayment,
                    billKey: purchaseObj.billKey,
                    user: {
                        userIdx: "1"
                    }
                }
            }).then((data) => {
                // 가맹점 서버 결제 API 성공시 로직
                // 이제 DB 입력을 해도 된다
                alert("헬로 success");
                return data;
            })
            return result;
        }

        // const verifyPurchase = async (param) => {
        //     // IMP.request_pay(param, callback) 호출
        //     const result = await IMP.request_pay(param,
        //         async (rsp) => {
        //         // callback
        //         if (rsp.success) {
        //             console.log(rsp);
        //             // axios로 HTTP 요청
        //             await axios({
        //                 url: "/api/v1/purchase/complete", // 가맹점 서버
        //                 method: "post",
        //                 headers: { "Content-Type": "application/json" },
        //                 data: {
        //                     imp_uid: rsp.imp_uid,
        //                     merchant_uid: rsp.merchant_uid,
        //                     paid_amount: rsp.paid_amount,
        //                 }
        //             }).then((data) => {
        //                 // 가맹점 서버 결제 API 성공시 로직
        //                 // 이제 DB 입력을 해도 된다
        //                 console.log(data);
        //                 //alert("최종 구매 성공 success");
        //                 return data;
        //             })
        //         } else {
        //             alert("검증실패 data 확인하세요");
        //             //...,
        //             // 결제 실패 시 로직,
        //             //...
        //         }
        //     });
        //     return result;
        // }
        
        /* 주소찾기 */
	  	function openDaumZipAddress() {
			new daum.Postcode({
				oncomplete:function(data) {
					$("#userAddress").val(data.address);
					$("#userAddressDetail").focus();
					$("#userPost").val(data.postcode1 +"-"+ data.postcode2 + "-"+data.zonecode);					
					console.log(data.postcode1 +"-"+ data.postcode2 + "-"+data.zonecode);				
				}
			}).open();
		}
        
        /* 기존 주소 */
        function openPopup(){
        	var _width = '650';
            var _height = '380';
			
            // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
            var _left = Math.ceil(( window.screen.width - _width )/2);
            var _top = Math.ceil(( window.screen.width - _height )/2); 
            window.open('/url', 'popup-test', 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top );
        }
    </script>
    <style>
    #userPost{
    	display:inline-block;
    }
    </style>
</head>
<body>

<!-- ================ start banner area ================= -->
<section class="cart_area">
    <div class="container">
        <div class="cart_inner">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Product</th>
                            <th scope="col">ProductName</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                        </tr>
                    </thead>
                    <tbody id="cartList">
                     
                    </tbody>
                </table>
            </div>
            <tr class="out_button_area">
                <td class="d-none-l">
                </td>
                <td>
                </td>
                <td>
                </td>
                <td>
                    <div class="checkout_btn_inner d-flex align-items-center">
                        <a class="button primary-btn" href="#" onclick="CatgPage(0)">계속 쇼핑하기</a>
                    </div>
                </td>
            </tr>
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
                            <input type="text" class="form-control" id="orderName" name="name">
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
                            <input type="text" class="form-control" id="receiverName" name="compemailany">
                            <span class="placeholder" data-placeholder="Email Address"></span>
                        </div>
                        <div class="col-md-12 form-group">
							<input type="text" id="userPost" class="form-control" name="userPost" style="width:278px;" value="우편번호" disabled/>
							<input type="button" onClick="openDaumZipAddress()" value = "주소 찾기" />
							<input type="button" onClick="openPopup()" value = "기존 주소" />
							<br/>
							<input type="text" id="userAddress" class="form-control" name="userAddress" value="주소" disabled/>
							<input type="text" id="userAddressDetail" class="form-control" name="userAddressDetail" placeholder="상세주소" onfocus="this.placeholder = ''" onblur="this.placeholder = '상세주소'"/>
						</div>
                        <div class="col-md-6 form-group p_star">
                            <span class="placeholder" data-placeholder="Phone number">연락처</span>
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
                            <span class="placeholder" data-placeholder="Address line 01"></span>
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
                            <textarea class="form-control" name="message" id="deliveryComment" rows="1"
                                      placeholder="배송시 요청 사항"></textarea>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4">
                    <div class="order_box">
                        <h2>주문 정보</h2>
                        <table id="list">
	                        <tr>
	                            <td><a href="#"><h4>상품 <span>금액</span></h4></a></td>
	                        </tr>
                        </table>
                        <ul class="list list_2">
                            <li><a href="#">배송비 <span id="shipping"></span></a></li>
                            <li><a href="#">총 금액<span id="totalPrice"></span></a></li>
                        </ul>
                        <div class="payment_item">
                            <div class="radion_btn">
                                <input type="radio" id="f-option5" name="selector" value="1">
                                <label for="f-option5">가상계좌</label>
                                <div class="check"></div>
                            </div>
                            <p>가상계좌 관련 내용.</p>
                        </div>
                        <div class="payment_item active">
                            <div class="radion_btn">
                                <input type="radio" id="f-option6" name="selector" value="2">
                                <label for="f-option6">카드 </label>
                                <img src="/img/product/card.jpg" alt="">
                                <div class="check"></div>
                            </div>
                            <p>카드 관련 내용.</p>
                        </div>
                        <div class="creat_account">
                            <input type="checkbox" id="f-option4" name="selector">
                            <label for="f-option4">I’ve read and accept the </label>
                            <a href="#">terms & conditions*</a>
                        </div>
                        <div class="text-center">
                            <input type="button" id="purchase" class="button button-paypal"
                                   value="Proceed to Paypal"></input>
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