<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세</title>
</head>
<body>
<br/><br/><br/>

<table>
   <tr>
      <td>제목</td>
      <td>${ notice.nt_subject }</td>
   </tr>
</table>

   <div style="margin: 0 auto;">
      <div style="text-align: center; font-size:30px; font-weight: bold;">공지사항 #${notice.nt_no }</div>
      <div style="margin-top: 10px; text-align: center;">
         <div style="margin-bottom: 10px; font-size: 18px; ">제목</div>
         <div style="margin-bottom: 15px; ">${ notice.nt_subject }</div>
         <div style="margin-bottom: 10px; font-size: 18px; ">내용</div>
         <div style="margin-bottom: 15px; ">${ notice.nt_content }</div>
         <div style="margin-bottom: 10px; font-size: 18px; ">작성일</div>
         <div style="margin-bottom: 15px; "><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${notice.nt_date }"/></div>
      </div>
   </div>
</body>
</html>