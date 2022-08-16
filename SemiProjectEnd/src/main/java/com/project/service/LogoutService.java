package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutService implements CommandProcess {
	
	@Override
	   public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			   throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
	
		/*
		 * session.removeAttribute("isLogin"); session.removeAttribute("m_id");
		 */
		
		return "r:productList.mvc";
	}
}
