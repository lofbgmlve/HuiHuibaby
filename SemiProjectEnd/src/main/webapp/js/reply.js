$(function() {

	// 댓글 유효성 ! 
	$("#replyWriteForm").on("submit", function() {
		
		if ($("#replyContent").val().length <= 5) {
			alert("댓글을 5자 이상 입력해주세요.");
			return false;
		};


		var params = $(this).serialize();
		console.log(params);
		
		$.ajax({
			url: "replyWrite.ajax",
			type: "post",
			data: params,
			dataType: "json",
			success: function(resultData, status, xhr) {
				console.log(resultData);
				var replyTable = null;

				// 최초 댓글 쓰기
				if ($("#replyTable").length == 0) {
					replyTable = $("<table id='replyTable'></table>");
					$("#replyList > td").empty();
					$("#replyList > td").append(replyTable);

				} else { // 기존 댓글이 있을 때 댓글 쓰기
					$("#replyTable").empty();
					replyTable = $("#replyTable");
				}
				
				$.each(resultData, function(index, value) {
					// 날짜 데이터를 출력 포맷에 맞게 수정
					var date = new Date(value.date);
					var strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10)
						? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"
						+ date.getDate() + "-" + ((date.getHours() < 10)
							? "0" + date.getHours() : date.getHours()) + ":"
						+ (date.getMinutes() < 10 ? "0" + date.getMinutes()
							: date.getMinutes()) + ":" + (date.getSeconds() < 10
								? "0" + date.getSeconds() : date.getSeconds());


					// 응답 후result 값 설정  
					var result = 
						"<tr class='reply_" + value.no + "'>" 
						+ "<td>"
						+ "	<div class='replyUser'>"
						+ "		<span class='member'>" + value.nickname + "</span>"
						+ "	</div>"
						+ "	<div class='replyModify'>"
						+ "		<span class='replyDate'>" + strDate + "</span>"
						+ "		<a href='#' class='modifyReply' data-no='" + value.no + "'>"
						+ "			<img id='replyPt' src='product_img/updatepen.png'alt='수정하기'>"
						+ "		</a>"
						+ "		<a href='#' class='deleteReply' data-no='" + value.no + "'>"
						+ "			<img id='replyPt' src='product_img/delete.png' alt='댓글 삭제하기'/>"
						+ "		</a>"
						+ "	</div>"
						+ "	<div class='replyContent' id='div_" + value.no + "'>"
						+ "		<pre><span>" + value.content + "</span></pre>"
						+ "	</div>"
						+ "</td>"
					+ "</tr>";
					
						replyTable.append(result);								
				});			
				
				// 댓글 쓰기가 완료되면 댓글 쓰기 폼을 숨긴다.
				$("#replyContent").val("");
				//$("#replyForm").slideUp(300).add("#replyContent").val("");
				console.log("write : " + $("#replyForm").length);
			},
			error: function(xhr, status, error) {
				alert("ajax 실패 : " + status + " - " + xhr.status);
			}
		
		});// ajax
			return false;
	});// replyWriteForm






});  // function