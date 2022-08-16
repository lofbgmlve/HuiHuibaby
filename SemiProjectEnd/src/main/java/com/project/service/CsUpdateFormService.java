package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CsDao;
import com.project.vo.Cs;

// 게시 글 수정 폼 요청을 처리하는 모델 클래스
public class CsUpdateFormService  implements CommandProcess {
	
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
		
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 	
		
		Cs cs = dao.getCs(Integer.valueOf(cs_no), false);
		
		// 게시글 정보와 페이지 정보를 Request 속성 영역에 저장 한다.
		request.setAttribute("cs", cs);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);

		// 검색 요청이면 type과 keyword를 request 영역에 저장한다.
		if(searchOption) {			
			request.setAttribute("type", type);			
			request.setAttribute("keyword", keyword);
		}		
		
		return "cs/updateForm";
	}
}