$(function(){
	
	$("#check").click(function(){
		if($("#check").prop("checked")) {
			$("input[type=checkbox]").prop("checked", true);
			
		}else{
			$("input[type=checkbox]").prop("checked", false);
		}
	});
	$("#btnDelete").click(function(){
		
	});

});