package com.project.service;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CcDao;
import com.project.vo.Cc;

public class CcListService  implements CommandProcess {
	
	private static final int PAGE_SIZE = 5;
	
	private static final int PAGE_GROUP = 10;
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException {
		
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");	
		String keyword = request.getParameter("keyword");	
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		// 요청 파라미터의 pageNum을 int 형으로 변환하여 현재 페이지로 설정하고 있다.
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		
		int endRow = startRow + PAGE_SIZE - 1;
				
		int listCount = 0;
		ArrayList<Cc> ccList = null;
		
		CcDao dao = new CcDao();
		
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 
		
		// 검색 요청이 아니면
		if(! searchOption) {
			// 전체 게시 글 수를 구한다.
			listCount = dao.getCsCount();
			
			// 현재 페이지에 해당 하는 게시 글 리스트를 DB로부터 읽어온다.
			ccList = dao.ccList(startRow, endRow);
			
		} else {
			// 검색어에 해당하는 게시 글 수를 구한다.
			listCount = dao.getCcCount(type, keyword);
			
			// 검색 결과에 대한 현재 페이지에 해당 하는 게시 글 리스트를 DB로부터 읽어온다.
			ccList = dao.searchList(type, keyword, startRow, endRow);
		}
		System.out.println("listCount : " + listCount);
		
		int pageCount = listCount / PAGE_SIZE 
							+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		 int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
			- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
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
		request.setAttribute("ccList", ccList);
		request.setAttribute("searchOption", searchOption);
		
		// 검색 요청이면 type과 keyword를 request 영역에 저장한다.
		if(searchOption) {			
			request.setAttribute("type", type);			
			request.setAttribute("keyword", keyword);
		}

		// return "f:/WEB-IN/member/overlapidCheck.jsp";
		return "cc/ccList";		
	}
}
