<%--
   회원 가입시 아이디 중복검사 요청에 대한 처리 결과를 출력할 View 페이지
   이 페이지는 새창으로 실행되고 중복 아이디 체크를 할 수 있는 폼을 제공한다.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" href="css/user.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/user.js"></script>
<title>중복 닉네임 체크</title>
</head>
<body>
   <div id="idCheckForm">
   <c:choose>
      <c:when test="${ overlap == true}" >
         <h3>사용할 수 없는 닉네임</h3>
         <div class="memberInputText">
            입력하신 <strong>${ nickname }</strong>는 이미 사용 중인 닉네임 입니다.
         </div>
         <div class="memberInputText">다른 닉네임을(를) 선택해 주세요</div>      
         <form action="overlapNicknameCheck.mvc" name="nicknameCheckForm" 
            method="post" id="nicknnameCheckForm">
            <div class="memberInputConfirm">
               <span class="checkFormSpan">닉네임 </span>
               <input type="text" name="mNickname" id="checkNickname" size="15"/>
               <input type="submit" value="중복확인" />
            </div>
         </form>
      </c:when>
      <c:otherwise>
         <h3>사용할 수 있는 닉네임</h3>
         <div class="memberInputText">
            입력하신 <strong>${ nickname }</strong>는 사용할 수 있는 아이디 입니다.</div>
         <div class="memberInputConfirm">
            <input type="button" value="${ nickname }을(를) 닉네임으로 사용하기" 
               id="btnNicknameCheckClose" data-nickname-value="${ nickname }"/>
         </div>
      </c:otherwise>      
   </c:choose>
   </div>
</body>
</html>