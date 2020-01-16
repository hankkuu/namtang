<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Aroma Shop - Login</title>
 <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <style type="text/css">

		#textP{
  		font-size:10px; color:red; margin-top: -20px; margin-left:20px;
  	}
  	
  	.pw{
  		margin-top: -20px; margin-left:20px;
  	}
  	
  	#pw-success{
  		color:blue; font-size:10px;
  	}
  	
  	#pw-fail{
  		color:red; font-size:10px;
  	}
  
  	.login_box_area .login_box_img:before{
  		height:810px;
  	}
	
	.login_form .sex-div{
		float:left;
		width:50%;
		border-bottom: 1px solid #cccccc
		
	}
	</style>

	<script type="text/javascript" src="/js/json.js"></script>
	<script type="text/javascript" >
		$(function(){
			$("#register").click(function(){
				let user = $("#register_form").serializeObject();

				console.log(user);
				$.ajax({
					type : "post" ,
					url : "/api/v1/account",
					dataType : "json",
					data : JSON.stringify(user),
					contentType: 'application/json; charset=utf-8',
					success : function(result) {
						console.log(result.statusCode);
						if(result.statusCode === "Success") {
							location.replace('/login');
						} else {
							alert("회원가입이 잘못되었습니다");
						}
					},
					error : function(error) {
						console.log(error);
						alert("오류 발생");
					}
				});//ajax끝
			})
			
			/* 비밀번호 입력 */
	  		$("#userPassword").mouseenter(function(){
	  			$("#textP").text("숫자 또는 문자로만 4~12자리 입력하세요.")
	  		});
	  		$("#userPassword").mouseleave(function(){
	  			$("#textP").text(" ");
	  		});
	  		
	  		/* 비밀번호 일치여부*/
				$("#pw-success").hide();
					$("#pw-fail").hide();
					$("input").keyup(function() {
						var pwd1 = $("#userPassword").val();
						var pwd2 = $("#confirmPassword").val();
						if (pwd1 != "" || pwd2 != "") {
							if (pwd1 == pwd2) {
								$("#pw-success").show();
								$("#pw-fail").hide();
								$("#submit").removeAttr("disabled");
							} else {
								$("#pw-success").hide();
								$("#pw-fail").show();
								$("#submit").attr("disabled", "disabled");
							}
						}
						$("#userName").click(function(){
							$("#pw-success").hide();
						});
					});
			});
		
		/* 주소찾기 */
	  	function openDaumZipAddress() {
			new daum.Postcode({
				oncomplete:function(data) {
					$("#postcode1").val(data.postcode1);
					$("#postcode2").val(data.postcode2);
					$("#zonecode").val(data.zonecode);
					$("#address").val(data.address);
					$("#address_etc").focus();
					console.log(data);
				}
			}).open();
		}

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
							<h4>로그아웃 하시겠어요?</h4>
							<p>로그아웃을 하시려면 버튼을 눌러주세요.</p>
							<a class="button button-account" href="login">로그아웃</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="login_form_inner register_form_inner">
						<h3>개인정보 수정</h3>
						<form class="row login_form" action="#/" id="register_form" >
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="이메일 주소" onblur="this.placeholder = '이메일 주소'">
			                </div>
			                <div class="col-md-12 form-group">
								<input type="text" class="form-control" id="userPassword" name="userPassword" placeholder="비밀번호" onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호'">
			                </div>
			                <p id="textP"></p>
			                <div class="col-md-12 form-group">
								<input type="text" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인" onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호 확인'">
							</div>
							<div class="pw" id="pw-success">비밀번호가 일치합니다.</div>
							<div class="pw" id="pw-fail">비밀번호가 일치하지 않습니다.</div>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="userName" name="userName" placeholder="이름" onfocus="this.placeholder = ''" onblur="this.placeholder = '이름'">
							</div>
							<div class="col-md-12 form-group">
								<div class="sex-div">
									<input type="radio" id="name" name="userSex" class="input_radio"value="1" >
									<label for="">남자</label>
								</div>
								<div class="sex-div">
									<input type="radio" id="name" name="userSex" class="input_radio"value="2" >
									<label for="">여자</label>
								</div>
							</div>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="userAge" name="userAge" placeholder="나이" onfocus="this.placeholder = ''" onblur="this.placeholder = '나이'">
							</div>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="userPhone" name="userPhone" placeholder="전화번호" onfocus="this.placeholder = ''" onblur="this.placeholder = '전화번호'">
							</div>
							<div class="col-md-12 form-group">
								<input id="postcode1" type="text" value="" style="width:50px;" readonly/>
								&nbsp;-&nbsp;
								<input id="postcode2" type="text" value="" style="width:50px;" readonly/>
								&nbsp;&nbsp;
								<input id="zonecode" type="text" value="" style="width:50px;" readonly/>
								&nbsp;
								<input type="button" onClick="openDaumZipAddress()" value = "주소 찾기" />
								<br/>
								<input type="text" id="address" value="" style="width:355px;" readonly/>
								<input type="text" id="address_etc" value="" style="width:355px;" placeholder="상세주소"/>
							</div>
							<div class="col-md-12 form-group">
								<button type="submit" value="submit" class="button button-register w-100">개인정보 수정</button>
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