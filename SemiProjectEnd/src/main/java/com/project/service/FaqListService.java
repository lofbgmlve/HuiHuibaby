package com.project.service;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.FaqDao;
import com.project.vo.Faq;

public class FaqListService  implements CommandProcess {
	
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
		
		int currentPage = Integer.parseInt(pageNum);
		// 요청한 페이지에 해당하는 게시 글의 첫 번째 행의 값을 계산한다.
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		int endRow = startRow + PAGE_SIZE - 1;
		int listCount = 0;

		ArrayList<Faq> faqList = null;
		
		FaqDao dao = new FaqDao();
		
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 
		
		if(! searchOption) {
			listCount = dao.getFaqCount();
			faqList = dao.faqList(startRow, endRow);
			
		} else {
			listCount = dao.getFaqCount(type, keyword);
			faqList = dao.searchList(type, keyword, startRow, endRow);
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
		request.setAttribute("fList", faqList);
//		request.setAttribute("searchOption", true);
		request.setAttribute("searchOption", true);
		System.out.println("listCount" + listCount);
		System.out.println(searchOption);
		if(searchOption) {			
			request.setAttribute("type", type);			
			request.setAttribute("keyword", keyword);
		}

		return "faq/faqList";		
	}
}
