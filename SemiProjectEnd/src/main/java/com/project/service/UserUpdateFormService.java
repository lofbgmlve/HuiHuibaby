package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.UserDao;
import com.project.vo.User;

public class UserUpdateFormService implements CommandProcess {

	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		/* Request 객체로 부터 세션 객체를 얻어 회원 로그인 또는
		 * 회원 가입시 세션 영역의 속성에 저장된 회원의 id를 읽어온다.
		 **/
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("mId");
		
		/* 회원 정보를 테이블로 부터 읽어오기 위해 MemberDAO 객체를 얻어
		 * 회원 테이블에서 id에 해당하는 회원 정보를 읽어온다.
		 **/ 
		UserDao dao = new UserDao();
		User user = dao.getMember(id);
		
		// Request 영역의 속성에 테이블로 부터 읽어온 회원 정보를 저장 한다.
		session.setAttribute("user", user);
		
		
		return "user/userUpdateForm";
	}

}
