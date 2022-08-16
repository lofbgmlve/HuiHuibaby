package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CommunityDao;
import com.project.dao.ReviewDao;
import com.project.vo.Community;
import com.project.vo.Review;

// 수정 폼 요청을 처리하는 모델 클래스
public class ReviewUpdateFormService implements CommandProcess {
	   
	 public String requestProcess(
	       HttpServletRequest request, HttpServletResponse response) 
	             throws ServletException, IOException {   
	      
	    String rNo = request.getParameter("r_no");
	    String pageNum = request.getParameter("pageNum");
	    String type = request.getParameter("type");   
	    String keyword = request.getParameter("keyword");

	    if(rNo == null || rNo.equals("") ||  pageNum == null || pageNum.equals("")) {

	       response.setContentType("text/html; charset=utf-8");
	       PrintWriter out = response.getWriter();
	       out.println("<script>");
	       out.println("   alert('정상적인 접근이 아닙니다.');");
	       out.println("   history.back();");
	       out.println("</script>");
	       return null;
	    }
	      
	    ReviewDao dao = new ReviewDao();
	    int r_no = Integer.parseInt(rNo);
	      
	    boolean searchOption = (type == null || type.equals("") 
	          || keyword == null || keyword.equals("")) ? false : true;    
	      
	    Review review = dao.getReview(Integer.parseInt(rNo));
	     
	      
	      
	    // 게시글 정보와 페이지 정보를 Request 속성 영역에 저장 한다.
	    request.setAttribute("review", review);
	    request.setAttribute("pageNum", pageNum);
	    request.setAttribute("searchOption", searchOption);

	    // 검색 요청이면 type과 keyword를 request 영역에 저장한다.
	    if(searchOption) {         
	       request.setAttribute("type", type);         
	       request.setAttribute("keyword", keyword);
	    }      
	      
	    return "review/reviewForm";
	 }
}
