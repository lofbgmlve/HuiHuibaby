package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.dao.CommunityDao;
import com.project.dao.ReviewDao;
import com.project.vo.Community;
import com.project.vo.Review;

public class ReviewUpdateService implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
			
				String contentType = request.getHeader("Content-Type");
				System.out.println("contentType : " + contentType);
				ReviewDao dao = new ReviewDao();
				Review review = null;
				String rSubject = null, rnickName = null, rContent = null,
						rNo = null, pageNum = null, type=null, keyword=null;
				int no = 0;
				
			if(contentType.contains("multipart/form-data")) {
				
				String uploadDir =
						(String) request.getServletContext().getAttribute("uploadDir");
				String realPath = request.getServletContext().getRealPath(uploadDir);
				
				int maxImageSize = 100 * 1024 * 1024;
				
				String encoding = "UTF-8";
			
				MultipartRequest multi = new MultipartRequest(request, realPath,
											maxImageSize, encoding, new DefaultFileRenamePolicy());
				
				rNo = multi.getParameter("r_no");
				pageNum = multi.getParameter("pageNum");
				type = multi.getParameter("type");
				keyword = multi.getParameter("keyword");
				
			if(rNo == null || rNo.equals("") || pageNum == null || pageNum.equals("")) {
				
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println(" alert('정상적인 접근이 아닙니다.');");
				out.println(" history.back();");
				out.println("</script>");
				return null;
			}
				
				rSubject = multi.getParameter("r_subject");
				rnickName = multi.getParameter("r_nickname");
				rContent = multi.getParameter("r_content");
				
				review = new Review();
				review.setrNo(no);
				review.setrSubject(rSubject);
				review.setrNickname(rnickName);
				review.setrContent(rContent);
				
				String imageName = multi.getFilesystemName("image");
				System.out.println("업로드 된 이미지 : " + imageName);
				System.out.println("원본 이미지 : " + multi.getOriginalFileName("image"));
				
				review.setrImage(imageName != null ? imageName : null);
				if(review.getrImage() == null) {
				System.out.println("이미지가 업로드 되지 않았음");
				}
			
				} else {
				request.setCharacterEncoding("utf-8");
				rNo = request.getParameter("r_no");
				pageNum = request.getParameter("pageNum");
				type = request.getParameter("type");
				keyword = request.getParameter("keyword");
				
				if(rNo == null || rNo.equals("") || pageNum == null || pageNum.equals("")) {
				
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println(" alert('정상적인 접근이 아닙니다.');");
				out.println(" history.back();");
				out.println("</script>");
				return null;
				}
				
				rSubject = request.getParameter("c_subject");
				rnickName = request.getParameter("m_nickname");
				rContent = request.getParameter("c_content");
				
				review = new Review();
				review.setrNo(no);
				review.setrSubject(rSubject);
				review.setrNickname(rnickName);
				review.setrContent(rContent);
				}
				
				dao.updateReview(review);
				
				boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals("")) ? false : true;
			
				String url = "reviewList.bbs?pageNum=" + pageNum;
			
				if(searchOption) {
			
				keyword = URLEncoder.encode(keyword, "utf-8");
				url += "&type=" + type + "&keyword=" + keyword;
				}
				System.out.println("keyword : " + keyword);
				System.out.println("url : " + url);
				
				return "r:" + url;
				}
}
