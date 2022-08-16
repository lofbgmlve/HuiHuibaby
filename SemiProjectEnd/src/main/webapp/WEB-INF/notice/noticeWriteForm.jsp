<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
</head>
<body>

<form name="writeForm" action="noticeWriteProcess.mvc" id="writeForm" 
	method="post">
	
	<h2 id="noticeTitle">공지사항 작성</h2>
	<table class="readTable">
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td>제목</td>
			<td class="readTd" colspan=3>
				<input type="text" name="subject" size="90" id="subject" style="width: 800px; margin-bottom: 12px;"/>
			</td>
		</tr>			
		<tr>
			<td>내용</td>
			<td class="readTd" colspan="3">
				<textarea name="content" id="content" rows="20" cols="80" style="width: 800px; resize: none;"></textarea>
			</td>
		</tr>
	</table>
	
	<div style="text-align: center;">
		<input type="reset" value="다시쓰기"/>
		<input type="submit" value="등록하기" />
		<input type="button" value="목록보기" 
				onclick="javascript:window.location.href='noticeList.mvc'"/>
	</div>
</form>
</body>
</html>