package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.UserDao;
import com.project.vo.User;

public class UserUpdateResultService implements CommandProcess {

	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		// 변수에 저장
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
		
		/* MemberBean 인스턴스를 생성하여 
		 * 회원 가입 폼으로 부터 넘어온 데이터를 저장 한다. 
		 **/	
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
		dao.updateUser(user);	
		
		
		return "r:productList.mvc";
	}

}
