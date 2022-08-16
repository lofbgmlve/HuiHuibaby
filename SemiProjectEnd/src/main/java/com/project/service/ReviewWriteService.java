package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.dao.ReviewDao;
import com.project.vo.Review;

public class ReviewWriteService implements CommandProcess {

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
			
			String subject = multi.getParameter("r_subject");
			String content = multi.getParameter("r_content");
			String image = multi.getParameter("r_image");
			String nickname = multi.getParameter("r_nickname");
			
			Review review = new Review();
			review.setrSubject(subject);
			review.setrContent(content);
			review.setrImage(image);
			review.setrNickname(nickname);
			
			ReviewDao dao = new ReviewDao();
			dao.insertReview(review);
			
			return "r:reviewList.bbs";
		}
		
}
