<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Aroma Shop - Login</title>
	<script type="text/javascript" src="/js/json.js"></script>
	<script type="text/javascript">

		$(window).on('load', function () {

			$("#findId").click(async () => {
				//let email = $("#contactForm").serializeObject();

				await purchaseProcess().then(async (result) => {

					if (result.statusCode === "Success") {
						let email = {
							name : "test",
							subject: "findId",
							sendTo: $("#email").val(),
							content: result.message
						}

						console.log(email);
						$.ajax({
							type: "post",
							url: "http://localhost:8889/api/v1/mail",
							dataType: "json",
							data: JSON.stringify(email),
							contentType: 'application/json; charset=utf-8',
							success: function (result) {
								if(result.statusCode === "Success") {
									alert("메일이 전송되었습니다");
								} else {
									alert("메일 발송에 실패했습니다");
								}

							},
							error: function (error) {
								console.log(error);
								alert("b오류 발생");
							}
						});//ajax끝
					}

				});
			});
		});

		const purchaseProcess = async () => {
			let email = $("#email").val();
			let user = {email: email}
			const result = await $.ajax({


				type: "post",
				url: "/api/v1/account/findId",
				dataType: "json",
				data: JSON.stringify(user),
				contentType: 'application/json; charset=utf-8',
				success: function (result) {
					console.log(result);
					if (result.statusCode === "Success") {
						//alert("구매시작");
					} else {
						alert("입력이 잘못되었습니다");
					}
				},
				error: function (error) {
					console.log(error);
					alert("오류 발생");
				}
			});//ajax끝
			return result;
		}
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
						<h3>아이디 찾기</h3>
						<form class="row login_form" id="findIdForm" >
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="email" name="email" placeholder="email" onfocus="this.placeholder = ''" onblur="this.placeholder = '이메일'">
							</div>
<%--							<div class="col-md-12 form-group">--%>
<%--								<input type="text" class="form-control" id="userPhone" name="phone" placeholder="전화번호" onfocus="this.placeholder = ''" onblur="this.placeholder = '전화번호'">--%>
<%--							</div>--%>
							<div class="col-md-12 form-group">
								<button type="button" id="findId" value="submit" class="button button-login w-100">아이디 찾기</button>
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