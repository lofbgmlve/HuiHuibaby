package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.FaqDao;
import com.project.vo.Faq;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// 게시 글 수정 폼에서 요청한 데이터를 받아 DB에 수정하는 모델 클래스
public class FaqUpdateService implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);	
		FaqDao dao = new FaqDao();
		Faq faq = null;
		int faq_no = 0;
		String faq_title = null, 
				faq_content = null, mg_id = null, 
				sNo = null, pageNum = null, type=null, keyword=null; 

		// 요청이 multipart/form-data 일 경우
		if(contentType.contains("multipart/form-data")) {
			
			
			sNo = request.getParameter("faq_no"); 
			pageNum = request.getParameter("pageNum"); 
			type = request.getParameter("type"); 
			keyword = request.getParameter("keyword");
			
			if(sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("")) {

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("	alert('정상적인 접근이 아닙니다.');");
				out.println("	history.back();");
				out.println("</script>");
				return null;
			}
			
			faq_no = Integer.parseInt(sNo);
			
			faq_title = request.getParameter("faq_title");
			faq_content = request.getParameter("faq_content");
			mg_id = request.getParameter("mg_id");
			
			faq = new Faq();
			faq.setFaq_title(faq_title);
			faq.setFaq_content(faq_content);
			faq.setMg_id(mg_id);			
			
		// 요청이 multipart/form-data 아닌 경우	
		} else {		
			
			request.setCharacterEncoding("utf-8");
			sNo = request.getParameter("faq_no");
			pageNum = request.getParameter("pageNum"); 
			type = request.getParameter("type"); 
			keyword = request.getParameter("keyword");

			
			if(sNo == null || sNo.equals("") ||  pageNum == null || pageNum.equals("")) {

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("	alert('정상적인 접근이 아닙니다.');");
				out.println("	history.back();");
				out.println("</script>");
				return null;
			}
			
			faq_no = Integer.parseInt(sNo);
			
			faq_title = request.getParameter("faq_title");
			faq_content = request.getParameter("faq_content");
			mg_id = request.getParameter("mg_id");
			
			faq = new Faq();
			faq.setFaq_title(faq_title);
			faq.setFaq_content(faq_content);
			faq.setMg_id(mg_id);
		}
		
		dao.updateFaq(faq);

		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 
		
		String url = "faqList.mvc?pageNum=" + pageNum;

		if(searchOption) {
			
			keyword = URLEncoder.encode(keyword, "utf-8");			
			url += "&type=" + type + "&keyword=" + keyword; 
		}		
		System.out.println("keyword : " + keyword);
		System.out.println("url : " + url);		
		
		return "r:" + url;
	}
}