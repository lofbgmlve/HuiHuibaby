package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.FaqDao;
import com.project.vo.Faq;

// 게시 글 상세보기 요청을 처리하는 서비스 클래스
public class FaqDetailService implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		
		String faq_no = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		System.out.println();
		// no와 pageNum이 비어 있으면 비정상 요청
		if(faq_no == null || faq_no.equals("") || pageNum == null || pageNum.equals("")) {
					
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
		
		FaqDao dao = new FaqDao();
		Faq faq = dao.getFaq(Integer.valueOf(faq_no), true);
		
		request.setAttribute("faq", faq);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		
		return "faq/faqDetail";
	}
}
