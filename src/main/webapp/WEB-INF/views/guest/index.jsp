<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Aroma Shop - Home</title>


  <style>
    @import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap&subset=korean');
    @import url('https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap');
    .card-product__title a {
      color: #222;
      font-size: initial;
      font-family: 'Noto Sans KR', sans-serif;
    }
      .hero-carousel__slideOverlay h3{
          color: #FFFFFF;
          font-size: 24px;
          font-family: 'Do Hyeon', sans-serif;
      }

    .slider .indicators .indicator-item {
        background-color: #666666;
        border: 3px solid #ffffff;
        -webkit-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
        -moz-box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    }
    .slider .indicators .indicator-item.active {
        background-color: #ffffff;
    }
    .slider {
        width: 900px;
        margin: 0 auto;
    }
    .slider .indicators {
        bottom: 60px;
        z-index: 100;
        /* text-align: left; */
    }

    .slider {
        position: relative;
        height: 600px;
        width: 100%
    }

    .slider.fullscreen {
        height: 100%;
        width: 100%;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0
    }

    .slider.fullscreen ul.slides {
        height: 100%
    }

    .slider.fullscreen ul.indicators {
        z-index: 2;
        bottom: 30px
    }

    .slider .slides {
        background-color: #9e9e9e;
        margin: 0;
        height: 600px
    }

    .slider .slides li {
        opacity: 0;
        position: absolute;
        top: 0;
        left: 0;
        z-index: 1;
        width: 100%;
        height: inherit;
        overflow: hidden
    }

    .slider .slides li img {
        height: 100%;
        width: 100%;
        background-size: cover;
        background-position: center
    }

    .slider .slides li .caption {
        color: #fff;
        position: absolute;
        top: 15%;
        left: 15%;
        width: 70%;
        opacity: 0%
    }

    .caption{
        font-size: xx-large;
    }
    .caption.top {
    }
    .caption.middle{
        margin-top: 250px;
    }
    .caption.bottom {
        margin-top: 350px;
    }



    .slider .slides li .caption p {
        color: #e0e0e0
    }

    .slider .slides li.active {
        z-index: 2
    }

    .slider .indicators {
        position: absolute;
        text-align: center;
        left: 0;
        right: 0;
        bottom: 25px;
        margin: 0
    }

    .slider .indicators .indicator-item {
        display: inline-block;
        position: relative;
        cursor: pointer;
        height: 16px;
        width: 16px;
        margin: 0 12px;
        background-color: #e0e0e0;
        transition: background-color .3s;
        border-radius: 50%
    }

    .slider .indicators .indicator-item.active {
        background-color: #4CAF50
    }

    .left-align {
        text-align: left;
        top:800px;
    }

    .right-align {
        text-align: right;
    }
    .center-align {
        text-align: center;
    }
    .a3w{
        color:white;
        font-size: 50px;
    }
      .a5w{
          color:white;
          font-size: 30px;
      }

  </style>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">--%>
<%--    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
    <script>
        $(function(){
            // $('.header_area').css("opacity","0");
            $('.header_area').css("box-shadow","none");
            $('.nav-link,.button.button-header').css("color","white");
            $('.nav-link.black').css("color","black");
            $('.nav-shop li i, .nav-shop li span').css("color","white");
            $(".navbar-brand.logo_h img").attr('src','/img/logo_white.png');
            $(window).scroll(function() {

                var scroll = $(window).scrollTop();
                //console.log(scroll);

                if (scroll >= 50) {
                    // $('.header_area').css("box-shadow","none");
                    $('.nav-link,.button.button-header').css("color","black");
                    $('.nav-shop li i, .nav-shop li span').css("color","black");
                    $(".navbar-brand.logo_h img").attr('src','/img/logo.png');
                } else {
                    $('.nav-link,.button.button-header').css("color","white");
                    $('.nav-shop li i, .nav-shop li span').css("color","white");
                    $('.nav-link.black').css("color","black");
                    $(".navbar-brand.logo_h img").attr('src','/img/logo_white.png');
                    // $(".header_area").removeClass("change");
                    // $('.header_area').animate({opacity:"0"}, 1000);
                }
            });
           $(".hero-carousel__slide").click(function(){
               location.href=$(this).children().next().attr('href');
           });

            //상품 디테일 버튼
            $(document).on('click','#BookDetail',function(){
                var id = $("i a",this).text();
                location.href="/BookDetail?id="+id;
            });
            $(document).on('click','.card-img, #card-img-temp',function(){
                var id = $(this).next().children().children().children().text();
                location.href="/BookDetail?id="+id;
            });

            $('.slider').slider({full_width: true});
            $('.slider').css("height","670px");
            $('.slides').css("height","670px");

        });

    </script>

</head>
<body>
  <!--================ Start Header Menu Area =================-->

	<!--================ End Header Menu Area =================-->

  <main class="site-main">
          <!--================ Hero banner start =================-->

    <section class="hero-banner" style="position: relative;top: -97px;">

<%--        <img class="img-fluid" src="img/home/hero-banner3.jpg" alt="">--%>
    <div class="slider">
        <ul class="slides">
            <li>
                <img src="img/home/dd.jpg"> <!-- random image -->
                <div class="caption middle center-align">
                    <h3 class="a3w">감성이 일상이 되는 공간</h3>
                    <h5 class="light white-text text-lighten-3 a5w">저희 '토크북'은 시와 에세이를 통해 세상을 다양하게 이야기 하고 있습니다.</h5>
                </div>
            </li>
            <li>
                <img src="img/home/bb.jpg"> <!-- random image -->
                <div class="caption middle left-align">
                    <h3 class="a3w">About TalkBook</h3>
                    <h5 class="light grey-text text-lighten-3 a5w">We talk about the world in a variety of poems and essays.</h5>
                </div>
            </li>
            <li>
                <img src="img/home/cc.jpg"> <!-- random image -->
                <div class="caption bottom right-align">
                    <h3 class="a3w"></h3>
                    <h5 class="light grey-text text-lighten-3 a5w">“한 편의 좋은 시를 읽는다는 것은, 영혼의  <br> 항아리 속에 향기로운 꽃을 꽂아두는 것과 같다."<br>-이어령</h5>
                </div>
            </li>
            <li>
                <img src="img/home/aa.jpg"> <!-- random image -->
                <div class="caption bottom left-align">
                    <h5 class="light grey-text text-lighten-3 a5w">“시는 우주의 생명적 본질이 인간의 감성적 <br> 작용을 통하여 표현되는 언어의 통일적 구상이다.”<br>-조지훈</h5>
                    <h3 class="a3w"></h3>
                </div>
            </li>
        </ul>
    </div>
    </section>
    <!--================ Hero banner start =================-->
          <!--================ Hero Carousel start =================-->
    <section class="section-margin mt-0" style="top: -107px;position: relative;">
<%--        <div class="container">--%>
<%--        <div class="section-intro pb-60px">--%>
<%--            <p>Theme Item in the market</p>--%>
<%--            <h2>Special <span class="section-intro__style">Category</span></h2>--%>
<%--        </div>--%>
<%--        </div>--%>
        <div class="owl-carousel owl-theme hero-carousel">

            <div class="hero-carousel__slide">
                <img src="img/home/night.jpg" alt="" class="img-fluid">
                <a href="/ThemeCatg?type=3" class="hero-carousel__slideOverlay">
                    <h3 style="font-size: 21px;">감성터지는 늦은 밤, 생각에 잠기기 좋은 책</h3>
                </a>
            </div>
            <div class="hero-carousel__slide">
                <img src="img/home/coffee.jpg" alt="" class="img-fluid">
                <a href="/ThemeCatg?type=4" class="hero-carousel__slideOverlay">
                    <h3>잔잔한 오후, 카페에서 읽기 좋은 책</h3>
                </a>
            </div>
            <div class="hero-carousel__slide">
                <img src="img/home/Happiness.jpg" alt="" class="img-fluid">
                <a href="/ThemeCatg?type=5" class="hero-carousel__slideOverlay">
                    <h3>힘들고 마음이 울적할때 읽기 좋은 책</h3>
                </a>
            </div>
        </div>
    </section>
    <!--================ Hero Carousel end =================-->

    <!-- ================ trending product section start ================= -->
    <section class="section-margin calc-60px">
      <div class="container">
        <div class="section-intro pb-60px">
          <p>Popular Item in the market</p>
          <h2>Trending <span class="section-intro__style">Product</span></h2>
        </div>
            <div class="row">
          <c:forEach var="item" items="${booklist2}" varStatus="status">
          <div class="col-md-6 col-lg-4 col-xl-3">
            <div class="card text-center card-product">
              <div class="card-product__img">
                <img class="card-img" src="${item.bookImg}" alt="" style="width: 50%">
                <ul class="card-product__imgOverlay">
                  <li><button id="BookDetail"><i class="ti-search"><a hidden>${item.bookIdx}</a></i></button></li>
                  <li><button><i class="ti-shopping-cart"></i></button></li>
                  <li><button><i class="ti-heart"></i></button></li>
                </ul>
              </div>
              <div class="card-body">
                <p>${item.category.catgName}</p>
                <h4 class="card-product__title"><a href="#">${item.bookTitle}</a></h4>
                <p class="card-product__price"><f:formatNumber type='currency' currencySymbol="￦" value="${item.bookPrice}"/></p>
              </div>
            </div>
          </div>
          </c:forEach>


        </div>
      </div>
    </section>
    <!-- ================ trending product section end ================= -->  


    <!-- ================ offer section start ================= --> 
    <section class="offer" id="parallax-1" data-anchor-target="#parallax-1" data-300-top="background-position: 20px 30px" data-top-bottom="background-position: 0 20px">
      <div class="container">
        <div class="row">
          <div class="col-xl-5">
            <div class="offer__content text-center">
              <h3 style="font-family: 'Noto Sans KR', sans-serif;">하상욱 시집 모음</h3>
              <h4 style="font-family: 'Noto Sans KR', sans-serif;"></h4>
              <p style="font-family: 'Noto Sans KR', sans-serif;">사람들의, 세상의 고정관념을 깨뜨리며
                  공감 또 공감하게 되는 시</p>
              <a class="button button--active mt-3 mt-xl-4" href="/ThemeCatg?type=6">Shop Now</a>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- ================ offer section end ================= --> 

    <!-- ================ Best Selling item  carousel ================= --> 
    <section class="section-margin calc-60px">
      <div class="container">
        <div class="section-intro pb-60px">
          <p>Popular Item in the market</p>
          <h2>Best <span class="section-intro__style">Sellers</span></h2>
        </div>
        <div class="owl-carousel owl-theme" id="bestSellerCarousel">

          <c:forEach var="item" items="${booklist}" varStatus="status">
          <div class="card text-center card-product">
            <div class="card-product__img">
              <img class="img-fluid" id="card-img-temp" src="${item.bookImg}" alt="" style="width: 50%;margin-left: 65px;">
              <ul class="card-product__imgOverlay">
                <li><button id="BookDetail"><i class="ti-search"><a hidden>${item.bookIdx}</a></i></button></li>
                <li><button><i class="ti-shopping-cart"></i></button></li>
                <li><button><i class="ti-heart"></i></button></li>
              </ul>
            </div>
            <div class="card-body">
              <p>${item.category.catgName}</p>
              <h4 class="card-product__title"><a href="#">${item.bookTitle}</a></h4>
              <p class="card-product__price"><f:formatNumber type='currency' currencySymbol="￦" value="${item.bookPrice}"/></p>
            </div>
          </div>
          </c:forEach>

        </div>
      </div>
    </section>
    <!-- ================ Best Selling item  carousel end ================= -->

    <!-- ================ Blog section start ================= -->
    <section class="blog">
        <div class="container">
            <div class="section-intro pb-60px">
                <p>Popular Item in the market</p>
                <h2>Latest <span class="section-intro__style">News</span></h2>
            </div>

            <div class="row">
                <div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
                    <div class="card card-blog">
                        <div class="card-blog__img">
                            <img class="card-img rounded-0" src="img/blog/blog1.png" alt="">
                        </div>
                        <div class="card-body">
                            <ul class="card-blog__info">
                                <li><a href="#">By Admin</a></li>
                                <li><a href="#"><i class="ti-comments-smiley"></i> 2 Comments</a></li>
                            </ul>
                            <h4 class="card-blog__title"><a href="single-blog.html">The Richland Center Shooping News
                                and weekly shooper</a></h4>
                            <p>Let one fifth i bring fly to divided face for bearing divide unto seed. Winged divided
                                light Forth.</p>
                            <a class="card-blog__link" href="#">Read More <i class="ti-arrow-right"></i></a>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
                    <div class="card card-blog">
                        <div class="card-blog__img">
                            <img class="card-img rounded-0" src="img/blog/blog2.png" alt="">
                        </div>
                        <div class="card-body">
                            <ul class="card-blog__info">
                                <li><a href="#">By Admin</a></li>
                                <li><a href="#"><i class="ti-comments-smiley"></i> 2 Comments</a></li>
                            </ul>
                            <h4 class="card-blog__title"><a href="single-blog.html">The Shopping News also offers
                                top-quality printing services</a></h4>
                            <p>Let one fifth i bring fly to divided face for bearing divide unto seed. Winged divided
                                light Forth.</p>
                            <a class="card-blog__link" href="#">Read More <i class="ti-arrow-right"></i></a>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
                    <div class="card card-blog">
                        <div class="card-blog__img">
                            <img class="card-img rounded-0" src="img/blog/blog3.png" alt="">
                        </div>
                        <div class="card-body">
                            <ul class="card-blog__info">
                                <li><a href="#">By Admin</a></li>
                                <li><a href="#"><i class="ti-comments-smiley"></i> 2 Comments</a></li>
                            </ul>
                            <h4 class="card-blog__title"><a href="single-blog.html">Professional design staff and
                                efficient equipment you’ll find we offer</a></h4>
                            <p>Let one fifth i bring fly to divided face for bearing divide unto seed. Winged divided
                                light Forth.</p>
                            <a class="card-blog__link" href="#">Read More <i class="ti-arrow-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ================ Blog section end ================= -->

    <!-- ================ Subscribe section start ================= -->
    <section class="subscribe-position">
        <div class="container">
            <div class="subscribe text-center">
                <h3 class="subscribe__title">Get Update From Anywhere</h3>
                <p>Bearing Void gathering light light his eavening unto dont afraid</p>
                <div id="mc_embed_signup">
                    <form target="_blank"
                          action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
                          method="get" class="subscribe-form form-inline mt-5 pt-1">
                        <div class="form-group ml-sm-auto">
                            <input class="form-control mb-1" type="email" name="EMAIL" placeholder="Enter your email"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = 'Your Email Address '">
                            <div class="info"></div>
                        </div>
                        <button class="button button-subscribe mr-auto mb-1" type="submit">Subscribe Now</button>
                        <div style="position: absolute; left: -5000px;">
                            <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value="" type="text">
                        </div>

                    </form>
                </div>

            </div>
        </div>
    </section>
    <!-- ================ Subscribe section end ================= -->


</main>


<!--================ Start footer Area  =================-->
<!--================ End footer Area  =================-->

</body>
</html>