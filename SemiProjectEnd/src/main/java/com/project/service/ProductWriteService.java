package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.project.dao.ProductDao;
import com.project.vo.Product;

public class ProductWriteService implements CommandProcess {

	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//cos라이브러리를 이용한 파일 업로드 ! 
		// MultipartRequest의 생성자 매개변수에 지정할 데이터설정 하기 
		// ServletContext 객체의 속성에 저장된 파일을 업로드할 디렉터리 정보를 읽어 - 시스템의 로컬 경로 구하기
		String uploadDir = (String) request.getServletContext().getAttribute("uploadDir");
		String realPath = request.getServletContext().getRealPath(uploadDir);
		int maxFileSize = 100 * 1024 * 1024;
		String encoding = "utf-8";

		MultipartRequest multi = new MultipartRequest(request, realPath, maxFileSize, encoding,
				new DefaultFileRenamePolicy());

		
		// 사용자가 폼에 입력한 데이터를 - MultipartRequest 객체를 통해서 파라미터를 읽어 변수에 저장.
		String name = multi.getParameter("name");
		String title = multi.getParameter("title");
		String part = multi.getParameter("part");
		String way = multi.getParameter("way");
		String staus = multi.getParameter("staus");
		int price = Integer.parseInt(multi.getParameter("price"));
		String pro = multi.getParameter("pro");
		String condition = multi.getParameter("condition");
		String file1 = multi.getFilesystemName("file1");
		String file2 = multi.getFilesystemName("file2");
		String file3 = multi.getFilesystemName("file3");
		String detail = multi.getParameter("detail");
		//String nickname = multi.getParameter("nickname");
		
		System.out.println("price : " +  price );
		// 글 정보를 저장하는 객체를 생성하고 요청 데이터를 Product 객체에 저장하기. 
		Product product = new Product();

		product.setName(name);
		product.setTitle(title);
		product.setPart(part);
		product.setWay(way);
		product.setStaus(staus);
		product.setPrice(price);
		product.setPro(pro);
		product.setCondition(condition);
		product.setFile1(file1);
		product.setFile2(file2);
		product.setFile3(file3);
		product.setDetail(detail);
		
		HttpSession session = request.getSession();
		String mNickname = (String) session.getAttribute("mNickname");		
		product.setNickname(mNickname);

		String fileName = multi.getFilesystemName("file1");
		System.out.println("업로드된 파일명 : " + fileName);
		System.out.println("원본 파일명 : " + multi.getOriginalFileName("file1"));
		
		 product.setFile1(fileName !=null  ? fileName : null );  
		   
		   if(product.getFile1()==null) {
			   System.out.println("파일이 업로드 되지 않음");
		   }
		   
		   ProductDao dao = new ProductDao();
		   dao.insertProduct(product);   
		

		return "r:productList.mvc";
	}

}
