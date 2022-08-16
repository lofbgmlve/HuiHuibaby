package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CsDao;

// 게시 글 삭제 요청을 받아 DB에서 게시 글을 삭제하는 모델 클래스
public class CsDeleteService  implements CommandProcess {
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {	
		
		String sNo = request.getParameter("cs_no");
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("")) {

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("	alert('정상적인 접근이 아닙니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
		CsDao dao = new CsDao();
		int cs_no = Integer.parseInt(sNo);
				
		// CsDao 객체를 이용해 게시 글을 삭제한다.
		dao.deleteCs(cs_no);	
		
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 	
		
		String url = "csList.mvc?pageNum=" + pageNum;

		if(searchOption) {
			
			keyword = URLEncoder.encode(keyword, "utf-8");			
			url += "&type=" + type + "&keyword=" + keyword; 
		}

		return "r:" + url;
	}
}
