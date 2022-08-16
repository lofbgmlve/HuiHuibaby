<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link type="text/css" href="css/community.css" rel="stylesheet">
<script src="js/reply.js"></script>
<article>
	<form name="checkForm" id="checkForm">
	<input type="hidden" name="no" id="no" value="${ community.communityNo }"/>
	<input type="hidden" name="pass" id="rPass" />
	<input type="hidden" name="pageNum" value="${ pageNum }" />	
	<c:if test="${ searchOption }">
		<input type="hidden" name="type" value="${ type }" />
		<input type="hidden" name="keyword" value="${ keyword }" />
	</c:if>
</form>
<table class="contentTable">
	<tr>
		<td colspan="4" class="boardTitle"><h2>게시 글 상세보기</h2></td>
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td class="contentTh">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
		<td class="contentTdSpan" colspan="3">${ community.communitySubject }</td>		
	</tr>
	<tr>
		<td class="contentTh">글쓴이</td>
		<td class="contentTd">${ community.nickName }</td>
		<td class="contentTh">작성일</td>
		<td class="contentTd"><fmt:formatDate value="${ community.communityDate }" 
			pattern="yyyy-MM-dd" /></td>		
	</tr>
	<tr>
		<td class="contentTh">이&nbsp;&nbsp;미&nbsp;&nbsp;지</td>
		<td class="contentTdSpan" colspan="3">
		<c:if test="${ empty community.communityImage }">
			이미지 없음
		</c:if>
		<c:if test="${ not empty communityImage }">
			<a href="upload/${ community.communityImage }">이미지 다운로드</a><br/>
			<a href="imageDownload.mvc?imageName=${ community.communityImage }">이미지 다운로드</a>
		</c:if>
		</td>		
	</tr>
	<tr>		
		<td class="readContent" colspan="4" style="max-width: 78px;">
			<pre>${ community.communityContent }</pre>
		</td>
	</tr>	
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="4" class="tdSpan">
			<input type="button" id="detailUpdate" value="수정하기"/>
			&nbsp;&nbsp;<input type="button" id="detailDelete" value="삭제하기" />
			<!-- 일반 게시 글 리스트에서 온 요청이면 일반 게시 글 리스트로 돌려보낸다. -->
			<c:if test="${ not searchOption }">		
				&nbsp;&nbsp;<input type="button" value="목록보기" 
					onclick="javascript:window.location.href=
						'communityList.mvc?pageNum=${ pageNum }'"/>
			</c:if>
			<!-- 검색 리스트에서 온 요청이면 검색 리스트의 동일한 페이지로 돌려보낸다. -->
			<c:if test="${ searchOption }">
				&nbsp;&nbsp;<input type="button" value="목록보기" 
					onclick="javascript:window.location.href=
						'communityList.mvc?pageNum=${ pageNum }&type=${ type }&keyword=${ keyword }'"/>
			</c:if>				
		</td>
	</tr>
	<!-- 댓글 리스트 출력 부분 -->
	<tr>
		<td colspan="4" class="replyHeader">
			<div id="recommend">
				<span id="replyWrite">
					&nbsp;댓글쓰기
				</span>					
			</div>
			<div id="replyTitle"><h3>이 글에 대한 댓글 리스트</h3></div>
		</td>
	</tr>
	<c:if test="${ empty replyList }" >
	<tr id="replyList">
		<td colspan="4">
			<div id="notReply">
				이 게시 글에 대한 댓글이 존재하지 않습니다.  
			</div>			
		</td>
	</tr>
	</c:if>
	<c:if test="${ not empty replyList }" >
	<tr id="replyList">
		<td colspan="4">		
		<table id="replyTable">
			<c:forEach var="reply" items="${ communityreplyList }" >
				<tr id="reply_${ reply.no }">
					<td>									
					<div class="replyUser">						
						<span class="member">${ communityreply.writer }</span>	
					</div>
					<div class="replyModify">
						<span class="reply_date">
							<fmt:formatDate value="${ communityreply.regDate}" 
								pattern="yyyy-MM-dd" /></span>
						<a href="#" class="modifyReply" data-no="${ communityreply.no }">
							<img src="images/reply_btn_modify.gif" alt="댓글 수정하기"/></a>
						<a href="#" class=deleteReply data-no="${ communityreply.no }">
							<img src="images/reply_btn_delete.gif" alt="댓글 삭제하기"/></a>
						<a href="javascript:reportReply('${ communityreply.no }')">
							<img src="images/reply_btn_notify.gif" alt="신고하기"/></a>
					</div>	
					<div class="replyContent" id="div_${ communityreply.no }">
						<pre><span>${ reply.reply }</span></pre>
					</div>
					</td>
				</tr>	
			</c:forEach>
		</table>				
		</td>
	</tr>	
	</c:if>
	</table>	
	<div id="replyForm">
		<form name="replyWriteForm" id="replyWriteForm">				
			<input type="hidden" name="bbsNo" value="${ communityNo }"/>
			<input type="hidden" name="replyWriter" value="${ sessionScope.id }" />			
			<table id="replyWriteTable">
				<tr>
					<td id="replyWriteTitle" colspan="2">
						<span>악의적인 댓글은 예고 없이 삭제될 수 있으며 글쓰기 제한과 아이디 삭제 처리됩니다.</span>
					</td>
				</tr>
				<tr>
					<td id="replyWriteContent">
						<textarea name="replyContent" id="replyContent" rows="4"></textarea>
					</td>
					<td id="replyWriteImage">						
						<input type="image" src="images/reply_btn_write.gif" 
							id="replyWriteButton" alt="댓글 입력" />
					</td>
				</tr>
			</table>
		</form>	
	</div>	
</article>