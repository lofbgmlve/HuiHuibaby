package com.project.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.project.vo.Manager;
import com.project.vo.User;



public class ManagerDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int checkManager(String mgId, String mgPw) { // manager 로그인 기능을 위한 메소드
		
		String loginSql = "SELECT mg_Pw FROM manager_tb WHERE mg_Id = ?";
	
		int result = -1;
		String password = "";
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(loginSql);
			pstmt.setString(1, mgId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				password = rs.getString("mg_Pw");
			} else {
				return result;
			}
			
			if(password.equals(mgPw)) {
				result = 1;				
			} else {
				result = 0;
			}			
		} catch(Exception e) {				
			e.printStackTrace();			
		} finally {		
			DBManager.close(conn, pstmt, rs);
		}		
		return result;
	} //end checkmanager

	/*
	public Manager getManager(String mgId) {
		String selectManager = "SELECT * FROM manager_tb WHERE mg_Id = ?"; 
		Manager manager = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(selectManager);
			pstmt.setString(1, mgId);
			
			rs = pstmt.executeQuery();
			
			manager.setMgId(rs.getString("mg_Id"));;
			manager.setMgPw(rs.getString("mg_Pw"));
			manager.setMgName(rs.getString("mg_Name"));
			manager.setMgNum(rs.getString("mg_Num")); 
		} catch(Exception e) {
			e.printStackTrace(); 
		} finally { 
			DBManager.close(conn, pstmt, rs); 
		} return manager;
	}
*/
	
	
	 
	
	
	/*
	 * public Manager getManager(String mgId) { //회원정보 수정폼 구현 String selectManager =
	 * "SELECT * FROM manager WHERE mg_Id = ?"; Manager manager = null;
	 * 
	 * try { conn = DBManager.getConnection(); pstmt =
	 * conn.prepareStatement(selectManager); pstmt.setString(1, mgId);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * if(rs.next()) { manager = new Manager();
	 * manager.setMgId(rs.getString("mg_Id"));;
	 * manager.setMgPw(rs.getString("mg_Pw"));
	 * manager.setMgName(rs.getString("mg_Name"));
	 * manager.setMgNum(rs.getString("mg_Num")); } } catch(Exception e) {
	 * e.printStackTrace(); } finally { DBManager.close(conn, pstmt, rs); } return
	 * manager; }//end user getmember
	 */	
	
}
