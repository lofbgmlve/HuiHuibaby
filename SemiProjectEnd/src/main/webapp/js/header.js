	$(document).ready(function(){
		$(".menu>li").mouseover(function(){
			$(this).find(".sub").stop().slideDown(500);
		}).mouseout(function(){
			$(this).find(".sub").stop().slideUp(500);
		});
	});