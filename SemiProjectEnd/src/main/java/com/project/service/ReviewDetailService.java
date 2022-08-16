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

public class ReviewDetailService implements CommandProcess {

	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String rNo = request.getParameter("r_no");
			String pageNum = request.getParameter("pageNum");
			String type = request.getParameter("type");
			String keyword = request.getParameter("keyword");
			
			if(rNo == null || rNo.equals("") || pageNum == null || pageNum.equals("")) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('정상적인 접근이 아닙니다.');");
			out.println(" history.back();");
			out.println("</script>");
		
			return null;
			}
			
			boolean searchOption = (type == null || type.equals("")
			|| keyword == null || keyword.equals("")) ? false : true;
			
			ReviewDao dao = new ReviewDao();
			Review review = dao.getReview(Integer.valueOf(rNo));
			
			request.setAttribute("review",review);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("searchOption", searchOption);
			
			if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
			}
		
			return "review/reviewDetail";
			}

}
