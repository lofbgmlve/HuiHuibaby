$(function() {
	// 게시 글쓰기 폼 유효성 검사
	$("#WriteForm").on("submit", function() {
		if($("#write").val().length <= 0) {
			alert("작성자가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#write").focus();			
			return false;
		}
		if($("#title").val().length <= 0) {
			alert("제목이 입력되지 않았습니다.\n제목을 입력해주세요");
			$("#title").focus();
			return false;
		}
		if($("#content").val().length <= 0) {
			alert("내용이 입력되지 않았습니다.\n내용을 입력해주세요");
			$("#content").focus();
			return false;
		}
		if($("#image").val().length <= 0) {
			var result = confirm("파일이 추가되지 않았습니다." +
					"\n사진파일 없이 게시 글을 등록 하시겠습니까?");
			if(!result) {
				$("#image").focus();
				return false;
			}
		}		
	});

	
});