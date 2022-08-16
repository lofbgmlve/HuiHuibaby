$(function() {
	// 게시 글쓰기 폼 유효성 검사
	$("#faqWriteForm").on("submit", function() {
		if($("#mg_id").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#mg_id").focus();			
			return false;
		}
		if($("#faq_title").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#faq_title").focus();
			return false;
		}
		if($("#faq_content").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#faq_content").focus();
			return false;
		}
	});
	
	// 게시 글 수정 폼 유효성 검사
	$("#updateForm").on("submit", function() {
		if($("#mg_id").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#mg_id").focus();			
			return false;
		}
		if($("#faq_title").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#faq_title").focus();
			return false;
		}
		if($("#faq_content").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#faq_content").focus();
			return false;
		}		
	});
	
	//게시 글 내용 보기에서 게시 글 삭제 요청 처리
	$("#detailDelete").on("click", function() {
			
		var mg_pw = $("#mg_pw").val();
		if(mg_pw.length <= 0) {
			alert("게시 글을 수정하려면 비밀번호를 입력해주세요");
			return false;
		}
		
		$("#rMg_pw").val(mg_pw);
		$("#checkForm").attr("action", "deleteProcess.mvc");
		$("#checkForm").attr("method", "post");
		$("#checkForm").submit();
	});
	
});