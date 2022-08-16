package com.project.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.ProductDao;
import com.project.vo.Reply;
import com.google.gson.Gson;

public class ReplyWriteAction implements AjaxProcess{

		public void ajaxProcess(
				HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException{
			
			
			String content = request.getParameter("content");
			String nickname= request.getParameter("nickname");
			String pNo =request.getParameter("pNo");
	
			
			// 댓글쓰기~~   pNo=&content=
			Reply reply = new Reply(content,nickname,Integer.parseInt(pNo));
			ProductDao dao = new ProductDao();
			// 댓글!! 
			dao.addReply(reply);
			
			
			
			ArrayList<Reply> replyList= dao.getReplyList(Integer.parseInt(pNo));
			
			Gson gson = new Gson();
			
			// dao 에서 받은 ArrayList 객체를 json 형식으로 직렬호한다.
			// dao에서 ArrayList 에 Reply 를 저장해 반환했기 때문에 
			// 이 데이터를 json 형식으로 직렬화 하면 
			
			String result =gson.toJson(replyList);
			//  댓글 결과 log 찍어 보기 
			System.out.println("ReplyWriteAction --- result" + result);
			
			// 응답 데이터를 스트림을 통해 클라이언트에게 전송하기 위해 응답객체response로 스트림연결! 
			// AjaxController 는 Ajax 요청만 받난 컨트롤서로 이컨트롤러에서 현재 클래스의 ajaxProcess()
			// 메서드를 호풀하면 요청을 처리하고 컨트롤러로 돌아갈떄 응갑객체에 연결된 스트림을 통해 
			// result에 저장된 데이터가 웹 브라우저의 XMLHttpRequest 객체로 전달된다 . 
			
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			
			
			
		}
	
}
