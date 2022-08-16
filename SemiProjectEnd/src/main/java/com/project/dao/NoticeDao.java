package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.dao.DBManager;
import com.project.vo.Notice;

import oracle.sql.DATE;


public class NoticeDao {
	//변수선언
	
	//DB에 연결해 작업을 수행할 수 있도록 지원
	private Connection conn;
	//DB에 쿼리를 발행
	private PreparedStatement pstmt;
	//DB에 SELECT 쿼리를 발행한 결과를 저장
	private ResultSet rs;
	
	// 검색어에 해당하는 게시글 수를 계산하기위한메서드 테이블에 등록된 모든게시글의 수를 반환
	public int getNoticeCount(String type, String keyword) {
		System.out.println(type + " - " + keyword);
		
		String sqlCount = "SELECT COUNT(*) FROM NOTICE WHERE "
				+ type + " LIKE '%' || ? || '%'";
		
		int count = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {			
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	//검색 결과를 DB에서 읽어와 반환
	public ArrayList<Notice> searchList(
			String type, String keyword, int startRow, int endRow) {
		
		//검색어가 포함된 게시 글 리스트를 추출
		String sqlSearchList = "SELECT * FROM (SELECT ROWNUM NUM, NT_NO, NT_SUBJECT,"
			    + " NT_CONTENT, NT_IMAGE, NT_DATE,"
			    + " FROM"
				+ " (SELECT * FROM NOTICE WHERE " + type + " LIKE ?"
				+ " ORDER BY no DESC)) WHERE NUM >= ? AND NUM <= ?";
		
		ArrayList<Notice> noticeList = null;
		
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlSearchList);			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeList = new ArrayList<Notice>();
				
				do {
					Notice notice = new Notice();
					notice.setNt_no(rs.getInt("nt_no"));
					notice.setNt_subject(rs.getString("nt_subject"));
					notice.setNt_content(rs.getString("nt_content"));
					notice.setNt_image(rs.getString("nt_image"));
					notice.setNt_date(rs.getDate("nt_date"));
					notice.setMg_id(rs.getString("mg_id"));
					
					noticeList.add(notice);
				} while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		System.out.println("noticeList " + noticeList);
		return noticeList;
	}
	
	//상세보기
	public Notice getNotice(int no, boolean state) {
		String noticeSql = "SELECT * FROM NOTICE WHERE NT_NO=?";
		Notice notice = new Notice();
		try{
			
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);
			
			
			pstmt = conn.prepareStatement(noticeSql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice.setNt_no(rs.getInt("nt_no"));
				notice.setNt_subject(rs.getString("nt_subject"));
				notice.setNt_content(rs.getString("nt_content"));
				notice.setNt_image(rs.getString("nt_image"));
				notice.setNt_date(rs.getDate("nt_date"));
				notice.setMg_id(rs.getString("mg_id"));
			}
			
			DBManager.commit(conn);
		} catch(Exception e) {
			DBManager.rollback(conn);
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}		
		
		return notice;
	
	}
	
	// 목록보기
	public ArrayList<Notice> NoticeList(int startRow, int endRow) {
		String sql = "SELECT * FROM NOTICE";
		ArrayList<Notice> noticeList = new ArrayList();
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				noticeList = new ArrayList<Notice>();
				
				do {
					Notice notice = new Notice();
					notice.setNt_no(rs.getInt("nt_no"));
					notice.setNt_subject(rs.getString("nt_subject"));
					notice.setNt_content(rs.getString("nt_content"));
					notice.setNt_image(rs.getString("nt_image"));
					notice.setNt_date(rs.getDate("nt_date"));
					notice.setMg_id(rs.getString("mg_id"));
					
					noticeList.add(notice);
				} while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		System.out.println("noticeList " + noticeList);
		return noticeList;
	}
	
	// 공지사항 작성하기
	public void insertNotice(Notice notice) {
		
		String sqlInsert = "INSERT INTO notice(nt_no, nt_subject , nt_content, nt_image, nt_date, mg_id)"
				+ " VALUES(jspbbs_seq.NEXTVAL, ?, ?, 'image', SYSDATE, 'heehee01')";
		
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, notice.getNt_subject());
			pstmt.setString(2, notice.getNt_content());			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}


}
	