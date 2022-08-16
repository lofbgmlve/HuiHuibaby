<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	nav > ul.menu a{
		display : block;
		text-decoration : none;
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
		color: #FF0000;
	}
	
	.sub > li:hover {
		background-color : #FFEAEA;		
	}
	
	#mainLogo{
		width : 130px;
		height : 85px;
		float : left;
		margin-top : 40px;
		margin-right : 50px;
	}
	
	.login{
		float : right;
		padding-right : 10px; 
	}
	
	.mypage{
		float : right;
	}
</style>
	

<header>
 <div>
 	<c:if test="${ not sessionScope.isLogin }" ><!--비 로그인상태 -->
				<a href="joinForm.mvc">회원가입</a><!-- 회원가입 링크 노출 -->
	</c:if>
	<c:if test="${ sessionScope.isLogin }" >	<!-- 로그인 시 나타나는 링크 -->
			<a href="myPage.mvc" class="mypage"><strong>마이페이지</strong></a>&nbsp;&nbsp;&nbsp;&nbsp; <!-- 마이페이지 이동 -->
			<a href="userUpdateForm.mvc" class="mypage"><strong>정보수정</strong></a> <!-- 정보수정 페이지 링크 -->
	</c:if>
	<a href='${ sessionScope.isLogin ? "logout.mvc" : "loginForm.mvc" }' class="login">
			<strong>${ sessionScope.isLogin ? "로그아웃" : "로그인" } </strong></a>
	<a href="productList.mvc"><img id="mainLogo" src="images/mainLogo.png"></a>
	<nav >
 		<ul class="menu">
 			<li><a href="productList.mvc">상품</a>
 				<ul class="sub">
 					<li><a href="productpart.mvc?p_part=1" >수유*위생*스킨</a></li>
 					<li><a href="productpart.mvc?p_part=2"  >패션의류잡화</a></li>
 					<li><a href="productpart.mvc?p_part=3" >장남감 * 도서</a></li>
 					<li><a href="productpart.mvc?p_part=4"  >유모차*관련용품</a></li>
 				</ul>
 			</li>		
 		
 			<li><a href="#">고객 소통</a>
 				<ul class="sub">
 					<li><a href="communityList.mvc">커뮤니티</a></li>
 					<li><a href="reviewList.mvc">후기</a></li> 
 				</ul>
 			</li>		
 		
 			<li><a href="noticeList.mvc">공지사항</a>
 				<ul class="sub">
 					<li><a href="noticeList.mvc">공지</a></li>
 					<li><a href="faqList.mvc">FAQ</a></li>
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