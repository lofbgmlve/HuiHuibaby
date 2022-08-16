package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CsDao;
import com.project.vo.Cs;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// 게시 글 수정 폼에서 요청한 데이터를 받아 DB에 수정하는 모델 클래스
public class CsUpdateService  implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);	
		CsDao dao = new CsDao();
		Cs cs = null;
		String cs_subject = null, cs_content = null, cs_image = null, cs_date = null, m_nickname=null,
				sNo = null, pageNum = null, type=null, keyword=null;
		int cs_no = 0;		

		// 요청이 multipart/form-data 일 경우
		if(contentType.contains("multipart/form-data")) {
			
			
			String uploadDir = 
					(String) request.getServletContext().getAttribute("uploadDir");
			String realPath = request.getServletContext().getRealPath(uploadDir);
			
			// 업로드 파일의 최대 크기를 100MB로 지정
			int maxFileSize = 100 * 1024 * 1024;
			
			// 파일의 인코딩 타입을 UTF-8로 지정
			String encoding = "UTF-8"; 
				
			MultipartRequest multi = new MultipartRequest(request, realPath, 
								maxFileSize, encoding, new DefaultFileRenamePolicy());	
			
			sNo = multi.getParameter("cs_no");		
			pageNum = multi.getParameter("pageNum");
			type = multi.getParameter("type");
			keyword = multi.getParameter("keyword");		
			
			if(sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("")) {

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("	alert('정상적인 접근이 아닙니다.');");
				out.println("	history.back();");
				out.println("</script>");
				return null;
			}
			
			cs_no = Integer.parseInt(sNo);
			
			cs_subject = multi.getParameter("cs_subject");
			cs_content = multi.getParameter("cs_content");		
			cs_image = multi.getParameter("cs_image");
			cs_date = multi.getParameter("cs_date");	
			m_nickname = multi.getParameter("m_nickname");		
			
			cs = new Cs();
			cs.setCs_no(cs_no);
			cs.setCs_subject(cs_subject);
			cs.setCs_content(cs_content);	
			cs.setCs_image(cs_image);
			cs.setM_nickname(m_nickname);
				
			String fileName = multi.getFilesystemName("cs_image");
			System.out.println("업로드 된 파일명 : " + fileName);
			System.out.println("원본 파일명 : " + multi.getOriginalFileName("cs_image"));
			
			// 파일명이 존재하면 파일명을 지정하고 존재하지 않으면 null로 지정 한다.	 
			cs.setCs_image(fileName != null ? fileName : null);
			
			if(cs.getCs_image() == null) {		
				System.out.println("파일이 업로드 되지 않았음");		
			}
			
		// 요청이 multipart/form-data 아닌 경우	
		} else {		
			
			request.setCharacterEncoding("utf-8");
			sNo = request.getParameter("cs_no");		
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
			
			cs_no = Integer.parseInt(sNo);
			
			
			cs_subject = request.getParameter("cs_subject");
			cs_content = request.getParameter("cs_content");
			cs_image = request.getParameter("cs_image");
			cs_date = request.getParameter("cs_date");
			m_nickname = request.getParameter("m_nickname");
			
			cs = new Cs();
			cs.setCs_no(cs_no);
			cs.setCs_subject(cs_subject);
			cs.setCs_content(cs_content);	
			cs.setCs_image(cs_image);
			cs.setM_nickname(m_nickname);
		}
		
		dao.updateCs(cs);

		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 	
		
		String url = "csList.mvc?pageNum=" + pageNum;

		if(searchOption) {
			
			keyword = URLEncoder.encode(keyword, "utf-8");			
			url += "&type=" + type + "&keyword=" + keyword; 
		}		
		System.out.println("keyword : " + keyword);
		System.out.println("url : " + url);		
		
		return "r:" + url;
	}
}