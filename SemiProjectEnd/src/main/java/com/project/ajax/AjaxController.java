package com.project.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.ajax.AjaxProcess;
import com.project.ajax.PassCheckAction;

// ajax 요청을 처리하는 Controller 클래스 
@WebServlet(name="ajaxController", urlPatterns="*.ajax")
public class AjaxController extends HttpServlet {
	
	 
	// doGet, doPost 둘다 처리
	public void doAjax(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		AjaxProcess ajaxAction = null;
		
		String command = request.getRequestURI();
		
		command = command.substring(request.getContextPath().length());
		System.out.println("command : " + command);
		
		if(command.equals("/passCheck.ajax")) {
			
			ajaxAction = new PassCheckAction();
			ajaxAction.ajaxProcess(request, response);
				
		} else if(command.equals("/replyWrite.ajax")) {
			ajaxAction = new ReplyWriteAction();
			ajaxAction.ajaxProcess(request, response);
		}
	} // AjaxController
		
	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		doAjax(request, response);
	}
	
	@Override
	public void doPost(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		doAjax(request, response);
	}
}
