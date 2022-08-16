package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CcDao;
import com.project.vo.Cc;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// 게시 글 수정 폼에서 요청한 데이터를 받아 DB에 수정하는 모델 클래스
public class CcUpdateService  implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		String contentType = request.getHeader("Content-Type");
		System.out.println("contentType : " + contentType);	
		CcDao dao = new CcDao();
		Cc cc = null;
		String col = null, col2 = null, mg_id = null, 
				sNo = null, pageNum = null, type=null, keyword=null;
		Timestamp col3 = null;
		int col5 = 0, cs_no = 0;		

		// 요청이 multipart/form-data 일 경우
		if(contentType.contains("multipart/form-data")) {
			
			
			String uploadDir = 
					(String) request.getServletContext().getAttribute("uploadDir");
			String realPath = request.getServletContext().getRealPath(uploadDir);
			
			// 업로드 파일의 최대 크기를 100MB로 지정
			int maxFileSize = 100 * 1024 * 1024;
			
			// 파일의 인코딩 타입을 UTF-8로 지정
			String encoding = "UTF-8"; 
				
			MultipartRequest multi = new MultipartRequest(request, realPath, 
								maxFileSize, encoding, new DefaultFileRenamePolicy());	
			
			sNo = multi.getParameter("col5");		
			pageNum = multi.getParameter("pageNum");
			type = multi.getParameter("type");
			keyword = multi.getParameter("keyword");		
			
			if(sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("")) {

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("	alert('정상적인 접근이 아닙니다.');");
				out.println("	history.back();");
				out.println("</script>");
				return null;
			}
			
			col5 = Integer.parseInt(sNo);
			cs_no = Integer.parseInt("cs_no");
			
			col = multi.getParameter("col");
			col2 = multi.getParameter("col2");
			mg_id = multi.getParameter("mg_id");		
			
			cc = new Cc();
			cc.setCol5(col5);
			cc.setCol(col);
			cc.setCol2(col2);	
			cc.setCol3(col3);
			cc.setMg_id(mg_id);
			cc.setCs_no(cs_no);
				
			
		// 요청이 multipart/form-data 아닌 경우	
		} else {		
			
			request.setCharacterEncoding("utf-8");
			sNo = request.getParameter("col5");		
			pageNum = request.getParameter("pageNum");
			type = request.getParameter("type");
			keyword = request.getParameter("keyword");
			
			if(sNo == null || sNo.equals("") || pageNum == null || pageNum.equals("")) {

				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("	alert('정상적인 접근이 아닙니다.');");
				out.println("	history.back();");
				out.println("</script>");
				return null;
			}
			
			col5 = Integer.parseInt(sNo);
			cs_no = Integer.parseInt("cs_no");
			
			col = request.getParameter("col");
			col2 = request.getParameter("col2");
			mg_id = request.getParameter("mg_id");
			
			cc = new Cc();
			cc.setCol5(col5);
			cc.setCol(col);
			cc.setCol2(col2);	
			cc.setCol3(col3);
			cc.setMg_id(mg_id);
			cc.setCs_no(cs_no);
		}
		
		dao.updateCc(cc);

		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 	
		
		String url = "ccList.mvc?pageNum=" + pageNum;

		if(searchOption) {
			
			keyword = URLEncoder.encode(keyword, "utf-8");			
			url += "&type=" + type + "&keyword=" + keyword; 
		}		
		System.out.println("keyword : " + keyword);
		System.out.println("url : " + url);		
		
		return "r:" + url;
	}
}