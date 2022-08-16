package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.dao.CommunityDao;
import com.project.vo.Community;

//커뮤니티 글쓰기 요청을 처리하는 서비스 클래스
public class CommunityWriteService implements CommandProcess {

	public String requestProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String uploadDir =
				(String) request.getServletContext().getAttribute("uploadDir");
				String realPath = request.getServletContext().getRealPath(uploadDir);
				
		int maxFileSize = 100 * 1024 * 1024;
		
		String encoding = "UTF-8";
	
		//2. 파일 업로드를 처리할 MultipartRequest 객체 생성
		MultipartRequest multi = new MultipartRequest(request, realPath,
				maxFileSize, encoding, new DefaultFileRenamePolicy());
		
		String subject = multi.getParameter("c_subject");
		String content = multi.getParameter("c_content");		
		String nickname = multi.getParameter("m_nickname");
		
		String image = multi.getParameter("c_image");
		
		String fileName = multi.getFilesystemName("c_image");
		String originName = multi.getOriginalFileName("c_image");
		System.out.println("fileName : " + fileName + ", originName : " + originName);
		
		
		Community community = new Community();
		community.setCommunitySubject(subject);
		community.setCommunityContent(content);
		community.setCommunityImage(fileName);
		community.setNickName(nickname);
		
		CommunityDao dao = new CommunityDao();
		dao.insertCommunity(community);
		
		return "r:communityList.mvc";
	}
	
}
