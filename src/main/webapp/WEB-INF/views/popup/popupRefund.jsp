<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Aroma Shop - MyPruchase</title>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script>
        $(window).on('load', function () {
            $(function () {
                 let para = document.location.href.split("?");
                 console.log(para[1]);
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
                                                    str += '<td><input type="checkbox" id="bookChk" /></td>'
                                                    str += '<td>' + list[i].bookName + '</td>'
                                                    str += '<td>' + list[i].count + '</a></td>'
                                                    str += '<td><input type="text" width="300px" id="refundReason" /></td>'

                                                    str += '<input type="hidden" id="bookIdx" value='+list[i].bookIdx+' />'
                                                    str += '<input type="hidden" id="orderIdx" value='+para[1]+' />'
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
        function onExchange(sipal) {
            console.log(sipal);
        }

        function onRefund(sipal) {

        }
        </script>

        </head>
        <body>
        <!--================Cart Area =================-->
        <section class="cart_area">
                <div class="container">
                        <div class="cart_inner">
                                <div class="table-responsive">
                                    <form method="post" id="refundForm">
                                        <table class="table">
                                                <thead>
                                                <tr>
                                                        <th scope="col">선택</th>
                                                        <th scope="col">상품명</th>
                                                        <th scope="col">수량</th>
                                                        <th scope="col">사유</th>


                                                </tr>
                                                </thead>

                                                <tbody id="cartList">

                                                </tbody>

                                        </table>
                                        <button type="button" id="exchange" value="exchange" onclick="onExchange();">교환</button>
                                        <button type="button" id="refund" value="refund" onclick="onRefund();">환불</button>
                                    </form>
                                </div>

                        </div>
                </div>
        </section>
        <!--================End Cart Area =================-->

        </body>
        </html>