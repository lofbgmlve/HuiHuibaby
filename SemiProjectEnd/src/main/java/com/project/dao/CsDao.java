package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.vo.Cs;
import com.project.vo.Cs;


public class CsDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCsCount(String type, String keyword) {
		System.out.println(type + " - " + keyword);
		
		String sqlCount = "SELECT COUNT(*) FROM customer_service WHERE " 	
					+ type + " LIKE '%' || ? || '%'";
		
		int count = 0;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setString(1,  keyword);			
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
	

	public ArrayList<Cs> searchList(
		String type, String keyword, int startRow, int endRow) {
		
		String sqlSearchList = "SELECT * FROM (SELECT ROWNUM cs_no, cs_subject, cs_content, cs_image, cs_date, m_nickname FROM"
				+ " (SELECT * FROM customer_service WHERE " + type + " LIKE ?"
				+ " ORDER BY no DESC)) WHERE num >= ? AND num <= ?";
		
		ArrayList<Cs> csList = null;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlSearchList);			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				csList = new ArrayList<Cs>();
				
				do {					
					Cs cs = new Cs();
					cs.setCs_no(rs.getInt("cs_no"));
					cs.setCs_subject(rs.getString("cs_subject"));
					cs.setCs_content(rs.getString("cs_content"));
					cs.setCs_image(rs.getString("cs_image"));
					cs.setCs_date(rs.getTimestamp("cs_date"));
					cs.setM_nickname(rs.getString("m_nickname"));
					
					csList.add(cs);
					
				} while(rs.next());
			}
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return csList;
	}
	
	
	// 전체 게시글 수를 계산하기 위해 호출되는 메서드
	public int getCsCount() {
		
		String sqlCount = "SELECT COUNT(*) FROM customer_service";
		int count = 0;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}			
		} catch(Exception e) {			
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	

	// 한 페이지에 보여질 게시글 리스트 요청시 호출되는 메소드
	 public ArrayList<Cs> csList(int startRow, int endRow) {
		
		String sqlCsList = "SELECT * FROM (SELECT ROWNUM "
				+ " cs_no, cs_subject, cs_content, cs_image, cs_date, m_nickname FROM"
				+ " (SELECT * FROM customer_service ORDER BY no DESC)) "
				+ " WHERE num >= ? AND num <= ?";
		
		ArrayList<Cs> csList = null;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCsList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				csList = new ArrayList<Cs>();
				
				do {					
					Cs cs = new Cs();
					cs.setCs_no(rs.getInt("faq_no"));
					cs.setCs_subject(rs.getString("cs_subject"));
					cs.setCs_content(rs.getString("cs_content"));
					cs.setCs_image(rs.getString("cs_image"));
					cs.setCs_date(rs.getTimestamp("cs_date"));
					cs.setM_nickname(rs.getString("m_nickname"));
					
					csList.add(cs);
					
				} while(rs.next());
			}
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
		return csList;
	}
	
	 
	 // 게시글 내용 보기 요청시 호출되는 메소드
	public Cs getCs(int cs_no, boolean state) {
		String csSql = "SELECT * FROM customer_service WHERE no=?";
		Cs cs = null;
		
		try{
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);
			
			pstmt = conn.prepareStatement(csSql);
			pstmt.setInt(1, cs_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cs = new Cs();
				cs.setCs_no(rs.getInt("faq_no"));
				cs.setCs_subject(rs.getString("cs_subject"));
				cs.setCs_content(rs.getString("cs_content"));
				cs.setCs_image(rs.getString("cs_image"));
				cs.setCs_date(rs.getTimestamp("cs_date"));
				cs.setM_nickname(rs.getString("m_nickname"));
			}
			
			DBManager.commit(conn);
			
		} catch(Exception e) {
			DBManager.rollback(conn);
			
			System.out.println("CsDao - getCs(cs_no, state)");
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}		
		
		return cs;
	}
	
	
	// 게시글 쓰기 요청시 호출되는 메서드
	public void insertCs(Cs cs) {
		
		String sqlInsert = "INSERT INTO customer_service(cs_no, cs_subject, cs_content, cs_image, cs_date, m_nickname) "
				+ " VALUES(customer_service_seq.NEXTVAL, ?, ?, ?, SYSDATE, ?)";
		
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(2, cs.getCs_subject());
			pstmt.setString(3, cs.getCs_content());
			pstmt.setString(4, cs.getCs_image());
			pstmt.setString(5, cs.getM_nickname());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	// 게시글 수정 요청시 호출되는 메서드
	public void updateCs(Cs cs) {
		
		String sqlNoFileUpdate = "UPDATE customer_service set cs_no=?, cs_subject=?, cs_content=?, cs_date=SYSDATE, m_nickname=?";
		String sqlFileUpdate = "UPDATE customer_service set cs_no=?, cs_subject=?, cs_content=?, cs_image=?, cs_date=SYSDATE, m_nickname=?";
		
		try {			
			conn = DBManager.getConnection();
			
			// 파일 업로드일 경우와 그렇지 않은 경우를 구분해서 처리
			if(cs.getCs_image() == null) {
				pstmt = conn.prepareStatement(sqlNoFileUpdate);
				pstmt.setInt(1, cs.getCs_no());
				pstmt.setString(2, cs.getCs_subject());
				pstmt.setString(3, cs.getCs_content());
				pstmt.setString(4, cs.getM_nickname());			
				
			} else {
				pstmt = conn.prepareStatement(sqlFileUpdate);
				pstmt.setInt(1, cs.getCs_no());
				pstmt.setString(2, cs.getCs_subject());
				pstmt.setString(3, cs.getCs_content());
				pstmt.setString(4, cs.getCs_image());
				pstmt.setString(5, cs.getM_nickname());
			}
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	
	// 게시글 삭제 요청 시 호출되는 메서드 
	public void deleteCs(int cs_no) {
		
		String sqlDelete = "DELETE FROM customer_service WHERE no=?"; 
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, cs_no);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
}






