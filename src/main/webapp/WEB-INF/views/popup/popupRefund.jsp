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
                let params = window.location.search;
                //let decode = decodeURI(params);
                let id = params.substring(params.indexOf("=") + 1);

                $.ajax({
                    type: "get",
                    url: "/api/v1/purchase/myPurchaseDetail",
                    data: {id: id},
                    dataType: "json",
                    success: function (result) {

                        if (result.statusCode === "Success") {
                            let str = "";
                            let list = JSON.parse(result.message);
                            for (let i = 0; i < list.length; i++) {

                                console.log(list[i]);


                                str += '<tr>'
                                str += '<td><input type="checkbox" id="bookChk' + i + '" name="check" /></td>'
                                str += '<td>' + list[i].bookName + '</td>'
                                str += '<td><input type="text" id="bookCount' + i + '" value='+list[i].count+' /></td>'
                                str += '<td><input type="text" width="300px" id="refundReason' + i + '" val="" />'
                                str += '<input type="hidden" id="orderIdx' + i + '" value=' + id + ' />'
                                str += '<input type="hidden" id="bookIdx' + i + '" value=' + list[i].bookIdx + ' />'

                                str += '</td>'
                                str += '<td><input type="checkbox" id="refundChk' + i + '" /></td>'
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

        function onRefund() {
            let checkbox = $("input:checkbox[name=check]:checked");//체크된 모든박스
            let rowData = new Array();
            let refundList = [];
            let type = 2;
            checkbox.each(function(i) {

                // checkbox.parent() : checkbox의 부모는 <td>이다.
                // checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
                let tr = checkbox.parent().parent().eq(i);
                let td = tr.children();

                // 체크된 row의 모든 값을 배열에 담는다.
                rowData.push(tr.text());

                // td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
                let bookName = td.eq(1).text();
                let bookCount = td.eq(2).children().eq(0).val();
                let reason = td.eq(3).children().eq(0).val();
                let orderIdx = td.eq(3).children().eq(1).val();
                let bookIdx = td.eq(3).children().eq(2).val();
                console.log(td.eq(4).children().eq(0).val());
                let refundCode = 0;
                // 여기 다시해야 함
                if(td.eq(4).children().eq(0).val() === "on") {
                    refundCode = 1;
                } else {
                    refundCode = 2;
                }


                // 가져온 값을 배열에 담는다.
                let refund = {
                    //type : type,
                    orderIdx: orderIdx,
                    bookIdx: bookIdx,
                    bookName : bookName,
                    bookCount : bookCount,
                    reason : reason,
                    refundCode : refundCode
                }
                refundList.push(refund);

            });

            $.ajax({
                type: "post",
                url: "/api/v1/purchase/refund",
                data: JSON.stringify({
                    list: refundList
                }),
                dataType: "json",
                contentType: 'application/json; charset=utf-8',
                success: function (result) {

                    if (result.statusCode === "Success") {
                        alert("성공");
                    }

                },
                error: function (request, status, error) {
                    console.log('code:' + request.status + "\n" + 'message : ' + request.responseText + "\n" + 'error:' + error);
                }
            });

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
                            <th scope="col">판매자 귀책여부</th>


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