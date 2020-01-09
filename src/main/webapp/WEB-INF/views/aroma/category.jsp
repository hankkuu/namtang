<%@ page import="javax.naming.Context" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Aroma Shop - Category</title>

  <style>

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
    $('.pagination-inner a').on('click', function() {
      $(this).siblings().removeClass('pagination-active');
      $(this).addClass('pagination-active');
    });

    $("#category li input, ".pagination-active).click(function(){

      $(".pagination-active").removeClass();
      $(".pagination-inner a:first").addClass('pagination-active');
      var CatgIdx = $(this).attr('id');
      var PageNum = $(".pagination-active").text();
      $.ajax({
        url : "/catgCall", //서버요청주소
        type : "get",//요청방식 (get,post,patch,delete,put)
        dataType : "json",//서버가 보내온 데이터 타입 (text, html, xml, json)
        data : "CatgIdx="+CatgIdx+"&PageNum="+PageNum,//서버에게 보내는 parameter정보
        success : function(result){
          // console.log(result);
          $("#bookCard").empty();

          $.each(result, function(index, item){
            var Img = item.bookImg;
            var Title = item.bookTitle;
            var Price = item.bookPrice;
            var CatgName = item.category.catgName;

            var str = " <div class=\"col-md-6 col-lg-4\">\n" +
                    "                <div class=\"card text-center card-product\">\n" +
                    "                  <div class=\"card-product__img\">\n" +
                    "                    <img class=\"card-img\" src=\""+Img+"\" alt=\"\">\n" +
                    "                    <ul class=\"card-product__imgOverlay\">\n" +
                    "                      <li><button><i class=\"ti-search\"></i></button></li>\n" +
                    "                      <li><button><i class=\"ti-shopping-cart\"></i></button></li>\n" +
                    "                      <li><button><i class=\"ti-heart\"></i></button></li>\n" +
                    "                    </ul>\n" +
                    "                  </div>\n" +
                    "                  <div class=\"card-body\">\n" +
                    "                    <p>"+CatgName+"</p>\n" +
                    "                    <h4 class=\"card-product__title\"><a href=\"#\">"+Title+"</a></h4>\n" +
                    "                    <p class=\"card-product__price\">"+Price+"</p>\n" +
                    "                  </div>\n" +
                    "                </div>\n" +
                    "              </div>"

            $('#bookCard').append(str);
          });


        } , //성공했을때
        error : function(err){

        }// 실패했을때
      });
    });

    });
  </script>
</head>
<body>

	<!-- ================ start banner area ================= -->	
	<section class="blog-banner-area" id="category">
		<div class="container h-100">
			<div class="blog-banner">
				<div class="text-center">
					<h1>Shop Category</h1>
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


	<!-- ================ category section start ================= -->		  
  <section class="section-margin--small mb-5">
    <div class="container">
      <div class="row">
        <div class="col-xl-3 col-lg-4 col-md-5">
          <div class="sidebar-categories">
            <div class="head">Browse Categories</div>
            <ul class="main-categories">
              <li class="common-filter">
                <form action="#">
                  <ul id="category">
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="0" name="catg"><label for="0">전체보기<span> (${list[10]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="1" name="catg"><label for="1">한국시<span> (${list[0]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="2" name="catg"><label for="2">외국시<span> (${list[1]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="3" name="catg"><label for="3">인물 에세이<span> (${list[2]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="4" name="catg"><label for="4">여행 에세이<span> (${list[3]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="5" name="catg"><label for="5">성공 에세이<span> (${list[4]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="6" name="catg"><label for="6">독서 에세이<span> (${list[5]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="7" name="catg"><label for="7">명상 에세이<span> (${list[6]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="8" name="catg"><label for="8">그림/포토 에세이<span> (${list[7]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="9" name="catg"><label for="9">연애/사랑 에세이<span> (${list[8]})</span></label></li>
                    <li class="filter-list"><input class="pixel-radio" type="radio" id="10" name="catg"><label for="10">삶의 지혜/명언<span> (${list[9]})</span></label></li>
                  </ul>
                </form>
              </li>
            </ul>
          </div>
          <div class="sidebar-filter">
            <div class="top-filter-head">Product Filters</div>
            <div class="common-filter">
              <div id="slider">슬라이더 들어갈 곳</div>
              <!--<div class="head">Price</div>
              <div class="price-range-area">
                <div id="price-range"></div>
                <div class="value-wrapper d-flex">
                  <div class="price">Price:</div>
                  <span>$</span>
                  <div id="lower-value"></div>
                  <div class="to">to</div>
                  <span>$</span>
                  <div id="upper-value"></div>
                </div>
              </div>-->
            </div>
          </div>
        </div>
        <div class="col-xl-9 col-lg-8 col-md-7">
          <!-- Start Filter Bar -->
          <div class="filter-bar d-flex flex-wrap align-items-center">
            <div class="sorting mr-auto">
              <select>
                <option value="1">가나다 순</option>
                <option value="1">가나다 역순</option>
                <option value="1">출간일 순</option>
              </select>
            </div>
            <div class="sorting" style="margin-right: 5px;">
              <select>
                <option value="1">제목별</option>
                <option value="1">저자별</option>
                <option value="1">출판사별</option>
              </select>
            </div>
            <div>
              <div class="input-group filter-bar-search">
                <input type="text" placeholder="Search">
                <div class="input-group-append">
                  <button type="button"><i class="ti-search"></i></button>
                </div>
              </div>
            </div>
          </div>
          <!-- End Filter Bar -->
          <!-- Start Best Seller -->
          <section class="lattest-product-area pb-40 category-list">
            <div class="row" id="bookCard">
                <c:forEach var="item" items="${booklist}" varStatus="status">
<%--            <c:forEach var="i" begin="0" end="8">--%>
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="${item.bookImg}" alt="">
                    <ul class="card-product__imgOverlay">
                      <li><button><i class="ti-search"></i></button></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                      <li><button><i class="ti-heart"></i></button></li>
                    </ul>
                  </div>
                  <div class="card-body">
                    <p>${item.category.catgName}</p>
                    <h4 class="card-product__title"><a href="#">${item.bookTitle}</a></h4>
                    <p class="card-product__price">${item.bookPrice}</p>
                  </div>
                </div>
              </div>
            </c:forEach>

<%--
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="/img/product/product2.png" alt="">
                    <ul class="card-product__imgOverlay">
                      <li><button><i class="ti-search"></i></button></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                      <li><button><i class="ti-heart"></i></button></li>
                    </ul>
                  </div>
                  <div class="card-body">
                    <p>Beauty</p>
                    <h4 class="card-product__title"><a href="#">Women Freshwash</a></h4>
                    <p class="card-product__price">$150.00</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="/img/product/product3.png" alt="">
                    <ul class="card-product__imgOverlay">
                      <li><button><i class="ti-search"></i></button></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                      <li><button><i class="ti-heart"></i></button></li>
                    </ul>
                  </div>
                  <div class="card-body">
                    <p>Decor</p>
                    <h4 class="card-product__title"><a href="#">Room Flash Light</a></h4>
                    <p class="card-product__price">$150.00</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="/img/product/product4.png" alt="">
                    <ul class="card-product__imgOverlay">
                      <li><button><i class="ti-search"></i></button></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                      <li><button><i class="ti-heart"></i></button></li>
                    </ul>
                  </div>
                  <div class="card-body">
                    <p>Decor</p>
                    <h4 class="card-product__title"><a href="#">Room Flash Light</a></h4>
                    <p class="card-product__price">$150.00</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="/img/product/product5.png" alt="">
                    <ul class="card-product__imgOverlay">
                      <li><button><i class="ti-search"></i></button></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                      <li><button><i class="ti-heart"></i></button></li>
                    </ul>
                  </div>
                  <div class="card-body">
                    <p>Accessories</p>
                    <h4 class="card-product__title"><a href="#">Man Office Bag</a></h4>
                    <p class="card-product__price">$150.00</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="/img/product/product6.png" alt="">
                    <ul class="card-product__imgOverlay">
                      <li><button><i class="ti-search"></i></button></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                      <li><button><i class="ti-heart"></i></button></li>
                    </ul>
                  </div>
                  <div class="card-body">
                    <p>Kids Toy</p>
                    <h4 class="card-product__title"><a href="#">Charging Car</a></h4>
                    <p class="card-product__price">$150.00</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="/img/product/product7.png" alt="">
                    <ul class="card-product__imgOverlay">
                      <li><button><i class="ti-search"></i></button></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                      <li><button><i class="ti-heart"></i></button></li>
                    </ul>
                  </div>
                  <div class="card-body">
                    <p>Accessories</p>
                    <h4 class="card-product__title"><a href="#">Blutooth Speaker</a></h4>
                    <p class="card-product__price">$150.00</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="/img/product/product8.png" alt="">
                    <ul class="card-product__imgOverlay">
                      <li><button><i class="ti-search"></i></button></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                      <li><button><i class="ti-heart"></i></button></li>
                    </ul>
                  </div>
                  <div class="card-body">
                    <p>Kids Toy</p>
                    <h4 class="card-product__title"><a href="#">Charging Car</a></h4>
                    <p class="card-product__price">$150.00</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6 col-lg-4">
                <div class="card text-center card-product">
                  <div class="card-product__img">
                    <img class="card-img" src="/img/product/product1.png" alt="">
                    <ul class="card-product__imgOverlay">
                      <li><button><i class="ti-search"></i></button></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                      <li><button><i class="ti-heart"></i></button></li>
                    </ul>
                  </div>
                  <div class="card-body">
                    <p>Accessories</p>
                    <h4 class="card-product__title"><a href="#">Quartz Belt Watch</a></h4>
                    <p class="card-product__price">$150.00</p>
                  </div>
                </div>
              </div>--%>



            </div>
            <nav class="pagination-container">
            <div class="pagination">
              <a class="pagination-newer" href="">PREV</a>
              <span class="pagination-inner">
					<a class="pagination-active" href="#ㅇㅅㅇ">1</a>
					<a href="#ㅇㅅㅇ">2</a>
					<a href="#ㅇㅅㅇ">3</a>
					<a href="#ㅇㅅㅇ">4</a>
					<a href="#ㅇㅅㅇ">5</a>
					<a href="#ㅇㅅㅇ">6</a>
				</span>
              <a class="pagination-older" href="#ㅇㅅㅇ">NEXT</a>
            </div>
          </nav>
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