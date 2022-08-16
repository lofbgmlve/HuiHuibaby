package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CcDao;
import com.project.vo.Cc;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// CC 글쓰기 요청을 처리하는 서비스 클래스
public class CcWriteService  implements CommandProcess {
	
	public String requestProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		String uploadDir = 
				(String) request.getServletContext().getAttribute("uploadDir");
		String realPath = request.getServletContext().getRealPath(uploadDir);		
		
		int maxFileSize = 100 * 1024 * 1024;
		
		String encoding = "UTF-8"; 
			
		MultipartRequest multi = new MultipartRequest(request, realPath, 
							maxFileSize, encoding, new DefaultFileRenamePolicy());	
		
		String col = multi.getParameter("col");
		String col2 = multi.getParameter("col2");
		String mg_id = multi.getParameter("mg_id");
		
		Cc cc = new Cc();
		cc.setCol(col);
		cc.setCol2(col2);
		cc.setMg_id(mg_id);
			
		
		CcDao dao = new CcDao();
		dao.insertCc(cc);

		return "r:ccList.mvc";		
	}
}
