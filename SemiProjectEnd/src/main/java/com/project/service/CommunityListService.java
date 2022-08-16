package com.project.service;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.CommunityDao;
import com.project.vo.Community;

// 게시 글 리스트 보기 요청을 처리하는 서비스 클래스
public class CommunityListService  implements CommandProcess {
	
	// 한 페이지에 보여 줄 게시 글의 수를 상수로 선언하고 있다.
	private static final int PAGE_SIZE = 5;
	
	/* 한 페이지에 보여 질 페이지 그룹의 수를 상수로 선언하고 있다.
	 * [이전] 1 2 3 4 5 6 7 8 9 10 [다음]	
	 **/
	private static final int PAGE_GROUP = 10;
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException {
		
		// 요청 파라미터로 넘어 온 페이지 번호를 읽어온다.
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");	
		String keyword = request.getParameter("keyword");	
		
		System.out.println("시작");
		
		/* pageNum이 null 이면 처음 게시 글 리스트를 요청하거나 게시 글쓰기에서 
		 * Redirect 되어 넘어온 요청으로 pageNum을 1페이지로 설정한다.
		 * 즉 최신 게시 글의 첫 번째 페이지에 해당하는 게시 글 리스트를 화면에 출력한다. 
		 **/
		if(pageNum == null) {
			pageNum = "1";
		}
		
		// 요청 파라미터의 pageNum을 int 형으로 변환하여 현재 페이지로 설정하고 있다.
		int currentPage = Integer.parseInt(pageNum);
		
		/* 요청한 페이지에 해당하는 게시 글의 첫 번째 행의 값을 계산한다.
		 * 현재 페이지가 1일 경우 startRow는 1, 2페이지 일 경우 startRow는 6이 된다.	 
		 * 
		 * 테이블에서 현재 페이지에 해당하는 게시 글을 검색할 때 ROWNUM을 사용했다.
		 * ROWNUM은 쿼리의 결과로 검색되는 행들의 순서 값을 가진 의사컬럼으로
		 * 1부터 시작한다. 예를 들어 3페이지에 해당하는 게시 글 리스트를 가져 온다면
		 * 한 페이지에 보여줄 게시 글의 수가 5개로 지정되어 있으므로 startRow는 11이 된다. 
		 * 즉 아래의 공식에 의해 startRow(11) = 3 * 5 - (5 - 1);
		 * 첫 번째 페이지 startRow = 1, 두 번째 페이지 startRow = 6이 된다.
		 **/ 
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		//int startRow = (currentPage - 1) * PAGE_SIZE + 1;
		
		int endRow = startRow + PAGE_SIZE - 1;
				
		int listCount = 0;
		ArrayList<Community> communityList = null;
		
		// BoardDao 객체를 생성하고 전체 게시 글의 수를 얻어온다.
		CommunityDao dao = new CommunityDao();
		
		/* 요청 파라미터에서 type이나 keyword가 비어 있으면 일반 
		 * 게시 글 리스트를 요청하는 것으로 간주하여 false 값을 갖게 한다.
		 **/
		boolean searchOption = (type == null || type.equals("") 
				|| keyword == null || keyword.equals("")) ? false : true; 
		
		// 검색 요청이 아니면
		if(! searchOption) {
			// 전체 게시 글 수를 구한다.
			listCount = dao.getCommunityCount();
			
			// 현재 페이지에 해당 하는 게시 글 리스트를 DB로부터 읽어온다.
			communityList = dao.CommunityList(startRow, endRow);
			
		} else {
			// 검색어에 해당하는 게시 글 수를 구한다.
			listCount = dao.getCommunityCount(type, keyword);
			
			// 검색 결과에 대한 현재 페이지에 해당 하는 게시 글 리스트를 DB로부터 읽어온다.
			communityList = dao.sqlCommunityList(type, keyword, startRow, endRow);
		}
		System.out.println("listCount : " + listCount);

		int pageCount = listCount / PAGE_SIZE 
							+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		 int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
			- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
				
		// 현재 페이지 그룹의 마지막 페이지 : 10, 20, 30...
		int endPage = startPage + PAGE_GROUP - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}	

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageGroup", PAGE_GROUP);
		request.setAttribute("listCount", listCount);
		request.setAttribute("cList", communityList);
		request.setAttribute("searchOption", searchOption);
		
		// 검색 요청이면 type과 keyword를 request 영역에 저장한다.
		if(searchOption) {			
			request.setAttribute("type", type);			
			request.setAttribute("keyword", keyword);
		}

		return "Community/communityList";		
	}
}
