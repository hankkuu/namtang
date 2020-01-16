<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Aroma Shop - Login</title>
    <style type="text/css">

        .login_box_area .login_box_img:before {
            height: 731.6px;
        }

        .login_form div:nth-child(5) div {
            border-bottom: 1px solid #cccccc;
        }

        .login_form .sex-div {
            float: left;
            width: 50%;
        }
    </style>

    <script type="text/javascript" src="/js/json.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#register").click(function () {
                let user = $("#register_form").serializeObject();

                console.log(user);
                $.ajax({
                    type: "post",
                    url: "/api/v1/account",
                    dataType: "json",
                    data: JSON.stringify(user),
                    contentType: 'application/json; charset=utf-8',
                    success: function (result) {
                        console.log(result.statusCode);
                        if (result.statusCode === "Success") {
                            location.replace('/login');
                        } else {
                            alert("회원가입이 잘못되었습니다");
                        }
                    },
                    error: function (error) {
                        console.log(error);
                        alert("오류 발생");
                    }
                });//ajax끝
            })
        });

    </script>

</head>
<body>

<!-- ================ start banner area ================= -->
<%--	<section class="blog-banner-area" id="category">--%>
<%--		<div class="container h-100">--%>
<%--			<div class="blog-banner">--%>
<%--				<div class="text-center">--%>
<%--					<h1>Register</h1>--%>
<%--					<nav aria-label="breadcrumb" class="banner-breadcrumb">--%>
<%--            <ol class="breadcrumb">--%>
<%--              <li class="breadcrumb-item"><a href="#">Home</a></li>--%>
<%--              <li class="breadcrumb-item active" aria-current="page">Register</li>--%>
<%--            </ol>--%>
<%--          </nav>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--    </div>--%>
<%--	</section>--%>
<!-- ================ end banner area ================= -->

<!--================Login Box Area =================-->
<section class="login_box_area section-margin">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="login_box_img">
                    <div class="hover">
                        <h4>계정이 있으신가요?</h4>
                        <p>이미 회원가입이 되어 있으시면 로그인 버튼을 눌러주세요.</p>
                        <a class="button button-account" href="login">로그인</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="login_form_inner register_form_inner">
                    <h3>회원가입</h3>
                    <form class="row login_form" action="/#" id="register_form" method="post">
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="email" name="email" placeholder="이메일 주소"
                                   onblur="this.placeholder = '이메일 주소'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="password" name="password" placeholder="비밀번호"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="confirmPassword" name="confirmPassword"
                                   placeholder="비밀번호 확인" onfocus="this.placeholder = ''"
                                   onblur="this.placeholder = '비밀번호 확인'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="이름"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = '이름'">
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="sex-div">
                                <input type="radio" id="man" name="sex" class="input_radio" value="1">
                                <label>남자</label>
                            </div>
                            <div class="sex-div">
                                <input type="radio" id="woman" name="sex" class="input_radio" value="2">
                                <label>여자</label>
                            </div>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="age" name="age" placeholder="나이"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = '나이'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="phone" name="phone" placeholder="전화번호"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = '전화번호'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="address" name="address" placeholder="주소"
                                   onfocus="this.placeholder = ''" onblur="this.placeholder = '주소'">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="button" value="submit" id="register"
                                   class="button button-register w-100">회원가입</input>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Login Box Area =================-->

</body>
</html>