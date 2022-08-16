package com.project.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ProductDao;
import com.project.vo.Product;

public class ProductPartListService implements CommandProcess {

	
	private static final int PAGE_SIZE=4;
	private static final int PAGE_GROUP=5;
	
	@Override
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		
		String part = request.getParameter("p_part");
		String pageNum =request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword= request.getParameter("keyword");
		
		
		if(pageNum== null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = currentPage *PAGE_SIZE -(PAGE_SIZE -1);
		int endRow = startRow + PAGE_SIZE - 1;
		
		
		
		int listCount = 0; 
		ArrayList<Product>pList = null;
		ProductDao dao = new ProductDao();
		
		/* ArrayList<Product>productList = dao.productList(part); */
		
		
		
		
		
		boolean searchOption =(type == null|| type.equals("")
				||keyword==null || keyword.equals("")) ? false : true ;
		
		if(! searchOption) {
			listCount = dao.getProductCount(part);
			pList = dao.productList(part, startRow, endRow);
		
		}else {
			listCount = dao.getProductCount(type, keyword);
			pList = dao.searchList(type, keyword, startRow, endRow);
		}
		
		System.out.println("partListCount --- "  + listCount);
		
		int pageCount = listCount / PAGE_SIZE + (listCount %PAGE_SIZE == 0 ? 0 : 1);
		
		int startPage = (currentPage / PAGE_GROUP)* PAGE_GROUP +1  -
				(currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		int endPage = startPage + PAGE_GROUP -1 ; 
		
		if( endPage > pageCount) {
			endPage = pageCount;
		}		
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageGroup", PAGE_GROUP);
		request.setAttribute("listCount", listCount);
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("part",part);
		request.setAttribute("pList", pList);
		
		if(searchOption ) {
			request.setAttribute("type",type);
			request.setAttribute("keyword",keyword);
		}
		
		return "product/productList";
		
		
	}

}
