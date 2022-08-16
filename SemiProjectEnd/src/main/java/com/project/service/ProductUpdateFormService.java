package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ProductDao;
import com.project.vo.Product;

public class ProductUpdateFormService implements CommandProcess{
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
		
		String no=request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		String type=request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		boolean searchOption =(type==null|| type.equals("")||
				keyword==null||keyword.equals(""))  ? false  : true ;

		ProductDao dao = new ProductDao();
	    Product product = dao.getProduct(Integer.valueOf(no));
		 
		request.setAttribute("product",product);
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("searchOption",searchOption);
		
		if(searchOption) {
			request.setAttribute("type",type);
			request.setAttribute("keyword",keyword);
		}
		
		
		
		
		return "product/updateForm";
	}
	
	
}
