package com.project.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeWriteFormService implements CommandProcess {

	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException{
		return "notice/noticeWriteForm";
	}
}
