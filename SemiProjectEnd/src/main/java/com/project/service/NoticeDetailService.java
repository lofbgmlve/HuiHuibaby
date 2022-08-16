package com.project.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.NoticeDao;
import com.project.vo.Notice;

public class NoticeDetailService implements CommandProcess {

	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException{ 
		
		String no = request.getParameter("no");
		
		
		NoticeDao dao = new NoticeDao();
		Notice notice = dao.getNotice(Integer.valueOf(no), true);
		
		request.setAttribute("notice", notice);
		return "notice/noticeDetail";
	}

}
