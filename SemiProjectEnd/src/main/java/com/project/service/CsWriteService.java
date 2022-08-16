package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CsDao;
import com.project.vo.Cs;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// CS 글쓰기 요청을 처리하는 서비스 클래스
public class CsWriteService  implements CommandProcess {
	
	public String requestProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		String uploadDir = 
				(String) request.getServletContext().getAttribute("uploadDir");
		String realPath = request.getServletContext().getRealPath(uploadDir);		
		
		int maxFileSize = 100 * 1024 * 1024;
		
		String encoding = "UTF-8"; 
			
		MultipartRequest multi = new MultipartRequest(request, realPath, 
							maxFileSize, encoding, new DefaultFileRenamePolicy());	
		
		String cs_subject = multi.getParameter("cs_subject");
		String m_nickname = multi.getParameter("m_nickname");
		String cs_content = multi.getParameter("cs_content");
		
		Cs cs = new Cs();
		cs.setCs_subject(cs_subject);
		cs.setM_nickname(m_nickname);
		cs.setCs_content(cs_content);
			
		String fileName = multi.getFilesystemName("cs_image");
		System.out.println("업로드 된 파일명 : " + fileName);
		System.out.println("원본 파일명 : " + multi.getOriginalFileName("cs_image"));
		
		// 파일명이 존재하면 파일명을 지정하고 존재하지 않으면 null로 지정 한다.	 
		cs.setCs_image(fileName != null ? fileName : null);
		
		if(cs.getCs_image() == null) {		
			System.out.println("파일이 업로드 되지 않았음");		
		}	
		
		CsDao dao = new CsDao();
		dao.insertCs(cs);

		return "r:csList.mvc";		
	}
}
