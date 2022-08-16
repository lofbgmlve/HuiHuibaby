package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 게시 글쓰기 폼 요청을 처리하는 모델 클래스
public class CcWriteFormService  implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		boolean isLogin = session.getAttribute("isLogin") != null ? 
				(Boolean) session.getAttribute("isLogin") : false;
		
		// 로그인 상태가 아니면 알림 창을 띄우고 회원 로그인 폼으로 보낸다.
		if(! isLogin) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("	alert('회원 전용 서비스입니다.\\n회원 로그인을 해주세요');");
			out.println("	location.href='loginForm.mvc';");
			out.println("</script>");
			
			return null;
		}		
		
		return "cc/writeForm";
	}
}