<%@ page import="javax.naming.Context" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Aroma Shop - Category</title>
  <style>
    @import url('https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap&subset=korean');
    .card-product__title a {
      color: #222;
      font-size: initial;
      font-family: 'Noto Sans KR', sans-serif;
    }

        .pixel-radio:checked::after {
            -webkit-animation: click-wave 0.65s;
            -moz-animation: click-wave 0.65s;
            animation: click-wave 0.65s;
            background: #384aeb;
            content: '';
            display: block;
            width: 15px;
            height: 15px;
            border-radius: 50%;
            position: relative;
            z-index: 2;
            opacity: 0;
        }

        @import url("https://fonts.googleapis.com/css?family=Roboto:400,300");
        .pagination-container {
            color: #2c3e50;
            font-family: 'Roboto', sans-serif;
            font-weight: 400;
            margin: 15px auto;
            padding-right: 40px;
            text-align: center;

    }
    .pagination {
      position: relative;
    }
    .pagination a {
      position: relative;
      display: inline-block;
      color: #2c3e50;
      text-decoration: none;
      font-size: 1.2rem;
      padding: 8px 16px 10px;
    }
    .pagination a:before {
      z-index: -1;
      position: absolute;
      height: 100%;
      width: 100%;
      content: "";
      top: 0;
      left: 0;
      background-color: #2c3e50;
      border-radius: 24px;
      -webkit-transform: scale(0);
      transform: scale(0);
      transition: all 0.2s;
    }
    .pagination a:hover,
    .pagination a .pagination-active {
      color: #fff;
    }
    .pagination a:hover:before,
    .pagination a .pagination-active:before {
      -webkit-transform: scale(1);
      transform: scale(1);
    }
    .pagination .pagination-active {
      color: #fff;
    }
    .pagination .pagination-active:before {
      -webkit-transform: scale(1);
      transform: scale(1);
    }
    .pagination-newer {
      margin-right: 50px;
    }
    .pagination-older {
      margin-left: 50px;
    }
  </style>
  <script>
    $(function(){

            var startPage =${startPage};
            var maxPage =${maxPage};
            var curPage;
            var pageInfo;

            // alert($('#sorting select option:selected').val());
            <%--alert($("#category li input[id=${CatgIdx}]").attr('id'));--%>

      //돈 콤마 찍기
      function addComma(num) {
        var regexp = /\B(?=(\d{3})+(?!\d))/g;
        return num.toString().replace(regexp, ',');
      }

      //상품 디테일 버튼
      $(document).on('click','#BookDetail',function(){
        var id = $("i a",this).text();
        location.href="/BookDetail?id="+id;
      });
      $(document).on('click','.card-img',function(){
        var id = $(this).next().children().children().children().text();
        location.href="/BookDetail?id="+id;
      });

      function MoveScreen(){
        // var offset = $("#catgSection").offset();
        // $('html, body').animate({scrollTop : offset.top}, 400);
        // window.scrollBy(0,500);

        // var Height = document.querySelector("#catgSection").offsetHeight;
        // console.log(Height);

        var location = document.querySelector("#catgSection").offsetTop;
        var Height = 330;
        window.scrollTo({top:location + Height, behavior:'smooth'});
      }
    });
  </script>
</head>
<body>

	<!-- ================ start banner area ================= -->	
	<section class="blog-banner-area" id="category2">
		<div class="container h-100"  >
			<div class="blog-banner">
				<div class="text-center">
					<h1>${Title}</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active" id="catgSection" aria-current="page">Shop Category</li>
            </ol>
          </nav>
				</div>
			</div>
    </div>
	</section>
	<!-- ================ end banner area ================= -->


	<!-- ================ category section start ================= -->
  <section class="section-margin--small mb-5">
    <div class="container">
      <div class="row">
        <div class="col-xl-9 col-lg-8 col-md-7" style="left: 150px;">
          <!-- Start Filter Bar -->
          <div class="filter-bar d-flex flex-wrap align-items-center">
          </div>
          <!-- End Filter Bar -->
          <!-- Start Best Seller -->
          <section class="lattest-product-area pb-40 category-list">
            <div class="row" id="bookCard">
                <c:forEach var="item" items="${booklist}" varStatus="status">
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="${item.bookImg}"  alt="">
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

          </section>
          <!-- End Best Seller -->
        </div>
      </div>
    </div>

  </section>
	<!-- ================ category section end ================= -->


	<!-- ================ top product area start ================= -->
	<section class="related-product-area">
		<div class="container">
			<div class="section-intro pb-60px">
        <p>Popular Item in the market</p>
        <h2>Top <span class="section-intro__style">Product</span></h2>
      </div>
			<div class="row mt-30">
        <div class="col-sm-6 col-xl-3 mb-4 mb-xl-0">
          <div class="single-search-product-wrapper">
            <div class="single-search-product d-flex">
              <a href="#"><img src="/img/product/product-sm-1.png" alt=""></a>
              <div class="desc">
                  <a href="#" class="title">Gray Coffee Cup</a>
                  <div class="price">$170.00</div>
              </div>
            </div>
            <div class="single-search-product d-flex">
              <a href="#"><img src="/img/product/product-sm-2.png" alt=""></a>
              <div class="desc">
                <a href="#" class="title">Gray Coffee Cup</a>
                <div class="price">$170.00</div>
              </div>
            </div>
            <div class="single-search-product d-flex">
              <a href="#"><img src="/img/product/product-sm-3.png" alt=""></a>
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
              <a href="#"><img src="/img/product/product-sm-4.png" alt=""></a>
              <div class="desc">
                  <a href="#" class="title">Gray Coffee Cup</a>
                  <div class="price">$170.00</div>
              </div>
            </div>
            <div class="single-search-product d-flex">
              <a href="#"><img src="/img/product/product-sm-5.png" alt=""></a>
              <div class="desc">
                <a href="#" class="title">Gray Coffee Cup</a>
                <div class="price">$170.00</div>
              </div>
            </div>
            <div class="single-search-product d-flex">
              <a href="#"><img src="/img/product/product-sm-6.png" alt=""></a>
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
              <a href="#"><img src="/img/product/product-sm-7.png" alt=""></a>
              <div class="desc">
                  <a href="#" class="title">Gray Coffee Cup</a>
                  <div class="price">$170.00</div>
              </div>
            </div>
            <div class="single-search-product d-flex">
              <a href="#"><img src="/img/product/product-sm-8.png" alt=""></a>
              <div class="desc">
                <a href="#" class="title">Gray Coffee Cup</a>
                <div class="price">$170.00</div>
              </div>
            </div>
            <div class="single-search-product d-flex">
              <a href="#"><img src="/img/product/product-sm-9.png" alt=""></a>
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
              <a href="#"><img src="/img/product/product-sm-1.png" alt=""></a>
              <div class="desc">
                  <a href="#" class="title">Gray Coffee Cup</a>
                  <div class="price">$170.00</div>
              </div>
            </div>
            <div class="single-search-product d-flex">
              <a href="#"><img src="/img/product/product-sm-2.png" alt=""></a>
              <div class="desc">
                <a href="#" class="title">Gray Coffee Cup</a>
                <div class="price">$170.00</div>
              </div>
            </div>
            <div class="single-search-product d-flex">
              <a href="#"><img src="/img/product/product-sm-3.png" alt=""></a>
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
	<!-- ================ top product area end ================= -->

	<!-- ================ Subscribe section start ================= -->
  <section class="subscribe-position">
    <div class="container">
      <div class="subscribe text-center">
        <h3 class="subscribe__title">Get Update From Anywhere</h3>
        <p>Bearing Void gathering light light his eavening unto dont afraid</p>
        <div id="mc_embed_signup">
          <form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01" method="get" class="subscribe-form form-inline mt-5 pt-1">
            <div class="form-group ml-sm-auto">
              <input class="form-control mb-1" type="email" name="EMAIL" placeholder="Enter your email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Your Email Address '" >
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




</body>
</html>