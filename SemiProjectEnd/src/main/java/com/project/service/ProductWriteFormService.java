package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductWriteFormService implements CommandProcess {

	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		boolean isLogin = session.getAttribute("isLogin") != null ? 
				(Boolean) session.getAttribute("isLogin") : false;
		
		
		if(! isLogin) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			
			out.println("<script>");
			out.println("	alert('회원 전용 서비스입니다.\\n회원 로그인을 해주세요');");
			out.println("	location.href='loginForm.mvc';");
			out.println("</script>");
			return null;
			}			
			
		
		return "product/writeForm";
	}

}
