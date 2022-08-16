package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.UserDao;
import com.project.vo.User;

public class JoinResultService implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {		
		
		// 값을 변수에 저장
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("mId");
		String pass = request.getParameter("mPw");
		String nickname = request.getParameter("mNickname");
		String name = request.getParameter("mName");
		String phone1 = request.getParameter("mPhone1");
		String phone2 = request.getParameter("mPhone2");
		String phone3 = request.getParameter("mPhone3");
		String gender = request.getParameter("mGender");
		String emailId = request.getParameter("mEmailId");
		String emailDomain = request.getParameter("mEmailDomain");
		String birth1 = request.getParameter("mBirth1");
		String birth2 = request.getParameter("mBirth2");
		String birth3 = request.getParameter("mBirth3");
		
		System.out.println("테스트 id : " + id);
		
		
		
		User user = new User();
		user.setmId(id);
		user.setmPw(pass);
		user.setmNickname(nickname);
		user.setmName(name);
		user.setmPhone(phone1 + "-" + phone2 + "-" + phone3);
		user.setmGender(gender);
		user.setmEmail(emailId + "@" + emailDomain);
		user.setmBirth(birth1 + "-" + birth2 + "-" + birth3);
		
		
		UserDao dao = new UserDao();
		dao.joinUser(user);		
		
		//0315 추가- 유희누나
		 HttpSession session = request.getSession();
		 session.setAttribute("mId",user.getmId());
		 session.setAttribute("mnickname",user.getmNickname()); // + 유희 
		 
		 session.setAttribute("isLogin", true);
		
		/*
		 * HttpSession session = request.getSession(); session.setAttribute("mId",
		 * user.getmId()); session.setAttribute("isLogin", true);
		 */

		
		return "r:productList.mvc";
	}
}
