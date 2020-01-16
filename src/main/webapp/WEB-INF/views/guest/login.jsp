<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Aroma Shop - Login</title>
    <script type="text/javascript" src="/js/json.js"></script>
    <script type="text/javascript">
        $(function () {

            // $("#login").click(function(){
            // 	let user = $("#contactForm").serializeObject();
            //
            // 	console.log(user);
            // 	$.ajax({
            // 		type : "post" ,
            // 		url : "/api/v1/account/login",
            // 		dataType : "json",
            // 		data : JSON.stringify(user),
            // 		contentType: 'application/json; charset=utf-8',
            // 		success : function(result) {
            // 			console.log(result.statusCode);
            // 			if(result.statusCode === "Success") {
            // 				location.replace('/');
            // 			} else {
            // 				alert("로그인 실패");
            // 			}
            // 		},
            // 		error : function(error) {
            // 			console.log(error);
            // 			alert("오류 발생");
            // 		}
            // 	});//ajax끝
            // });

        });

    </script>
</head>
<body>
<!--================ Start Header Menu Area =================-->

<!--================ End Header Menu Area =================-->

<!-- ================ start banner area ================= -->
<section class="blog-banner-area" id="category">
    <div class="container h-100">
        <div class="blog-banner">
            <div class="text-center">
                <h1>Login / Register</h1>
                <nav aria-label="breadcrumb" class="banner-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Login/Register</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</section>
<!-- ================ end banner area ================= -->
<!--================Login Box Area =================-->
<section class="login_box_area section-margin">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="login_box_img">
                    <div class="hover">
                        <h4>계정이 없으신가요?</h4>
                        <p>계정이 없으시면 아래 회원가입 버튼을 클릭 해주세요.</p>
                        <a class="button button-account" href="register">회원가입</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="login_form_inner">
                    <h3>로그인</h3>
                    <form class="row login_form" method="post"action="/"> <%--action="#/" id="contactForm" >--%>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="username" name="username" placeholder="이메일"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = '이메일'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="password" name="password" placeholder="비밀번호"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호'">
                        </div>
                        <div class="col-md-12-form-group">
                            <div class="create_account">
                                <input type="checkbox" id="remember-me" name="remember-me">
                                <label for="remember-me">Keep me logged in</label>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="col-md-12 form-group">
                                <button type="submit" value="submit" id="login" class="button button-login w-100">로그인
                                </button>
                                <a href="findPassword">비밀번호 찾기</a>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Login Box Area =================-->

</body>
</html>