package com.project.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.UserDao;
import com.project.vo.Community;
import com.project.vo.Product;
import com.project.vo.Review;

public class MyPageService implements CommandProcess {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// UserDao를 생성
		UserDao dao = new UserDao();
		HttpSession session = request.getSession();
		String mNickname = (String) session.getAttribute("mNickname");
		
		
		System.out.println("MyPageService mNickname : "+ mNickname);
		
		// UserDao를 이용해서 - 현재 유저가 등록한 상품 리스트
		ArrayList<Product> myProductList= dao.myProductList(mNickname);
		
		
		// UserDao를 이용해서 - 현재 유저가 작성한 리뷰 리스트
		
		ArrayList<Review> myReviewList = dao.myReviewList(mNickname);
		
		
		// UserDao를 이용해서 - 현재 유저가 작성한 게시글 리스트			
		ArrayList<Community> myCommunityList= dao.myCommunityList(mNickname);
		
		// Dao를 통해서 가져온 데이터를 모델에 담는다. - myPage 뷰에 출력하기 위해서
		request.setAttribute("myProductList", myProductList);
		request.setAttribute("myCommunityList", myCommunityList);
		request.setAttribute("myReviewList", myReviewList);
		
		System.out.println("mycommunitylist" + myCommunityList);
		
		return "user/myPage";
	}

}
