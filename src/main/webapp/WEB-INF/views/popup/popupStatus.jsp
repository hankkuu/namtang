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
                    let str = params.substring(params.indexOf("=") + 1);


                    $.ajax({
                    method: "get",
                    url: "/api/v1/purchase/myPurchaseStatus",
                    data: { id: str },
                    dataType:"json",
                    success: function (result) {

                        if(result.statusCode === "Success") {
                        let str = "";
                        let data = JSON.parse( result.message);
                        console.log(data);


                        str += '<tr>'
                        str += '<td>' + data.address + '</td>'
                        str += '<td>' +  data.phone + '</td>'
                        str += '<td>' + data.purchaseStatus + '</td>'

                        str += '</tr>'


                        $("#cartList").html(str);
                        }

                    },
                    error: function (request, status, error) {
                    console.log('code:' + request.status + "\n" + 'message : ' + request.responseText + "\n" + 'error:' + error);
                    }
                });
            });
        });
        </script>

        </head>
        <body>
        <!--================Cart Area =================-->
        <section class="cart_area">

            <div class="table-responsive">
                <table class="table">
                <thead>
                    <tr>
                        <th scope="col">주소</th>
                        <th scope="col">전화번호</th>
                        <th scope="col">구매상태</th>
                    </tr>
                </thead>

                <tbody id="cartList">

                </tbody>

                </table>
            </div>
        </section>
        <!--================End Cart Area =================-->

        </body>
        </html>