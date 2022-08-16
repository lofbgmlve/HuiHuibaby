<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<article>
<form name="checkForm" id="checkForm">
	<input type="hidden" name="faq_no" id="rNo" value="${ faq.faq_no }"/>
	<input type="hidden" name="mg_pw" id="rMg_pw" />
	<input type="hidden" name="pageNum" value="${ pageNum }" />
		
	<c:if test="${ searchOption }">
		<input type="hidden" name="type" value="${ type }" />
		<input type="hidden" name="keyword" value="${ keyword }" />
	</c:if>
</form>
<table class="contentTable">
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td class="contentTdSpan" colspan="3"><strong>${ faq.faq_title }</strong></td>		
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td class="readFaq_content" colspan="4">
			<pre>${ faq.faq_content }</pre>
		</td>
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="4" class="tdSpan">
			<input type="button" id="detailUpdate" value="수정하기" onclick="javascript:window.location.href=
						'faqupdateForm.mvc?pageNum=${ pageNum }'"/>
			&nbsp;&nbsp;<input type="button" id="detailDelete" value="삭제하기"/>
			
			<c:if test="${ not searchOption }">		
				&nbsp;&nbsp;<input type="button" value="목록보기" 
					onclick="javascript:window.location.href=
						'faqList.mvc?pageNum=${ pageNum }'"/>		
			</c:if>
			
			<c:if test="${ searchOption }">		
				&nbsp;&nbsp;<input type="button" value="목록보기" 
					onclick="javascript:window.location.href=
						'faqList.mvc?pageNum=${ pageNum }&type=${ type }&keyword=${ keyword }'"/>		
			</c:if>
		</td>		
	</tr>
</table>
</article>
