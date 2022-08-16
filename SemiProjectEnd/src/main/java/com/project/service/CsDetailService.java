package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CsDao;
import com.project.vo.Cs;

// 게시 글 상세보기 요청을 처리하는 서비스 클래스
public class CsDetailService implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		
		String cs_no = request.getParameter("cs_no");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		// no와 pageNum이 비어 있으면 비정상 요청
		if(cs_no == null || cs_no.equals("") || pageNum == null || pageNum.equals("")) {
					
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
					
			out.println("<script>");
			out.println("	alert('정상적인 접근이 아닙니다.');");
			out.println("	history.back();");
			out.println("</script>");
					
			return null;
		}
		
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 
		
		CsDao dao = new CsDao();
		Cs cs = dao.getCs(Integer.valueOf(cs_no), true);
		
		request.setAttribute("cs", cs);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		
		return "cs/csDetail";
	}
}
