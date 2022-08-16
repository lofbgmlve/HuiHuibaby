package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.ProductDao;
import com.project.vo.Product;
import com.project.vo.Reply;

public class ProductDetailService implements CommandProcess {
			
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
		
		// HttpSession 객체를 구하고 세션 영역의 속성ㅇ
		HttpSession session = request.getSession();
		 
		 boolean isLogin = session.getAttribute("isLogin") != null ?
				 	(Boolean)session.getAttribute("isLogin") : false;
		
		 
		 if(! isLogin) { response.setContentType("text/html; charset=utf-8");
		 PrintWriter out = response.getWriter();
		 out.println("<script>");
		  out.println("alert('회원 전용 서비스입니다.\\n 회원 로그인을 해주세요.')");
		  out.println("location.href='loginForm.mvc'");
		  out.println("</script>");
		  return null; 
		 
		 }
		
		
		
		String no = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		String type= request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		// 스트림에 직접쓰기 위해서 응답객체(response) 로 부터 스트림을 구해야한다. 
		// 응답객체는 스트림을 구하기 위해 ContentType을 설정해야한다. 
		
		 if (no == null || no.equals("") || pageNum == null || pageNum.equals("")) {
			 response.setContentType("text/html; charset=utf-8");
			 PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.')");
			out.println("history.back();");
			out.println("</script>");
			
			return null; 
		 }  // no , pageNum 
		
		 // 검색요청일떄 true 아닐떄 false
		boolean searchOption = (type==null || type.equals("")
	 				||keyword == null || keyword.equals("")) ? false : true ;
		 
		 
		ProductDao dao = new ProductDao();
	    Product product = dao.getProduct(Integer.valueOf(no));
	    
	    // 댓글 !! 
	    ArrayList<Reply> replyList =dao.getReplyList(Integer.valueOf(no));
	    
	    
		 //  view 페이지에서 필요한 request영역의 속성에 저장
	    // 게시글 하나의 내용, 현재페이지 번호를 속성에 저장! 
		request.setAttribute("product", product);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("replyList",replyList);
		
		if(searchOption) {
			request.setAttribute("type",type);
			request.setAttribute("keyword",keyword);
		}
	    
	  
		return "product/productDetail"; 
	}// requestProcess
	
}
