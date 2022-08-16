package com.project.service;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ReviewDao;
import com.project.vo.Review;

//게시 글 리스트 보기 요청을 처리하는 서비스 클래스
public class ReviewListService  implements CommandProcess {
	
	// 한 페이지에 보여 줄 게시 글의 수를 상수로 선언하고 있다.
	private static final int PAGE_SIZE = 5;
	
	/* 한 페이지에 보여 질 페이지 그룹의 수를 상수로 선언하고 있다.
	 * [이전] 1 2 3 4 5 6 7 8 9 10 [다음]	
	 **/
	private static final int PAGE_GROUP = 10;
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException {
		
		// 요청 파라미터로 넘어 온 페이지 번호를 읽어온다.
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");	
		String keyword = request.getParameter("keyword");	
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		
		int endRow = startRow + PAGE_SIZE - 1;
				
		int listCount = 0;
		ArrayList<Review> reviewList = null;
		
		ReviewDao dao = new ReviewDao();
		
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 
		
		
		if(! searchOption) {

			listCount = dao.getReviewCount();
			
			reviewList = dao.ReviewList(startRow, endRow);
			
		} else {
			
			listCount = dao.getReviewCount(type, keyword);
			
			reviewList = dao.sqlReviewList(type, keyword, startRow, endRow);
		}
		System.out.println("listCount : " + listCount);

		int pageCount = listCount / PAGE_SIZE 
							+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		 int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
			- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
				
		// 현재 페이지 그룹의 마지막 페이지 : 10, 20, 30...
		int endPage = startPage + PAGE_GROUP - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}	

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageGroup", PAGE_GROUP);
		request.setAttribute("listCount", listCount);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("searchOption", searchOption);
		
		// 검색 요청이면 type과 keyword를 request 영역에 저장한다.
		if(searchOption) {			
			request.setAttribute("type", type);			
			request.setAttribute("keyword", keyword);
		}

		return "review/reivewList";		
	}
}
