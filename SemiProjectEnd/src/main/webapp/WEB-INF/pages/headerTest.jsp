<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<ul>
		<li><a href='${ sessionScope.isLogin ? "logout.mvc" : "loginForm.mvc" }'>
					${ sessionScope.isLogin ? "로그아웃" : "로그인" }</a></li>
		<li>
			<c:if test="${ not sessionScope.isLogin }" ><!--비 로그인상태 -->
				<a href="joinForm.mvc">회원가입</a><!-- 회원가입 링크 노출 -->
			</c:if>
			<c:if test="${ sessionScope.isLogin }" >	<!-- 로그인 시 나타나는 링크 -->
				<a href="myPage.mvc">마이페이지</a> <!-- 마이페이지 이동 -->
				<a href="userUpdateForm.mvc">정보수정</a> <!-- 정보수정 페이지 링크 -->
			</c:if>
		</li>
	<li>
			<a href="productList.mvc">홈</a>		<!-- 기능 확인하려고 추가한 부분입니다. -->
	</li>
	<li>
			<a href="noticeList.mvc">공지사항</a>	<!-- 공지사항 임시 폼 -->
	</li>
	<li>
			<c:if test = "m_role == 'ADMIN'" >	<!-- 로그인 시 나타나는 링크 -->
				<a href="adminProductList.mvc">마이페이지</a> <!-- 마이페이지 이동 -->
			</c:if>
		</li>
	</ul>	
</header>