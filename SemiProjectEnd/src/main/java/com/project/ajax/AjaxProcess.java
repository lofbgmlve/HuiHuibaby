package com.project.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 슈퍼 인터페이스
public interface AjaxProcess {
	
	public void ajaxProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException;
}
