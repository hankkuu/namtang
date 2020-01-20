<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>]
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>string</title>
    <link rel="icon" href="/img/Fevicon.png" type="image/png">
    <link rel="stylesheet" href="/vendors/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="/vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="/vendors/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="/vendors/linericon/style.css">
    <link rel="stylesheet" href="/vendors/owl-carousel/owl.theme.default.min.css">
    <link rel="stylesheet" href="/vendors/owl-carousel/owl.carousel.min.css">
    <link rel="stylesheet" href="/vendors/nice-select/nice-select.css">
    <link rel="stylesheet" href="/vendors/nouislider/nouislider.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .nav-item2{
            position:   relative;
        }

        .togglesearch{
            border-radius: 10px;
            background: #fff;
            position: absolute;
            top: 35px;
            right: -18px;
            width: 255px;
            height: 45px;
            line-height: 60px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
            border-top:4px solid #fff;
            display: none;
        }

        .togglesearch:before{
            content: "";
            position: absolute;
            top: -32px;
            right: 13px;
            border-left: 12px solid transparent;
            border-right: 12px solid transparent;
            border-top: 14px solid transparent;
            border-bottom: 14px solid #fff;
        }

        .togglesearch input[type="text"]{
            width: 170px;
            height: 25px;
            margin-top:3px;
            /*padding: 5px 10px;*/
            margin-left: 10px;
            top : -12.5px;
            border: 1px solid #384AEB;
            outline: none;
            position: relative;
        }

        .togglesearch input[type="button"]{
            width: 55px;
            line-height: 15px;
            padding: 5px 0;
            position: relative;
            background: #384AEB;
            color: #fff;
            bottom: 14px;
            left:2px;
            border: 1px solid #fff;
            outline: none;
            cursor: pointer;
            font-size:12px;
        }


        @media all and (min-width:0px) and (max-width:990px){
            .togglesearch{
                left: -13px;
                top: 40px;
            }
            .togglesearch:before{
                right: 215px;
            }
        }
        /*@media all and (min-width:990px)and (max-width:1200px){*/
        /*    .togglesearch{*/
        /*        right: 35%;*/
        /*    }*/
        /*}*/
    </style>
<script>
    function searchCall(){
        document.searchForm.submit();
    }

    $(document).ready(function() {
        $(document).mouseup(function (e) {
            var container = $(".nav-item2");
            if (!$(".textbox, .textbtn, .togglesearch").is(e.target) && !container.is(e.target) && container.has(e.target).length === 0){
                $(".togglesearch").css('display','none');
                $(".textbox").val("");
            }

        });

        $(".nav-item2").click(function() {
            $(".togglesearch").toggle();
            $(".textbox").focus();
        });

    });
    function CatgPage(catg){
        var f=document.paging;
        f.catg.value=catg;
        f.action="/category";
        f.method="post";
        f.submit();
    }

</script>
</head>
<body style="padding-top: 0px;">
<!--================ Start Header Menu Area =================-->
<header class="header_area">
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container">
                <a class="navbar-brand logo_h" href="/"><img src="/img/logo.png" alt=""></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav mr-auto" style="margin-left: 65px;">
                        <li class="nav-item active"><a class="nav-link" href="/">Home</a></li>
                        <li class="nav-item submenu dropdown">
                            <%--                     <a href="aroma/mypage" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                                        aria-expanded="false">--%>
                            <a href="/mypage" class="nav-link dropdown-toggle">My Page</a>
                        </li>

                        <li class="nav-item submenu dropdown">
                            <%--                     <a href="aroma/mypage" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                                        aria-expanded="false">--%>
                            <form name="paging">
                                <input type="hidden" name="catg"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <a href="#" onclick='CatgPage(0)' class="nav-link dropdown-toggle">Category</a>
                            <ul class="dropdown-menu">
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(1)'>한국시</a></li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(2)'>외국시</a></li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(3)'>인물 에세이</a></li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(4)'>여행 에세이</a></li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(5)'>성공 에세이</a></li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(6)'>독서 에세이</a></li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(7)'>명상 에세이</a></li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(8)'>그림/포토 에세이</a>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(9)'>연애/사랑 에세이</a>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="#" onclick='CatgPage(10)'>삶의 지혜/명언</a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item submenu dropdown">
                            <a href="/Contact" class="nav-link dropdown-toggle">Contact Us</a>
                        </li>

                        <%--                        <li class="nav-item submenu dropdown">
                                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                                       aria-expanded="false">Cart</a>
                                                </li>--%>
                        <%-- <li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>--%>
                    </ul>

                    <ul class="nav-shop">


                        <li class="searchbar">
<%--                                <li class="nav-item"><button><i class="ti-search"></i></button></li>--%>
                        <li class="nav-item2"><button><i class="ti-search" aria-hidden="true">

                        <div class="togglesearch">
                            <form name="searchForm" id="searchForm" action="/SearchWord" method="post">
                                <script>

                                </script>
                                <input class="textbox" type="text" name="Word" placeholder=""/>
                                <input class="textbtn" type="button" value="Search" onclick="searchCall();"/>
                            </form>
                        </div>
                    </i></button></li>
<%--                            <i class="fa fa-search" aria-hidden="true"></i>--%>


                        </li>

                        <li class="nav-item"><a href="/user/cart"><button><i class="ti-shopping-cart"></i></button></a> </li>
  <security:authorize access="!isAuthenticated()">
<li class="nav-item"><a class="button button-header" href="/login">Login / Register</a></li>
</security:authorize>
<security:authorize access="isAuthenticated()">
<li class="nav-item"><a class="button button-header" href="/logout">Logout</a></li>
</security:authorize>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>
<!--================ End Header Menu Area =================-->

</body>
</html>


