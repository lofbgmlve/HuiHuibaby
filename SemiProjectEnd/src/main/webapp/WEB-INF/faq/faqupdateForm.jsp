<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article>
<form name="faqupdateForm" id="updateForm" action="faqupdateProcess.mvc"
	method="post" 
	${ not empty faq.faq_image ?  "" : "enctype='multipart/form-data'"}>
	<input type="hidden" name="faq_no" value="${ faq.faq_no }" />
	<input type="hidden" name="pageNum" value="${ pageNum }" />		
<table class="readTable">
	<tr>
		<td colspan="4" class="faqFaq_title"><h2>자주 찾는 질문 수정하기</h2></td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr>
		<th class="readTh">작성자</th>
		<td class="readTd">
			<input type="text" name="mg_id" id="mg_id" size="30" 
				maxlength="10" value="${ faq.mg_id }"/>
		</td>
	</tr>
	<tr>
		<th class="readTh">제&nbsp;&nbsp;목</th>
		<td class="readTd" colspan="3">
			<input type="text" name="faq_title" id="faq_title" size="50" 
				maxlength="50" value="${ faq.title }"/>
		</td>				
	</tr>
	<tr>
		<th class="readTh">내&nbsp;&nbsp;용</th>
		<td class="readTd" colspan="3">
			<textarea name="faq_content" id="faq_content" rows="20" 
				cols="72">${ faq.faq_content } </textarea>
		</td>				
	</tr>
	<tr>
			<td colspan="4">&nbsp;</td></tr>
	<tr>		
		<td class="tdSpan" colspan="4">
			<input type="reset" value="다시쓰기" />
			&nbsp;&nbsp;<input type="submit" value="수정하기" />
			
			<!-- 일반 게시 글 리스트에서 온 요청이면 일반 게시 글 리스트로 돌려보낸다. -->
			<c:if test="${ not searchOption }">		
				&nbsp;&nbsp;<input type="button" value="목록보기" 
					onclick="javascript:window.location.href=
						'faqList.mvc?pageNum=${ pageNum } &type= ${ type } &keyword= ${ keyword } '" />
			</c:if>
		</td>
	</tr>
</table>
</form>			
</article>