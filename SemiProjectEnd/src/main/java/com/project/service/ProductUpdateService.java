package com.project.service;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.dao.ProductDao;
import com.project.vo.Product;

public class ProductUpdateService implements CommandProcess{

	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
		
		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);
		
		ProductDao dao = new ProductDao();
		Product product = null;
		
		String title = null, name = null, pro = null, part = null, condition = null, way = null;
		String file1 = null, file2 = null, file3 = null, detail = null, nickname = null;
	
		String sNo = null, pageNum = null, type = null, keyword = null;
	
		int no = 0;
		int price = 0;
		
		
		if (contentType.contains("multipart/form-data")) {
			
			String uploadDir = (String) request.getServletContext().getAttribute("uploadDir");
			String realPath =request.getServletContext().getRealPath(uploadDir);
			int maxFileSize = 100 * 1024 * 1024;
			String encoding = "UTF-8";
		
		
			MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding,
					new DefaultFileRenamePolicy());
			
			
			sNo = multi.getParameter("no");
			pageNum = multi.getParameter("pageNum");
			type=multi.getParameter("type");
			keyword=multi.getParameter("keyword");
			
			title = multi.getParameter("title");
			name = multi.getParameter("name");
			pro = multi.getParameter("pro");
			part = multi.getParameter("part");
			condition = multi.getParameter("condition");
			way = multi.getParameter("way");
			price = Integer.parseInt(multi.getParameter("price"));
			file1 = multi.getFilesystemName("file1");
			file2 = multi.getFilesystemName("file2");
			file3 = multi.getFilesystemName("file3");
			detail = multi.getParameter("detail");
			nickname = multi.getParameter("nickname");
			
			
			// 게시글 정보 - product 객체 생각 - 파라미터값 저장 
			product = new Product();
			product.setNo(Integer.parseInt(sNo));
			product.setTitle(title);
			product.setName(name);
			product.setPro(pro);
			product.setPart(part);
			product.setCondition(condition);
			product.setWay(way);
			product.setPrice(price);
			product.setFile1(file1);
			product.setFile2(file2);
			product.setFile3(file3);
			product.setDetail(detail);
			product.setNickname(nickname);
		
			
			//MultipartRequest  - 객체 	
			String fileName = multi.getFilesystemName("file1");
			System.out.println("업로드 된 파일명 : " + fileName);
			System.out.println("원본 파일명 : " + multi.getOriginalFileName("file1"));
			product.setFile1(fileName);
			
			if (product.getFile1() == null) {
				System.out.println("파일이 업로드 되지 않았음");	
			}
		}
			// product 객체르 파라미터 값받은 걸 ! update  ! 
			dao.updateProduct(product);
			
			// 검색일 때 ! 
			boolean searchOption = (type == null || type.equals("") || keyword == null || keyword.equals("")) ? false : true;
			String url = "productList.mvc?pageNum="+pageNum;
			
			if (searchOption) {
				keyword = URLEncoder.encode(keyword, "utf-8");
				url += "&type=" + type + "&keyword=" + keyword;
			}
			System.out.println("keyword : " + keyword);
			System.out.println("url : " + url);
			
			
			
	
		return "r:"+url;
	}
		
}
