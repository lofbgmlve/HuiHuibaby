package com.project.service;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ProductDao;

public class DeleteService implements CommandProcess{

	public String requestProcess(
		HttpServletRequest request, HttpServletResponse response)
			throws ServletException , IOException {
		
		String sNo = request.getParameter("no");
		String type= request.getParameter("type");
		String keyword= request.getParameter("keyword");
		String pageNum =request.getParameter("pageNum");
		
		ProductDao  dao = new ProductDao();
		int no = Integer.parseInt(sNo);
		// 객체생성 - 삭제 ~~ 
		dao.deleteProduct(no);
		
		boolean searchOption =(type==null || type.equals("")||
				keyword==null || keyword.equals(""))  ? false : true ;
				
		String url = "productList.mvc?pageNum="+pageNum;
		
		if(searchOption){
			
			keyword = URLEncoder.encode(keyword,"utf-8");
			url +="&type"+type+"&keyword"+keyword;
		}
		System.out.println("keyword  - "  + keyword);
		System.out.println("url  - "  + url);
		
		
		
		
		return "r:" + url; 
	}
		
	
}
