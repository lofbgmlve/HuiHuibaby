package com.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.UserDao;

// 회원 가입시 아이디 중복검사 요청을 처리하는 모델 클래스
public class OverlapNicknameCheckService implements CommandProcess {
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		
		String nickname = request.getParameter("mNickname");
		
		UserDao dao = new UserDao();	
		boolean overlap = dao.overlapNicknameCheck(nickname);
		
		// Request 영역의 속성에 입력된 id와 회원 아이디 중복 여부를 저장 한다. 
		request.setAttribute("nickname", nickname);
		request.setAttribute("overlap", overlap);

		return "f:/WEB-INF/user/overlapNicknameCheck.jsp";
	}
}
