<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article>
	<form name="writeForm" action="communityWriteProcess.mvc" 
			id="writeForm" method="post" enctype="multipart/form-data">
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
					<input type="text" name="m_nickname" size="30" id="m_nickname" maxlength="10"/>
				</td>
			</tr>
			<tr>
				<td class="readTh">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
				<td class="readTd" colspan=3>
					<input type="text" name="c_subject" size="90" id="c_subject" maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td class="readTh">내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
				<td class="readTd" colspan="3">	
					<textarea name="c_content" id="c_content" rows="20" cols="80"></textarea>
				</td>
			</tr>
			<tr>
				<td class="readTh">이&nbsp;&nbsp;미&nbsp;&nbsp;지</td>
				<td class="readTd" colspan=3>
					<input type="file" name="c_image" size="70" id="c_image" maxlength="50"/>
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
				onclick="javascript:window.location.href='communityList.mvc'"/></td>
			</tr>
		</table>
	</form>	
</article>