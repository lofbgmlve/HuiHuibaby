<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<article>
<div class="noticeTitle" style="margin: 0 auto; text-align: center; margin-top:200px;">
	<div style="font-size: 20px; font-weight: bold; margin-bottom: 12px;">공지사항</div>
	
	<table style="border-collapse: collapse;margin: 0 auto; width: 850px;">
		<tr class="title" style="border-top: thin solid; ">
			<th style="width: 10%">번호</th>
			<th style="width: 30%">제목</th>
			<th style="width: 30%">글쓴이</th>
			<th style="width: 30%">작성시간</th>
		</tr>
		<c:forEach var="notice" items="${ noticeList }" >
			<tr class="write" style="border-top: thin solid; border-color: #ababab; ">
				<td class="contentTh">${notice.nt_no }</td>
				<td class="contentTd">
					<a href="noticeDetail.mvc?no=${ notice.nt_no }"> ${ notice.nt_subject }</a>
				</td>
				<td class="contentTh">${notice.mg_id }</td>
				<td class="contentTd"><fmt:formatDate pattern="yyyy년 MM월 dd일" value="${notice.nt_date }"/></td>
			</tr>
		</c:forEach>
	</table>
	
		<div style="text-align: center; margin-top: 12px;">
			<input type="search" name="search" />
			<input type="submit" name="submit" />
			<a href="noticeWriteForm.mvc" style="margin-left: 15px;">작성하기</a>
		</div>
	</div>
</article>

</html>
