package com.project.service;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.NoticeDao;
import com.project.vo.Notice;

public class NoticeListService implements CommandProcess {
	//한 페이지에 보여줄 게시글 수
	private static final int PAGE_SIZE = 5;
	//한페이지에 보여질 페이지 수
	private static final int PAGE_GROUP = 10;
	
	public String requestProcess(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException{
		//페이지번호 읽어오기
		String pageNum = request.getParameter("pageNum");
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		//pageNum을 int형으로 변환
		int currentPage = Integer.parseInt(pageNum);
		
		//페이지에 해당하는 게시글의 첫번째 행의값 계산
		int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
		
		int endRow = startRow + PAGE_SIZE -1;
		
		int listCount = 0;
		ArrayList<Notice> noticeList = new ArrayList();
		
		//전체 게시글의 수를 얻는다
		NoticeDao dao = new NoticeDao();
		
		//type,keyword = null 일반게시글리스트요청으로 간주하여 false
		boolean searchOption = (type == null || type.equals("")
				|| keyword == null || keyword.equals(""))? false : true;
		
		//검색요청이아니면
		if(! searchOption) {
			//전체 게시글 수			
			//현재 페이지에 해당하는 게시글 DB에서리스트읽어오기
			noticeList = dao.NoticeList(startRow, endRow);
			
		} else {
			//검색어에 해당하는 게시글 수
			listCount = dao.getNoticeCount(type, keyword);
			
			// 검색 결과에 대한 현재 페이지에 해당 하는 게시글 리스트DB에서 읽어오기
			noticeList = dao.searchList(type, keyword, startRow, endRow);
			
		}
		
		//전체 페이지 계산
		int pageCount = listCount / PAGE_SIZE
							+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
			- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		int endPage = startPage + PAGE_GROUP - 1;
		
		//마지막페이지 보정
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		if (noticeList != null) {	
			System.out.println(noticeList.size());			
		}
		
		//view저장
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageGroup", PAGE_GROUP);
		request.setAttribute("listCount", listCount);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("searchOption", searchOption);
		
		// 검색요청 type,keyword를 request에 저장
		if(searchOption) {
			request.setAttribute("type", type);
			request.setAttribute("keyword", keyword);
		}
		
		//Redirect 정보와 View 페이지 정보를 문자열로 반환
		return "notice/noticeList";
		
	} // throw ServletException
	
} // public class BoardListService implements CommandProcess
