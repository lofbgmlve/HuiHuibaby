<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>	
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
//메뉴 마이스오버 슬라이드
	$(document).ready(function(){
		$(".menu>li").mouseover(function(){
			$(this).find(".sub").stop().slideDown(500);
		}).mouseout(function(){
			$(this).find(".sub").stop().slideUp(500);
		});
	});
</script>

<style>
	*{
		margin : 0px auto;
		padding : 0px;
		list-style : none;
		color : #000000;
		font-size : 20px;
	}
	
	header div {
		width : 1400px;
	}
	
	a{
		display : block;
		text-decoration : none;
	}
	
	nav {
		width : 1000px;
	}
	
	.menu>li{
		float : left;
		width : 250px;
		height : 40px;
		margin-top : 50px;
		background-color : #70a0cf;
	}
	
	.menu>li>a{
		line-height : 40px;
		text-align : center;
		font-weight : bold;
		color : #ffffff;
	}
	
	.menu>li:hover>a{
		background-color : #84acd3;
	}
	
	.sub{
		display : none;
	}
	
	.sub>li {
		width : 250px;
		height : 40px;
		background-color : #d7eafd;
	}
	
	.sub>li>a{
		line-height : 40px;
		font-size : 18px;
		text-align : center;
		color : #70a0cf;
	}
	
	.sub>li:hover>a{
		text-decolation : underline;
	}
	
	#mainLogo{
		width : 200px;
		height : 150px;
		float : left;
	}
	
	.login{
		float : right;
		padding-right : 10px; 
	}
	
	.mypage{
		float : right;
	}
</style>
	
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
 <div>
	<a href="#" class="mypage"><strong>마이페이지</strong></a>
	<a href="#" class="login"><strong>로그인</strong></a>
	<img id="mainLogo" src="images/mainLogo.png">
	<nav >
 		<ul class="menu">
 			<li><a href="#">상품</a>
 				<ul class="sub">
 					<li><a href="#">수유,위생 용품 & 스킨케어</a></li>
 					<li><a href="#">의류잡화</a></li>
 					<li><a href="#">장남감 & 도서</a></li>
 					<li><a href="#">유모차(관련용품)</a></li>
 				</ul>
 			</li>		
 		
 			<li><a href="#">고객 소통</a>
 				<ul class="sub">
 					<li><a href="#">커뮤니티</a></li>
 					<li><a href="#">후기</a></li> 
 				</ul>
 			</li>		
 		
 			<li><a href="#">공지사항</a>
 				<ul class="sub">
 					<li><a href="#">공지</a></li>
 					<li><a href="#">FAQ</a></li>
 				</ul>
 			</li>		
 		
 			<li><a href="#">고객센터</a>
 				<ul class="sub">
 					<li><a href="#">Q&A</a></li>
 				</ul>
 			</li>		
 		</ul>
 	</nav>
 </div>
</header>
</body>
</html>