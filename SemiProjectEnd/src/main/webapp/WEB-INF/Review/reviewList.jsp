<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.project.dao.*, com.project.vo.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<article>
	<table class="listTable">
		<tr>
		<td class="reviewTitle" colspan="4"><h2>리 뷰</h2></td>
		</tr>
		<tr>
		<td colspan="4">
			<form name="searchForm" id="searchForm" action="#">
				<select name="type">
					<option value="reviewSubject">제목</option>
					<option value="nickName">작성자</option>
					<option value="reviewContent">내용</option>
				</select>
				<input type="text" name="keyword" />
				<input type="submit" value="검색" />
			</form>
		</td>
		</tr>
		<!-- 검색 요청일 경우만 아래를 화면에 표시 한다. -->
	<c:if test="${ searchOption }">
	<tr>
		<td colspan="5" id="searchComment" style="board: 1px solid red">
			"${ keyword }" 검색 결과</td>
	</tr>
	</c:if>
	<c:if test="${ not searchOption }">
	<tr>			
		<td colspan="4" class="listWrite"><a href="reviewWriteForm.mvc">글쓰기</a></td>
	</tr>
	</c:if>
	<c:if test="${ searchOption }">
	<tr>			
		<td colspan="2" class="reviewListLink"><a href="reviewList.mvc">리스트</a></td>
		<td colspan="3" class="listWrite"><a href="reviewWriteForm.mvc">글쓰기</a></td>
	</tr>
	</c:if>
	<tr>
	<th class="listThNo">NO</th>
		<th class="listThSubject">제목</th>
		<th class="listThNickname">작성자</th>
		<th class="listThDate">작성일</th>
	</tr>
		<c:if test="${ searchOption and not empty rList }">
	<c:forEach var="r" items="${ rList }" varStatus="status">		
	<tr class="listTr">
		<td class="listTdNo">${ r.rNo  }</td>
		<td class="listTdSubject">
			<a href="reviewDetail.mvc?r_no=${ r.rNo }&pageNum=${ currentPage }
				&type=${ type }&keyword=${ keyword }">${ r.rSubject }</a>
		</td>
		<td class="listTdNickname">${ r.rNickname }</td>
		<td class="listTdDate"><fmt:formatDate value="${ r.rdate }" 
			pattern="yyyy-MM-dd" /></td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="4" class="listPage">
		 	<c:if test="${ startPage > pageGroup }">
				<a href="reviewList.mvc?pageNum=${ startPage - pageGroup }
					&type=${ type }&keyword=${ keyword }">[이전]</a>
			</c:if>	
			<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
					[ ${ i } ]
				</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="reviewList.mvc?pageNum=${ i }&type=${ type }
						&keyword=${ keyword }">[ ${ i } ]</a>
				</c:if>			
			</c:forEach>
			<c:if test="${ endPage < pageCount }">
				<a href="reviewList.mvc?pageNum=${ startPage + pageGroup }
					&type=${ type }&keyword=${ keyword }">[다음]</a>
			</c:if>		
		</td>
	</tr>
</c:if>	
<c:if test="${ not searchOption and not empty rList }">
	<c:forEach var="r" items="${ rList }" varStatus="status">		
	<tr class="listTr">
		<td class="listTdNo">${ r.rNo  }</td>
		<td class="listTdSubject">
			<a href="reviewDetail.mvc?r_no=
				${ r.rNo }&pageNum=${ currentPage }" >${ r.rSubject }</a>
		</td>
		<td class="listTdNickname">${ r.rNickname }</td>
		<td class="listTdDate"><fmt:formatDate value="${ r.rDate }" 
			pattern="yyyy-MM-dd" /></td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="4" class="listPage">
		 	<c:if test="${ startPage > pageGroup }"> 
				<a href="reviewList.mvc?pageNum=${ startPage - pageGroup }">
					[이전]</a>
			</c:if>	
			<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
				<c:if test="${ i == currentPage }">
					[ ${ i } ]
				</c:if>			
				<c:if test="${ i != currentPage }">
					<a href="reviewList.mvc?pageNum=${ i }">[ ${ i } ]</a>
				</c:if>			
			</c:forEach>
			<c:if test="${ endPage < pageCount }">
				<a href="reviewList.mvc?pageNum=${ startPage + pageGroup }">
					[다음]</a>
			</c:if>		
		</td>
	</tr>
</c:if>
<!-- 검색 요청이면서 검색된 리스트가 존재하지 않을 경우 -->
<c:if test="${ searchOption and empty rList }">
	<tr>
		<td colspan="4" class="listTdSpan">
			"${ keyword }"가 포함된 게시 글이 존재하지 않습니다.</td>
	</tr>
</c:if>
<!-- 일반 게시 글 리스트 요청이면서 게시 글 리스트가 존재하지 않을 경우 -->
<c:if test="${ not searchOption and empty rList }">
	<tr>
		<td colspan="4" class="listTdSpan">게시 글이 존재하지 않습니다.</td>
	</tr>
</c:if>
</table>
</article>