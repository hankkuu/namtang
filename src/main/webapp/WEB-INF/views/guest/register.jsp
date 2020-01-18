<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
  	
  	#pwSuccess{
  		color:blue; font-size:10px;
  	}
  	
  	#pwFail{
  		color:red; font-size:10px;
  	}
  
  	.login_box_area .login_box_img:before{
  		height:810px;
  	}
  	
  	#userId{
  		 width:280px; display:inline-block; 
  	}
  	
  	#userIdC div:nth-child(1){
  		display:inline-block;	
  	}

	.login_form .sex-div{
		float:left;
		width:50%;
		border-bottom: 1px solid #cccccc;
	}
	
	#personalDate{
		margin-top:-2px; font-size:10px; 
	}
	
	</style>

	<script type="text/javascript" src="/js/json.js"></script>
	<script type="text/javascript" >
		$(function(){
			
			/*아이디 중복체크*/

			$("#confirmId").click(function(){
				let id=$("#userId").val();
				console.log(id);
				$.ajax({
					type : "get",
					url : "/api/v1/account/checkId",
					dataType : "json",
					data : { id: id },
					success : function(result){
						console.log("1 = 중복o / 0 = 중복x : " + result);

						if(result.statusCode === "Fail" ){
							$("#checkId").text("아이디가 중복됩니다.");
							$("#checkId").css("color","red");
						} else {
							$("#checkId").text("아이디 사용가능합니다.");
							$("#checkId").css("color","blue");
						}
					},
					error : function(error) {
						console.log(error);
						alert("아이디 중복 체크 오류 발생");
					}
				});
			}); //아이디 중복체크 끝
			
			//회원가입
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
						alert("회원가입 오류 발생");
					}
				});//ajax끝
			})
			
			/* 비밀번호 입력 */

	  		/* $("#userPassword").blur(function(){
	  			let userIdCheck = $("#userId").val();
	  			let userPwCheck = $("#userPassword").val();
	  			let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	  			
	  			if(userPwCheck.indexOf(userIdCheck) != -1){
	  				$("#textP").text("아이디가 포함되어있습니다.");
	  			};
	  			
  				if(false === reg.test(userPwCheck)) { 
  					$("#textP").text("비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.");
  				} else {
  				 	$("#textP").css("color","blue");
  					$("#textP").text("성공");
  				}

  				
	  		}); */
	  		
	  		/* 비밀번호 일치여부*/
			$("#pwSuccess").hide();
			$("#pwFail").hide();
				$("input").keyup(function() {
					var pwd1 = $("#userPassword").val();
					var pwd2 = $("#confirmPassword").val();
					if (pwd1 != "" || pwd2 != "") {
						if (pwd1 == pwd2) {
							$("#pwSuccess").show();
							$("#pwFail").hide();
							$("#confirmPassword").blur(function(){
								$("#pwSuccess").hide();
							}); 
							$("#submit").removeAttr("disabled");
						} else {
							$("#pwFail").show();
							$("#pwSuccess").hide();
							$("#submit").attr("disabled", "disabled");
						}
					}
					
				 	//$("#confirmPassword").blur(function(){
						//$("#pw-success").hide();
					//}); 

				});
				
			}); //js 끝
		
		/* 주소찾기 */
	  	function openDaumZipAddress() {
			new daum.Postcode({
				oncomplete:function(data) {
					$("#userAddress").val(data.address);
					$("#userAddressDetail").focus();
					$("#userPost").val(data.postcode1 +"-"+ data.postcode2 + "-"+data.zonecode);					console.log(data.postcode1 +"-"+ data.postcode2 + "-"+data.zonecode);				
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
							<div class="col-md-12 form-group" id="userIdC">
								<input type="text" class="form-control" id="userId" name="userId" placeholder="아이디" onblur="this.placeholder = '아이디'">

								<input type="button" id="confirmId" name="confirmId" class="confirmId" value="중복확인" >	

			                </div>
			                
			                <div class="check_font" id="checkId"></div>
			                
			                <div class="col-md-12 form-group">
								<input type="password" class="form-control" id="userPassword" name="password" placeholder="비밀번호" onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호'">
			                </div>
			                <p id="textP"></p>
			                <div class="col-md-12 form-group">
								<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인" onfocus="this.placeholder = ''" onblur="this.placeholder = '비밀번호 확인'">
							</div>
							<div class="pw" id="pwSuccess">비밀번호가 일치합니다.</div>
							<div class="pw" id="pwFail">비밀번호가 일치하지 않습니다.</div>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="userEmail" name="email" placeholder="이메일 주소" onblur="this.placeholder = '이메일 주소'">
			                </div>

							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="userName" name="userName" placeholder="이름" onfocus="this.placeholder = ''" onblur="this.placeholder = '이름'">
							</div>
							<div class="col-md-12 form-group">
								<div class="sex-div">
									<input type="radio" id="man" name="sex" class="input_radio"value="1" >
									<label>남자</label>
								</div>
								<div class="sex-div">
									<input type="radio" id="woman" name="sex" class="input_radio"value="2" >
									<label>여자</label>
								</div>
							</div>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="userPhone" name="phone" placeholder="전화번호" onfocus="this.placeholder = ''" onblur="this.placeholder = '전화번호'">
							</div>
							<div class="col-md-12 form-group">
								
								<input type="text" id="userPost" name="userPost" style="width:278px;" value="우편번호" readonly/>
								<input type="button" onClick="openDaumZipAddress()" value = "주소 찾기" />
								<br/>

								<input type="text" id="userAddress" name="userAddress" style="width:355px;" value="주소" readonly/>

								<input type="text" id="userAddressDetail" name="userAddressDetail" style="width:355px;" placeholder="상세주소" onfocus="this.placeholder = ''" onblur="this.placeholder = '상세주소'"/>
							</div>
							<input type="checkbox" id="checkPersonalDate"><a href="https://hankkuu.tistory.com/77" id="personalDate" target="_blank">개인정보 수집 및 활용 동의</a>
							<div class="col-md-12 form-group">
								<input type="button" value="submit" id="register" class="button button-register w-100">회원가입
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