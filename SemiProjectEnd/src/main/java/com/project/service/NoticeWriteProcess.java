package com.project.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.NoticeDao;
import com.project.vo.Notice;

public class NoticeWriteProcess implements CommandProcess {

	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException{
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setNt_subject(subject);
		notice.setNt_content(content);
		
		NoticeDao noticeDao = new NoticeDao();
		noticeDao.insertNotice(notice);
		return "r:noticeList.mvc";		
	}
	
}
