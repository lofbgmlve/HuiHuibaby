$(function(){ //dom이 준비되면 실행되는 콜백 함수
	
	$("#loginFrom").submit(function(){ //로그인 버튼 클릭시 유효성 검사 
		var id = $("#userId").val();
		var pass = $("#userPass").val();
		
		if(id.length <= 0){
			alert("아이디가 입력되지 않았습니다. \n아이디를 입력해주세요");
			$("#userId").focus();
			return false;
		} //아이디 입력되지 않았을 때
		
		if(pass.length <= 0){
			alert("비밀번호가 입력되지 않았습니다. \n 비밀번호를 입력해주세요");
			$("#userPass").focus();
			return false;
		}//비밀번호 입력되지 않았을 때
		
	})//end #loginForm
	
	
	// id keyup event, 정규표현식을 사용
	$("#mId").on("keyup", function() {		 
		var regExp = /[^A-Za-z0-9]/gi;	
		
		if(regExp.test($(this).val())) {
			alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
			$(this).val($(this).val().replace(regExp, ""));
		}
	});
	
	// password, email에도 keyup event
	$("#mPw").on("keyup", inputCharReplace);	
	$("#mPw2").on("keyup", inputCharReplace);	
	$("#mEmailId").on("keyup", inputCharReplace);	
	$("#mEmailDomain").on("keyup", inputEmailDomainReplace);
	
	
	// 회원가입 폼 - 아이디 중복확인 버튼 클릭시 event
	$("#btnOverlapId").on("click", function() {
		var id = $("#mId").val();
		url="overlapIdCheck.mvc?mId=" + id;
		
		if(id.length == 0) {
			alert("아이디를 입력해주세요");
			return false;
		}
		
		if(id.length < 4) {
			alert("아이디는 4자 이상 입력해주세요.");
			return false;
		}
		
		window.open(url, "idCheck", "toolbar=no, location=no, " 
				+  "status=no, menubar=no, width=400, height=200");
	});
	
	// 중복 아이디로 검색했을때 새 창에서 다시 중복확인
	$("#idCheckForm").on("submit", function() {
		var id = $("#checkId").val();	
		
		if(id.length == 0)  {
			alert("아이디를 입력해주세요");
			return false;
		}
		
		if(id.length < 4) {
			alert("아이디는 4자 이상 입력해주세요.");
			return false;
		}
	});
	
	// 중복 확인 창에서 '아이디 사용하기' 버튼 click event
	$("#btnIdCheckClose").on("click", function() {
		var id = $(this).attr("data-id-value");
		opener.document.joinForm.mId.value = id;
		opener.document.joinForm.isIdCheck.value = true;
		window.close();
	});
	
	
	// 닉네임 중복확인 - 형식은 id와 같음
	$("#btnOverlapNickname").on("click", function() {
		var nickname = $("#mNickname").val();
		url="overlapNicknameCheck.mvc?mNickname=" + nickname;
		
		if(nickname.length == 0) {
			alert("닉네임을 입력해주세요");
			return false;
		}
		
		if(nickname.length < 4) {
			alert("닉네임은 4자 이상 입력해주세요.");
			return false;
		}
		
		window.open(url, "nicknameCheck", "toolbar=no, location=no, " 
				+  "status=no, menubar=no, width=400, height=200");
	});
	
	// 중복 닉네임으로 검색했을때 새 창에서 다시 중복확인
	$("#NicknameCheckForm").on("submit", function() {
		var nickname = $("#checkNickname").val();	
		
		if(nickname.length == 0)  {
			alert("닉네임을 입력해주세요");
			return false;
		}
		
		if(nickname.length < 4) {
			alert("닉네임은 4자 이상 입력해주세요.");
			return false;
		}
	});
	
	
	// 중복 확인 창에서 '닉네임 사용하기' 버튼 click event
	$("#btnNicknameCheckClose").on("click", function() {
		var nickname = $(this).attr("data-nickname-value");
		opener.document.joinForm.mNickname.value = nickname;
		opener.document.joinForm.isNicknameCheck.value = true;
		window.close();
	});

	
	
	// 이메일 입력 셀렉트 박스에서 선택된 도메인을 설정하는 함수 
	$("#selectDomain").on("change", function() {
		var str = $(this).val();
		
		if(str == "직접입력") {	
			$("#mEmailDomain").val("");
			$("#mEmailDomain").prop("readonly", false);
			
		} else if(str == "네이버"){	
			$("#mEmailDomain").val("naver.com");			
			$("#mEmailDomain").prop("readonly", true);
			
		} else if(str == "다음") {		
			$("#mEmailDomain").val("daum.net");
			$("#mEmailDomain").prop("readonly", true);
			
		} else if(str == "한메일"){	
			$("#mEmailDomain").val("hanmail.net");
			$("#mEmailDomain").prop("readonly", true);
			
		} else if(str == "구글") {		
			$("#mEmailDomain").val("gmail.com");
			$("#mEmailDomain").prop("readonly", true);
		}
	});
	
	
	// 회원 가입 폼이 서브밋 될 때 이벤트 처리 - 폼 유효성 검사
	$("#joinForm").on("submit", function() { 
		return joinFormCheck();
	});
	

	// 회원가입 수정 폼- 비밀번호 확인
	$("#btnPassCheck").click(function() {
		var oldId = $("#mId").val();
		var oldPass = $("#oldPass").val();
		
		if($.trim(oldPass).length == 0) {
			alert("기존 비밀번호가 입력되지 않았습니다.\n기존 비밀번호를 입력해주세요");
			return false;
		}
		var data = "mId=" + oldId + "&mPw="+oldPass;
		console.log("data : " + data);
		
		$.post("passCheck.ajax", data, function(resultData, status, xhr) {
			console.log("시작은 됨");
			if(status == "success") {				
				var obj =resultData;
				console.log("resultData : " + resultData);
				
				if(obj.result == -1) {
					alert("존재하지 않는 아이디입니다.");
					
				} else if(obj.result == 0) {
					alert("비밀번호가 다릅니다.\n비밀번호를 다시 입력하고 비밀번호를 확인해주세요");
					$("#oldPass").val("").focus();
					
				} else if(obj.result == 1) {
					alert("비밀번호가 확인되었습니다.\n비밀번호를 수정해주세요");
					$("#btnPassCheck").prop("disabled", true);
					$("#mPw").focus();
				}
			}
		});
	});	
	
	
	// 비밀번호 확인 후, 비밀번호 수정
	$("#oldPass").change(function() {
		$("#btnPassCheck").prop("disabled", false);
	});
		
	
	// 회원정보 수정 폼에서 수정하기 버튼이 클릭되면 유효성 검사를 하는 함수
	$("#userUpdateForm").on("submit", function() {
		
		/* 회원정보 수정 폼에서 "비밀번호 확인" 버튼이 disabled 상태가 아니면
		 * 기존 비밀번호를 확인하지 않았기 때문에 확인하라는 메시지를 띄운다.
		 **/
		if(! $("#btnPassCheck").prop("disabled")) {
			alert("기존 비밀번호를 확인해야 비밀번호를 수정할 수 있습니다.\n"
				+ "기존 비밀번호를 입력하고 비밀번호 확인 버튼을 클릭해 주세요");
			return false;
		}
		
		return joinFormCheck();
	});
	
}); // function()


/* 회원 아이디, 비밀번호, 비밀번화 확인, 이메일 아이디 폼 컨트롤에
 * 사용자가 입력한 값이 영문 대소문자, 숫자 만 입력되도록 수정하는 함수
 **/
function inputCharReplace() {
	// 아래와 같이 정규표현식을 이용해 영문 대소문자, 숫자만 입력되었는지 체크할 수 있다. 
	var regExp = /[^A-Za-z0-9]/gi;	
	if(regExp.test($(this).val())) {
		alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
		$(this).val($(this).val().replace(regExp, ""));
	}
}


/* 이 메일 도메인 입력 폼 컨트롤에 사용자가 입력한 값이 
 * 영문 대소문자, 숫자, 점(.)만 입력되도록 수정하는 함수 
 **/ 
function inputEmailDomainReplace() {
	var regExp = /[^a-z0-9\.]/gi;	
	if(regExp.test($(this).val())) {
		alert("이메일 도메인은 영문 소문자, 숫자, 점(.)만 입력할 수 있습니다.");
		$(this).val($(this).val().replace(regExp, ""));
	}
}


/* 회원 가입 폼과 회원정보 수정 폼의 유효성 검사를 하는 함수*/
function joinFormCheck() {
	var id = $("#mId").val();
	var pass1 = $("#mPw").val();
	var pass2 = $("#mPw2").val();
	var nickname = $("#mNickname").val();
	var name = $("#mName").val();
	var phone2 = $("#mPhone2").val();
	var phone3 = $("#mPhone3").val();
	var gender = $("input:radio[name=mGender]:checked");
	var emailId = $("#mEmailId").val();
	var emailDomain = $("#mEmailDomain").val();
	var isIdCheck = $("#isIdCheck").val();
	var isNicknameCheck = $("#isNicknameCheck").val();
	var birth1 = $("#mBirth1").val();
	var birth3 = $("#mBirth3").val();
	
	
	if(id.length == 0) {		
		alert("아이디가 입력되지 않았습니다.\n아이디를 입력해주세요");
		return false;
	}		
	if(isIdCheck == 'false') {		
		alert("아이디 중복 체크를 하지 않았습니다.\n아이디 중복 체크를 해주세요");
		return false;
	}
	if(pass1.length == 0) {		
		alert("비밀번호가 입력되지 않았습니다.\n비밀번호를 입력해주세요");
		return false;
	}
	if(pass2.length == 0) {		
		alert("비밀번호 확인이 입력되지 않았습니다.\n비밀번호 확인을 입력해주세요");
		return false;
	}
	if(pass1 != pass2) {
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
		return false;
	}	
	if(nickname.length == 0) {		
		alert("닉네임이 입력되지 않았습니다.\n닉네임을 입력해주세요");
		return false;
	}	
	if(isNicknameCheck == 'false') {		
		alert("닉네임 중복 체크를 하지 않았습니다.\n닉네임 중복 체크를 해주세요");
		return false;
	}
	if(name.length == 0) {		
		alert("이름이 입력되지 않았습니다.\n이름을 입력해주세요");
		return false;
	}
	if(gender.length == 0) {
		alert("성별을 선택해주세요");
		return false;
	}
	if(phone2.length == 0 || phone3.length == 0) {		
		alert("휴대폰 번호가 입력되지 않았습니다.\n휴대폰 번호를 입력해주세요");
		return false;
	}
	if(emailId.length == 0) {		
		alert("이메일 아이디가 입력되지 않았습니다.\n이메일 아이디를 입력해주세요");
		return false;
	}	
	if(emailDomain.length == 0) {		
		alert("이메일 도메인이 입력되지 않았습니다.\n이메일 도메인을 입력해주세요");
		return false;
	}
	if(birth1.length == 0 || birth3.length == 0) {		
		alert("생년월일이 입력되지 않았습니다.\n생년월일을 입력해주세요");
		return false;
	}
}