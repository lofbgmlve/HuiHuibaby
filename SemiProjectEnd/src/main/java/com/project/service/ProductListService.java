package com.project.service;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ProductDao;
import com.project.vo.Product;

public class ProductListService implements CommandProcess{

	
	private static final int PAGE_SIZE = 4;
	
	private static final int PAGE_GROUP = 5;
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response) 
		throws ServletException{
		
			String pageNum =request.getParameter("pageNum");
			String type = request.getParameter("type");
			String keyword= request.getParameter("keyword");
			
		
			if(pageNum == null) {
				pageNum="1";
			}
			
			int currentPage = Integer.parseInt(pageNum);
			int startRow = currentPage *PAGE_SIZE -(PAGE_SIZE -1);
			int endRow = startRow + PAGE_SIZE - 1;
			
			
			int listCount = 0;
			ArrayList<Product>productList = null;
			
			ProductDao dao = new ProductDao();
			
			
			boolean searchOption =(type == null|| type.equals("")
					||keyword==null || keyword.equals("")) ? false : true ;
			
			
			if(! searchOption) {
				
				listCount = dao.getProductCount();
				productList = dao.productList(startRow, endRow);
				
			}else {
				
				listCount = dao.getProductCount(type, keyword);
				productList = dao.searchList(type, keyword, startRow, endRow);
			}
							
			System.out.println("listCount : "  + listCount );
			
			// 페이지 수 계싼 - 다음/ 이전 
			int pageCount = listCount / PAGE_SIZE + (listCount %PAGE_SIZE == 0 ? 0 : 1);
			
			int startPage = (currentPage / PAGE_GROUP)* PAGE_GROUP +1  -
					(currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
			
			int endPage = startPage + PAGE_GROUP -1 ; 
			
			
			if( endPage > pageCount) {
				endPage = pageCount;
			}			
			
			// 뷰페이지에서 필요한 데이터를 request 영역의 속성에 저장 . 
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("pageGroup", PAGE_GROUP);
			request.setAttribute("listCount", listCount);
			request.setAttribute("pList", productList);
			request.setAttribute("searchOption", searchOption);
			
			// 검색 요청이면 타입, 키워드를 리퀴스트에 저장한다 ! ! 
			
			if(searchOption ) {
				request.setAttribute("type",type);
				request.setAttribute("keyword",keyword);
			}
			
		return "product/productList";
	}
		
}


