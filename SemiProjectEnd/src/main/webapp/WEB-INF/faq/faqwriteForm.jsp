<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="js/faq.js"></script>    
<article>
<form name="faqWriteForm" action="faqwriteProcess.mvc" id="faqWriteForm" method="post" >
	<table class="readTable">
		<tr>
			<td colspan="4" class="faqFaq_title">
				<h2>자주 찾는 질문 글쓰기</h2>
			</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td class="readTh">작성자</td>
			<td class="readTd">
				<input type="text" name="mg_id" size="53" id="mg_id" maxlength="30" placeholder="작성자를 입력하세요."/>
			</td>
		</tr>		
		<tr>
			<td class="readTh">제&nbsp;&nbsp;&nbsp;목</td>
			<td class="readTd" colspan=3>
				<input type="text" name="faq_title" size="53" id="faq_title" maxlength="100" placeholder="제목을 입력하세요."/>
			</td>
		</tr>			
		<tr>
			<td class="readTh">내&nbsp;&nbsp;&nbsp;용</td>
			<td class="readTd" colspan="3">
				<textarea name="faq_content" id="faq_content" rows="20" cols="93" maxlength="1000" placeholder="내용을 입력하세요."></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="4" class="tdSpan"><input type="reset" value="다시쓰기"/>
				&nbsp;&nbsp;<input type="submit" value="등록하기" />
				&nbsp;&nbsp;<input type="button" value="목록보기" 
					onclick="javascript:window.location.href='faqList.mvc'"/></td>
		</tr>
	</table>
</form>
</article>