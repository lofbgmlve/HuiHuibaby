package com.project.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.UserDao;
import com.project.vo.User;

public class LoginService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mId = request.getParameter("mId");
		String  mPw = request.getParameter("mPw");		
		
		UserDao dao = new UserDao();
		int checkLogin = dao.checkUser(mId,mPw);
		
		if(checkLogin == -1) { //아이디가 틀리면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('입력하신 로그인 정보가 일치하지 않습니다.')");
			out.println("window.history.back();");
			out.println("</script>");
			return null;
			
		}else if(checkLogin == 0) {//비밀번호가 틀리면
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('입력하신 로그인 정보가 일치하지 않습니다.')");
			out.println("window.history.back();");
			out.println("</script>");
			return null;		
			
		} else if(checkLogin == 1) {	// 로그인 성공이면			
			
			// DAO를 이용햬써 .회원 쩡뽀를 DB예써 일ㄲ어와
			User user = dao.getUser(mId);
				
			HttpSession session = request.getSession();
			session.setAttribute("mId", mId);
			session.setAttribute("mNickname", user.getmNickname());
			session.setAttribute("isLogin", true);
		}
		return "r:productList.mvc";		
		
	}//end String requestProcess
}
