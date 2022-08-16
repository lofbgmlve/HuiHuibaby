package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.vo.Cc;


public class CcDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int getCcCount(String type, String keyword) {
		System.out.println(type + " - " + keyword);
		
		String sqlCount = "SELECT COUNT(*) FROM cs_comment WHERE " 	
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
	

	public ArrayList<Cc> searchList(
		String type, String keyword, int startRow, int endRow) {
		
		String sqlSearchList = "SELECT * FROM (SELECT ROWNUM col5, col, col2, col3, mg_id, cs_no FROM"
				+ " (SELECT * FROM cs_comment WHERE " + type + " LIKE ?"
				+ " ORDER BY no DESC)) WHERE num >= ? AND num <= ?";
		
		ArrayList<Cc> ccList = null;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlSearchList);			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				ccList = new ArrayList<Cc>();
				
				do {					
					Cc cc = new Cc();
					cc.setCol5(rs.getInt("col5"));
					cc.setCol(rs.getString("col"));
					cc.setCol2(rs.getString("col2"));
					cc.setCol3(rs.getTimestamp("col3"));
					cc.setMg_id(rs.getString("mg_id"));
					cc.setCs_no(rs.getInt("cs_no"));
					
					ccList.add(cc);
					
				} while(rs.next());
			}
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return ccList;
	}
	
	
	// 전체 게시글 수를 계산하기 위해 호출되는 메서드
	public int getCsCount() {
		
		String sqlCount = "SELECT COUNT(*) FROM cs_comment";
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
	 public ArrayList<Cc> ccList(int startRow, int endRow) {
		
		String sqlCsList = "SELECT * FROM (SELECT ROWNUM "
				+ " col5, col, col2, col3, mg_id, cs_no FROM"
				+ " (SELECT * FROM cs_comment ORDER BY no DESC)) "
				+ " WHERE num >= ? AND num <= ?";
		
		ArrayList<Cc> ccList = null;
		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCsList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				ccList = new ArrayList<Cc>();
				
				do {					
					Cc cc = new Cc();
					cc.setCol5(rs.getInt("col5"));
					cc.setCol(rs.getString("col"));
					cc.setCol2(rs.getString("col2"));
					cc.setCol3(rs.getTimestamp("col3"));
					cc.setMg_id(rs.getString("mg_id"));
					cc.setCs_no(rs.getInt("cs_no"));
					
					ccList.add(cc);
					
				} while(rs.next());
			}
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
		return ccList;
	}
	
	 
	 // 게시글 내용 보기 요청시 호출되는 메소드
	public Cc getCs(int col5, boolean state) {
		String csSql = "SELECT * FROM cs_comment WHERE no=?";
		Cc cc = null;
		
		try{
			conn = DBManager.getConnection();
			DBManager.setAutoCommit(conn, false);
			
			pstmt = conn.prepareStatement(csSql);
			pstmt.setInt(1, col5);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cc = new Cc();
				cc.setCol5(rs.getInt("col5"));
				cc.setCol(rs.getString("col"));
				cc.setCol2(rs.getString("col2"));
				cc.setCol3(rs.getTimestamp("col3"));
				cc.setMg_id(rs.getString("mg_id"));
				cc.setCs_no(rs.getInt("cs_no"));
			}
			
			DBManager.commit(conn);
			
		} catch(Exception e) {
			DBManager.rollback(conn);
			
			System.out.println("CcDao - getCc(col5, state)");
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}		
		
		return cc;
	}
	
	
	// 게시글 쓰기 요청시 호출되는 메서드
	public void insertCc(Cc cc) {
		
		String sqlInsert = "INSERT INTO cs_comment(col5, col, col2, col3, mg_id, cs_no) "
				+ " VALUES(cs_comment_seq.NEXTVAL, ?, ?, SYSDATE, ?, ?)";
		
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(2, cc.getCol());
			pstmt.setString(3, cc.getCol2());
			pstmt.setString(4, cc.getMg_id());
			pstmt.setInt(5, cc.getCs_no());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	// 게시글 수정 요청시 호출되는 메서드
	public void updateCc(Cc cc) {
		
		String sqlNoFileUpdate = "UPDATE cs_comment set col5=?, col=?, col2=?, col3=SYSDATE, mg_id=?, cs_no=?";
		
		try {			
			conn = DBManager.getConnection();
			
			// 파일 업로드일 경우와 그렇지 않은 경우를 구분해서 처리
			pstmt = conn.prepareStatement(sqlNoFileUpdate);
			pstmt.setInt(1, cc.getCol5());
			pstmt.setString(2, cc.getCol());
			pstmt.setString(3, cc.getCol2());
			pstmt.setTimestamp(4, cc.getCol3());
			pstmt.setString(5, cc.getMg_id());
			pstmt.setInt(6, cc.getCs_no());			
			
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	
	// 게시글 삭제 요청 시 호출되는 메서드 
	public void deleteCc(int col5) {
		
		String sqlDelete = "DELETE FROM cs_comment WHERE no=?"; 
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, col5);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
}






