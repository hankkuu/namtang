<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: ksw
  Date: 07/01/2020
  Time: 4:59 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Aroma Shop - Product Details</title>
    <style>
        .starR{
            background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;
            background-size: auto 100%;
            width: 30px;
            height: 30px;
            display: inline-block;
            text-indent: -9999px;
            cursor: pointer;
        }
        .starR.on{background-position:0 0;}
        /*.starR1{*/
        /*    background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat -52px 0;*/
        /*    background-size: auto 100%;*/
        /*    width: 15px;*/
        /*    height: 30px;*/
        /*    float:left;*/
        /*    text-indent: -9999px;*/
        /*    cursor: pointer;*/
        /*}*/
        /*.starR2{*/
        /*    background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;*/
        /*    background-size: auto 100%;*/
        /*    width: 15px;*/
        /*    height: 30px;*/
        /*    float:left;*/
        /*    text-indent: -9999px;*/
        /*    cursor: pointer;*/
        /*}*/
        /*.starR1.on{background-position:0 0;}*/
        /*.starR2.on{background-position:-15px 0;}*/

    </style>
    <script>

        $(function () {
            $("#add_cart").on('click' , function(){

                let bookIdx = ${book.get().bookIdx};
                let bookTitle = "${book.get().bookTitle}";
                let bookPrice = ${book.get().bookPrice};
                let bookImg = "${book.get().bookImg}";
                let qty =$("#cate_amount option:selected").val();

                let add_to = {
                    bookIdx : bookIdx,
                    bookTitle : bookTitle,
                    bookImg : bookImg,
                    bookPrice : bookPrice,
                    qty : qty
                }
                console.log(add_to);

                $.ajax({
                    type : "get",
                    url : "/cart/insert",
                    dataType : "json",
                    data : add_to,
                    error : function(err){

                        console.log(err);
                    },
                    success : function(result){
                        // $("#content").html(Parse_data); //div에 받아온 값을 넣는다.
                        if(result.statusCode === "Success") {
                            alert(result.message);
                        }

                    }

                });
            });

        })

        $(document).ready(function(){
            $('.starRev span').click(function(){
                $(this).parent().children('span').removeClass('on');
                $(this).addClass('on').prevAll('span').addClass('on');
                return false;
            });
        });

         function clicka() {
             $("#reviewScore").val(2);
         }
         function clickb() {
             $("#reviewScore").val(4);
         }
        function clickc() {
            $("#reviewScore").val(6);
        }
        function clickd() {
            $("#reviewScore").val(8);
        }
        function clicke() {
            $("#reviewScore").val(10);
        }

    </script>

</head>
<body>
<!-- ================ start banner area ================= -->
<section class="blog-banner-area" id="blog">
    <div class="container h-100">
        <div class="blog-banner">
            <div class="text-center">
                <h1>Product Details</h1>
                <nav aria-label="breadcrumb" class="banner-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Shop Single</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- ================ end banner area ================= -->


<!--================Single Product Area =================-->
<div class="product_image_area">
    <div class="container">
        <div class="row s_product_inner" style="margin-left: 150px;">
            <div>
                <div>
                    <div class>
                        <img class="img-fluid" src="${book.get().bookImg}" style="width: 250px;margin-right: 100px;border-top-width: 30px;margin-top: 70px;">
                    </div>
                    <!-- <div class="single-prd-item">
                        <img class="img-fluid" src="img/category/s-p1.jpg" alt="">
                    </div>
                    <div class="single-prd-item">
                        <img class="img-fluid" src="img/category/s-p1.jpg" alt="">
                    </div> -->
                </div>
            </div>
            <div class="col-lg-5 offset-lg-1">
                <div class="s_product_text">

                    <h3>${book.get().bookTitle}</h3>
                    <h2><fmt:formatNumber  value="${book.get().bookPrice}"  type="currency" /></h2>
                    <ul class="list">
                        <li><a class="active" href="#"><span>Category</span> : ${book.get().category.catgName}</a></li>
                        <li><a href="#"><span>Author</span> : ${book.get().bookAuthor}</a></li>
                        <li><a href="#"><span>Publisher</span> : ${book.get().bookPublisher}</a></li>
                    </ul>
                    <p>${book.get().bookDesc}</p>
                    <div class="product_count">
                        <%--                        <label for="qty">Quantity:</label>--%>
                        <h6>Quantity</h6>
                        <select name="cate_amount" id='cate_amount'>
                            <c:forEach begin="1" end="10" var="i">
                                <option value="${i}">${i}</option>
                            </c:forEach>

                        </select>
                        <p></p>
                        <%--                        <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"--%>
                        <%--                                class="increase items-count" type="button"><i class="ti-angle-left"></i></button>--%>
                        <%--                        <input type="text" name="qty" id="sst" size="2" maxlength="12" value="1" title="Quantity:" class="input-text qty">--%>

                        <%--                        <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"--%>
                        <%--                                class="reduced items-count" type="button"><i class="ti-angle-right"></i></button>--%>

                        &emsp;&emsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&emsp;&emsp;<a class="button primary-btn" href="#" id="add_cart">Add to Cart</a>

                    </div>
                    <div class="card_area d-flex align-items-center">
                        <a class="icon_btn" href="#"><i class="lnr lnr lnr-diamond"></i></a>
                        <a class="icon_btn" href="#"><i class="lnr lnr lnr-heart"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--================End Single Product Area =================-->

<!--================Product Description Area =================-->
<section class="product_description_area">
    <div class="container">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Description</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
                   aria-selected="false">Specification</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact"
                   aria-selected="false">Comments</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" id="review-tab" data-toggle="tab" href="#review" role="tab" aria-controls="review"
                   aria-selected="false">Reviews</a>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="home-tab">
                <p>Beryl Cook is one of Britain’s most talented and amusing artists .Beryl’s pictures feature women of all shapes
                    and sizes enjoying themselves .Born between the two world wars, Beryl Cook eventually left Kendrick School in
                    Reading at the age of 15, where she went to secretarial school and then into an insurance office. After moving to
                    London and then Hampton, she eventually married her next door neighbour from Reading, John Cook. He was an
                    officer in the Merchant Navy and after he left the sea in 1956, they bought a pub for a year before John took a
                    job in Southern Rhodesia with a motor company. Beryl bought their young son a box of watercolours, and when
                    showing him how to use it, she decided that she herself quite enjoyed painting. John subsequently bought her a
                    child’s painting set for her birthday and it was with this that she produced her first significant work, a
                    half-length portrait of a dark-skinned lady with a vacant expression and large drooping breasts. It was aptly
                    named ‘Hangover’ by Beryl’s husband and</p>
                <p>It is often frustrating to attempt to plan meals that are designed for one. Despite this fact, we are seeing
                    more and more recipe books and Internet websites that are dedicated to the act of cooking for one. Divorce and
                    the death of spouses or grown children leaving for college are all reasons that someone accustomed to cooking for
                    more than one would suddenly need to learn how to adjust all the cooking practices utilized before into a
                    streamlined plan of cooking that is more efficient for one person creating less</p>
            </div>
            <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                <div class="table-responsive">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td>
                                <h5>Width</h5>
                            </td>
                            <td>
                                <h5>128mm</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>Height</h5>
                            </td>
                            <td>
                                <h5>508mm</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>Depth</h5>
                            </td>
                            <td>
                                <h5>85mm</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>Weight</h5>
                            </td>
                            <td>
                                <h5>52gm</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>Quality checking</h5>
                            </td>
                            <td>
                                <h5>yes</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>Freshness Duration</h5>
                            </td>
                            <td>
                                <h5>03days</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>When packeting</h5>
                            </td>
                            <td>
                                <h5>Without touch of hand</h5>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h5>Each Box contains</h5>
                            </td>
                            <td>
                                <h5>60pcs</h5>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="comment_list">
                            <div class="review_item">
                                <div class="media">
                                    <div class="d-flex">
                                        <img src="img/product/review-1.png" alt="">
                                    </div>
                                    <div class="media-body">
                                        <h4>Blake Ruiz</h4>
                                        <h5>12th Feb, 2018 at 05:56 pm</h5>
                                        <a class="reply_btn" href="#">Reply</a>
                                    </div>
                                </div>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                    commodo</p>
                            </div>
                            <div class="review_item reply">
                                <div class="media">
                                    <div class="d-flex">
                                        <img src="img/product/review-2.png" alt="">
                                    </div>
                                    <div class="media-body">
                                        <h4>Blake Ruiz</h4>
                                        <h5>12th Feb, 2018 at 05:56 pm</h5>
                                        <a class="reply_btn" href="#">Reply</a>
                                    </div>
                                </div>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                    commodo</p>
                            </div>
                            <div class="review_item">
                                <div class="media">
                                    <div class="d-flex">
                                        <img src="img/product/review-3.png" alt="">
                                    </div>
                                    <div class="media-body">
                                        <h4>Blake Ruiz</h4>
                                        <h5>12th Feb, 2018 at 05:56 pm</h5>
                                        <a class="reply_btn" href="#">Reply</a>
                                    </div>
                                </div>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                    commodo</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="review_box">
                            <h4>Post a comment</h4>
                            <form class="row contact_form" action="contact_process.php" method="post" id="contactForm" novalidate="novalidate">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Your Full name">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email Address">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="number" name="number" placeholder="Phone Number">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <textarea class="form-control" name="message" id="message" rows="1" placeholder="Message"></textarea>
                                    </div>
                                </div>
                                <div class="col-md-12 text-right">
                                    <button type="submit" value="submit" class="btn primary-btn">Submit Now</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="row total_rate">
                            <div class="col-6">
                                <div class="box_total">
                                    <h5>Overall</h5>
                                    <h4>4.0</h4>
                                    <h6>(03 Reviews)</h6>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="rating_list">
                                    <h3>Based on 3 Reviews</h3>
                                    <ul class="list">
                                        <li><a href="#">5 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                        <li><a href="#">4 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                        <li><a href="#">3 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                        <li><a href="#">2 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                        <li><a href="#">1 Star <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i
                                                class="fa fa-star"></i><i class="fa fa-star"></i> 01</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <c:choose>
                        <c:when test="${fn:length(reviewcc) == 0}">
                        <div class="review_list">
                            <div class="review_item">
                                <div class="media">
                                    <div class="d-flex">
                                        <img src="img/product/review-1.png" alt="">
                                    </div>
                                    <div class="media-body">
                                        <h4>현재 리뷰 없음!</h4>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                </div>
                                <p>리뷰 데이터 없음!</p>
                            </div>
                        </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="reviewc" items="${reviewcc}">
                            <div class="review_list">
                                <div class="review_item">
                                    <div class="media">
                                        <div class="d-flex">
                                            <img src="img/product/review-1.png" alt="">
                                        </div>
                                        <div class="media-body">
                                            <h4>${reviewc.reviewTitle}</h4>
                                            <c:choose>
                                                <c:when test="${reviewc.reviewScore == 2}">
                                                    <i class="fa fa-star"></i>
                                                </c:when>
                                                <c:when test="${reviewc.reviewScore == 4}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </c:when>
                                                <c:when test="${reviewc.reviewScore == 6}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </c:when>
                                                <c:when test="${reviewc.reviewScore == 8}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </c:when>
                                                <c:when test="${reviewc.reviewScore == 10}">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <p>${reviewc.reviewContent}</p>
                                </div>
                            </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>




<%--
                            <div class="review_item">
                                <div class="media">
                                    <div class="d-flex">
                                        <img src="img/product/review-2.png" alt="">
                                    </div>
                                    <div class="media-body">
                                        <h4>Blake Ruiz</h4>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                </div>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                    commodo</p>
                            </div>
                            <div class="review_item">
                                <div class="media">
                                    <div class="d-flex">
                                        <img src="img/product/review-3.png" alt="">
                                    </div>
                                    <div class="media-body">
                                        <h4>Blake Ruiz</h4>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                </div>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                                    dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
                                    commodo</p>
                            </div>
--%>

<%--                    <div class="col-lg-6">--%>
<%--                        <div class="review_box">--%>
                    <div>
                        <div>
                            <h4>Add a Review</h4>
                            <p>Your Rating:</p>
<%--                            <ul class="list">--%>
                            <form action="/reviewReg" class="form-contact form-review mt-3" method="post">
                                <div class="starRev" id="starRate">
                                    <span class="starR on" id="half" onclick="clicka();">별1</span>
                                    <span class="starR" id="one" onclick="clickb();">별2</span>
                                    <span class="starR" id="onehalf" onclick="clickc();">별3</span>
                                    <span class="starR" id="two" onclick="clickd();">별4</span>
                                    <span class="starR" id="twohalf" onclick="clicke();">별5</span>
                                </div>
<%--                            <div class="starRev" id="starRate">--%>
<%--                                <span class="starR1 on" id="half" onclick="clicka();">별1_왼쪽</span>--%>
<%--                                <span class="starR2" id="one" onclick="clickb();">별1_오른쪽</span>--%>
<%--                                <span class="starR1" id="onehalf" onclick="clickc();">별2_왼쪽</span>--%>
<%--                                <span class="starR2" id="two" onclick="clickd();">별2_오른쪽</span>--%>
<%--                                <span class="starR1" id="twohalf" onclick="clicke();">별3_왼쪽</span>--%>
<%--                                <span class="starR2" id="three" onclick="clickf();">별3_오른쪽</span>--%>
<%--                                <span class="starR1" id="threehalf" onclick="clickg();">별4_왼쪽</span>--%>
<%--                                <span class="starR2" id="four" onclick="clickh();">별4_오른쪽</span>--%>
<%--                                <span class="starR1" id="fourhalf" onclick="clicki();">별5_왼쪽</span>--%>
<%--                                <span class="starR2" id="five" onclick="clickj();">별5_오른쪽</span>--%>
<%--                            </div>--%>
<%--                                <li><a href="#"><i class="fa fa-star"></i></a></li>--%>
<%--                                <li><a href="#"><i class="fa fa-star"></i></a></li>--%>
<%--                                <li><a href="#"><i class="fa fa-star"></i></a></li>--%>
<%--                                <li><a href="#"><i class="fa fa-star"></i></a></li>--%>
<%--                                <li><a href="#"><i class="fa fa-star"></i></a></li>--%>
<%--                            </ul>--%>
                            <p>Outstanding</p>
<%--                            <form action="/reviewReg" class="form-contact form-review mt-3" method="post">--%>
<%--                                <div class="form-group">--%>
<%--                                    <input class="form-control" name="reviewerId" type="text" placeholder="Enter your name" required>--%>
<%--                                </div>--%>
                                <div class="form-group">
                                    <input class="form-control" name="reviewTitle" type="text" placeholder="Enter Subject">
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control different-control w-100" name="reviewContent" id="textarea" cols="30" rows="5" placeholder="Enter Message"></textarea>
                                </div>

                                    <input type="hidden" name="reviewScore" id="reviewScore" value=2>
                                    <input type="hidden" name="bookIdx" value="${book.get().bookIdx}">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                                <div class="form-group text-center text-md-right mt-3">
                                    <button type="submit" class="button button--active button-review">Submit Now</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
<!--================End Product Description Area =================-->

<!--================ Start related Product area =================-->
<section class="related-product-area section-margin--small mt-0">
    <div class="container">
        <div class="section-intro pb-60px">
            <p>Popular Item in the market</p>
            <h2>Top <span class="section-intro__style">Product</span></h2>
        </div>
        <div class="row mt-30">
            <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                <div class="single-search-product-wrapper">
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-1.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-2.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-3.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                <div class="single-search-product-wrapper">
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-4.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-5.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-6.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                <div class="single-search-product-wrapper">
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-7.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-8.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-9.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
                <div class="single-search-product-wrapper">
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-1.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-2.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                    <div class="single-search-product d-flex">
                        <a href="#"><img src="img/product/product-sm-3.png" alt=""></a>
                        <div class="desc">
                            <a href="#" class="title">Gray Coffee Cup</a>
                            <div class="price">$170.00</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================ end related Product area =================-->


<%--    window.onload = function(){--%>
<%--        var td = new Date();                   //오늘 날짜--%>

<%--        var yyyy = td.getFullYear();           //년도--%>

<%--        var mm = "" + (td.getMonth()+1);    //월--%>

<%--        var dd = "" + td.getDate();           //일--%>



<%--        if(mm.length < 2) mm = "0" + mm;--%>

<%--        if(dd.length < 2) dd = "0" + dd;--%>



<%--        var Today = yyyy.toString() + mm + dd;--%>



<%--        document.getElementById('todayDate').value = Today;--%>

<%--    }--%>

<%--</script>--%>
</body>
</html>