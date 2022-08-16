package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CcDao;
import com.project.vo.Cc;

// 게시 글 상세보기 요청을 처리하는 서비스 클래스
public class CcDetailService implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		
		String col5 = request.getParameter("col5");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		// no와 pageNum이 비어 있으면 비정상 요청
		if(col5 == null || col5.equals("") || pageNum == null || pageNum.equals("")) {
					
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
		
		CcDao dao = new CcDao();
		Cc cc = dao.getCs(Integer.valueOf(col5), true);
		
		request.setAttribute("cc", cc);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		
		return "cc/ccDetail";
	}
}
