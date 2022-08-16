<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article>
	<form name="writeForm" action="writeProcess.jsp" id="writeForm" method="post">
		<table class="readTable">
			<tr>
				<td colspan="4" class="biardTitle">
				<h2>게시 글 쓰기</h2>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td class="readTh">글쓴이</td>
				<td class="readTd">
					<input type="text" name="writer" size="30" id="writer" maxlength="10"/>
				</td>
			</tr>
			<tr>
				<td class="readTh">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
				<td class="readTd" colspan=3>
					<input type="text" name="title" size="90" id="title" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td class="readTh">내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
				<td class="readTd" colspan="3">	
					<textarea name="content" id="content" rows="20" cols="80"></textarea>
				</td>
			</tr>
			<tr>
				<td class="readTh">이&nbsp;&nbsp;미&nbsp;&nbsp;지</td>
				<td class="readTd" colspan=3>
					<input type="image" name="image" size="70" id="image" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" class="tdSpan">
				<input type="reset" value="다시쓰기"/>&nbsp;&nbsp;
				<input type="submit" value="등록하기" />&nbsp;&nbsp;
				<input type="button" value="목록보기"
				onclick="javascript:window.location.href='reviewList.jsp'"/></td>
			</tr>
		</table>
	</form>	
</article>