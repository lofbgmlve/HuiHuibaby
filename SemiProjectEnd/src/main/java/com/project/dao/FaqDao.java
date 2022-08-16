package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.vo.Faq;


public class FaqDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getFaqCount(String type, String keyword) {
		System.out.println(type + " - " + keyword);
		
		String sqlCount = "SELECT COUNT(*) FROM faq_notice WHERE " 	
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
	

	public ArrayList<Faq> searchList(
		String type, String keyword, int startRow, int endRow) {
		String sqlSearchList = "SELECT * FROM (SELECT faq_no, faq_title, faq_image, faq_content, mg_id FROM"
				+ " (SELECT * FROM faq_notice WHERE " + type + " LIKE ?"
				+ " ORDER BY faq_no DESC)) WHERE faq_no >= ? AND faq_no <= ?";
		ArrayList<Faq> faqList = null;
		
		try{			
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sqlSearchList);			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				faqList = new ArrayList<Faq>();
				
				do {					
					Faq faq = new Faq();
					faq.setFaq_no(rs.getInt("faq_no"));
					faq.setFaq_title(rs.getString("faq_title"));
					faq.setFaq_image(rs.getString("faq_image"));
					faq.setFaq_content(rs.getString("faq_content"));
					faq.setMg_id(rs.getString("mg_id"));
					
					faqList.add(faq);
					
				} while(rs.next());
			}
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return faqList;
	}
	
	
	// 전체 게시글 수를 계산하기 위해 호출되는 메서드
	public int getFaqCount() {
		
		String sqlCount = "SELECT COUNT(*) FROM faq_notice";
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
	 public ArrayList<Faq> faqList(int startRow, int endRow) {

		String sqlFaqList = "SELECT * FROM (SELECT faq_no, faq_title, faq_image, faq_content, mg_id FROM"
				+ " (SELECT * FROM faq_notice ORDER BY faq_no DESC)) "
				+ " WHERE faq_no >= ? AND faq_no <= ?";
		ArrayList<Faq> faqList = null;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlFaqList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				faqList = new ArrayList<Faq>();
				
				do {					
					Faq faq = new Faq();
					faq.setFaq_no(rs.getInt("faq_no"));
					faq.setFaq_title(rs.getString("faq_title"));
					faq.setFaq_image(rs.getString("faq_image"));
					faq.setFaq_content(rs.getString("faq_content"));
					faq.setMg_id(rs.getString("mg_id"));
					
					faqList.add(faq);
					
				} while(rs.next());
			}
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
		return faqList;
	}
	
	 
	 // 게시글 내용 보기 요청시 호출되는 메소드
	public Faq getFaq(int faq_no, boolean state) {
		String faqSql = "SELECT * FROM faq_notice WHERE faq_no=?";
		Faq faq = null;
		
		try{
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);
			
			pstmt = conn.prepareStatement(faqSql);
			pstmt.setInt(1, faq_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				faq = new Faq();				
				faq.setFaq_no(rs.getInt("faq_no"));
				faq.setFaq_title(rs.getString("faq_title"));
				faq.setFaq_image(rs.getString("faq_image"));
				faq.setFaq_content(rs.getString("faq_content"));
				faq.setMg_id(rs.getString("mg_id"));
			}
			
			DBManager.commit(conn);
			
		} catch(Exception e) {
			DBManager.rollback(conn);
			
			System.out.println("FaqDao - getFaq(faq_no, state)");
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}	
		
		return faq;
	}
	
	
	// 게시글 쓰기 요청시 호출되는 메서드
	public void insertFaq(Faq faq) {
		
		String sqlInsert = "INSERT INTO faq_notice(faq_no, faq_title, faq_content, mg_id) "
				+ " VALUES(FAQ_NUMBER.NEXTVAL, ?, ?, ?)";
		
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, faq.getFaq_title());
			pstmt.setString(2, faq.getFaq_content());
			pstmt.setString(3, faq.getMg_id());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	// 게시글 수정 요청시 호출되는 메서드
	public void updateFaq(Faq faq) {
		
		String sqlNoFileUpdate = "UPDATE faq_notice set faq_no=?, faq_title=?, faq_content=?, mg_id=?";
		String sqlFileUpdate = "UPDATE faq_notice set faq_no=?, faq_title=?, faq_image=?, faq_content=?, mg_id=?";
		
		try {			
			conn = DBManager.getConnection();
			
			// 파일 업로드일 경우와 그렇지 않은 경우를 구분해서 처리
			if(faq.getFaq_image() == null) {
				pstmt = conn.prepareStatement(sqlNoFileUpdate);
				pstmt.setInt(1, faq.getFaq_no());
				pstmt.setString(2, faq.getFaq_title());
				pstmt.setString(3, faq.getFaq_content());
				pstmt.setString(4, faq.getMg_id());			
				
			} else {
				pstmt = conn.prepareStatement(sqlFileUpdate);
				pstmt.setInt(1, faq.getFaq_no());
				pstmt.setString(2, faq.getFaq_title());
				pstmt.setString(3, faq.getFaq_image());
				pstmt.setString(4, faq.getFaq_content());
				pstmt.setString(5, faq.getMg_id());
			}
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	
	// 게시글 삭제 요청 시 호출되는 메서드 
	public void deleteFaq(int faq_no) {
		
		String sqlDelete = "DELETE FROM faq_notice WHERE faq_no=?"; 
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, faq_no);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
}






