$(function() {

	// write  - 글쓰기  유효성 검사 
	$("#writeForm").on("submit", function() {
	
		  if ($("#ptitle").val().length <= 0) {
			alert("제목이 입력되지 않았습니다. \n제목을 입력해주세요.");
			$("#ptitle").focus();
			return false;

		} if ($("#pname").val().length <= 0) {
			alert("제품명이 입력되지 않았습니다.\n상품명을 입력해주세요.");
			$("#pname").focus();
			return false;

		} if ($("#ppart").val()=='none') {
			alert("상품분류를 선택하지 않았습니다.\n 상품분류를 선택해주세요.");
			$("#ppart").focus();
			return false;
		
		} if ($("input[name=condition]:radio:checked").length<1) {
			alert("제품상태를 입력하지 않았습니다. \n 제품상태를 선택해주세요.");
			$(".pcondition").focus();
			return false;

		} if (! $("#pway:checked").val()) {
			alert("거래방법을 선택하지 않았습니다.\n거래방법을 선택해주세요.");
			$("#pway").focus();
			return false;

		
		}if ($("#pprice").val().length <= 0) {
			alert("가격을 입력하지 않았습니다.\n가격을 입력해주세요.");
			$("#pprice").focus();
			return false;


		} if ($("#pfile").val().length <= 0) {
			var result = confirm("이미지를 등록하지 않았습니다. 최소한장의 이미를 등록해주세요. ");
	
			return false;
		
				
		} if ($("#pdetail").val().length <= 0) {
			alert("상세정보를 입력하지 않았습니다.\n상세정보를 입력해주세요.");
			$("#pdetail").focus();
			return false;
			
		}
		 
		

	});// writeForm


	


	// 수정하기  버튼 -detail
	$("#detailUpdate1").on("click", function() {

		var no = $(this).attr("data-no");
		
		$("#checkForm").attr("action", "updateForm.mvc");
		$("#chechForm").attr("method", "post");
		$("#checkForm").submit();

	});  // detailupdate








	// 업데이트 유효성검사 
	$("#updateForm").on("submit", function() {
		
		 if ($("#ptitle").val().length <= 0) {
			alert("제목이 입력되지 않았습니다. \n제목을 입력해주세요.");
			$("#ptitle").focus();
			return false;

		} if ($("#pname").val().length <= 0) {
			alert("제품명이 입력되지 않았습니다.\n상품명을 입력해주세요.");
			$("#pname").focus();
			return false;

		} if ($("#ppart").val()=='none') {
			alert("상품분류를 선택하지 않았습니다.\n 상품분류를 선택해주세요.");
			$("#ppart").focus();
			return false;
		
		} if ($("input[name=condition]:radio:checked").length<1) {
			alert("제품상태를 입력하지 않았습니다. \n 제품상태를 선택해주세요.");
			$(".pcondition").focus();
			return false;

		} if (! $("#pway:checked").val()) {
			alert("거래방법을 선택하지 않았습니다.\n거래방법을 선택해주세요.");
			$("#pway").focus();
			return false;

		
		}if ($("#pprice").val().length <= 0) {
			alert("가격을 입력하지 않았습니다.\n가격을 입력해주세요.");
			$("#pprice").focus();
			return false;


		} if ($("#pfile").val().length <= 0) {
			var result = confirm("이미지를 등록하지 않았습니다. 최소한장의 이미를 등록해주세요. ");
	
			return false;
		
				
		} if ($("#pdetail").val().length <= 0) {
			alert("상세정보를 입력하지 않았습니다.\n상세정보를 입력해주세요.");
			$("#pdetail").focus();
			return false;
			
		}
		 
		

	});// updateForm






	// 삭제하기 ! 
	$("#detailDelete").on("click", function() {

		$("#checkForm").attr("action", "deleteProcess.mvc");
		$("#checkFrom").attr("method", "post");
		$("#checkForm").submit();


	});








	// 검색 옵션 
	$("#psearchForm").on("submit", function() {
		var keyword = $("#keyword").val();
		if (keyword.length <= 0) {
			alert("검색어가 입력되지 않았습니다.\n검색어를 입력해주세요!");
			return false;
		}
		$(this).attr("method", "post");
		$(this).attr("action", "productList.mvc");
	});








});//