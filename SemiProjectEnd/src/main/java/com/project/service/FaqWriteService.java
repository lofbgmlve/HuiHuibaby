package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.FaqDao;
import com.project.vo.Faq;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// FAQ 글쓰기 요청을 처리하는 서비스 클래스
public class FaqWriteService  implements CommandProcess {
	
	public String requestProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String faq_title = request.getParameter("faq_title");
		String mg_id = request.getParameter("mg_id");
		String faq_content = request.getParameter("faq_content");
		
		/* 하나의 게시 글 정보를 저장하는 자바빈 객체를 생성하고 요청 데이터를
		 * Board 객체에 저장한다.
		 **/
		Faq faq = new Faq();
		faq.setFaq_title(faq_title);
		faq.setMg_id(mg_id);
		faq.setFaq_content(faq_content);
		
		FaqDao dao = new FaqDao();
		dao.insertFaq(faq);

		return "r:faqList.mvc";		
	}
}
