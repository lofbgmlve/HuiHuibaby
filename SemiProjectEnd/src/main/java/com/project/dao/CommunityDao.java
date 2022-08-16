package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.project.vo.Community;

public class CommunityDao {

	private Connection conn;
	
	private PreparedStatement pstmt;
	
	private ResultSet rs;
	
	
	/* 전체 게시 글 수를 계산하기 위해 호출되는 메서드 - paging 처리에 사용
	* DB 테이블에 등록된 모든 게시 글의 수를 반환하는 메서드
	**/
	public int getCommunityCount() {
		
		String sqlCount = "SELECT COUNT(*) FROM community_tb";
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
	
	
	/* 검색어에 해당하는 게시 글 수를 계산하기 위해 호출되는 메서드
	 * DB 테이블에 등록된 모든 게시 글의 수를 반환하는 메서드
	 **/
	public int getCommunityCount(String type, String keyword) {
		System.out.println(type + " - " + keyword);
		
		String sqlCount = "SELECT COUNT(*) FROM community_tb WHERE " 	
				+ type + " LIKE '%' || ? || '%'";
		int count = 0;
		
		try {
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
	
	
	/* 제목, 작성자, 내용에서 검색어가 포함된 게시 글 검색 시 호출되는 메서드
	 * 요청한 페이지에 해당하는 검색 결과를 DB에서 읽어와 반환하는 메서드
	 **/
	public ArrayList<Community> sqlCommunityList(
			String type, String keyword, int startRow, int endRow) {
		
		String sqlCommunityList =
				"SELECT * FROM "
				 + "(SELECT  ROWNUM num, sc.* FROM "
				 + "	(SELECT * FROM community_tb WHERE " + type + " LIKE ? ORDER BY c_no DESC) sc) "
			+ "WHERE num >= ? AND num <= ?";
		 
		ArrayList<Community> CommunityList = null;
		try {	
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCommunityList);			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				CommunityList = new ArrayList<Community>();
				
				do {
					Community community = new Community();
					community.setCommunityNo(rs.getInt("c_no"));
					community.setCommunitySubject(rs.getString("c_subject"));
					community.setCommunityContent(rs.getString("c_content"));
					community.setCommunityDate(rs.getDate("c_date"));
					community.setCommunityImage(rs.getString("c_image"));
					community.setNickName(rs.getString("m_nickname"));
					
					CommunityList.add(community);
					
				}while(rs.next());
			}
		} catch(Exception e) {			
				e.printStackTrace();
				
		} finally {
				DBManager.close(conn, pstmt, rs);
		}
		System.out.println("communityList " + CommunityList);
		return CommunityList;
	}
	
	/* 게시 글 상세 보기 요청 시 호출되는 메서드*/
	public Community getCommunity(int no) {
		String communitySql = "SELECT * FROM community_tb WHERE c_no=?";
		Community community = null;
		
		try{
				
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(communitySql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				community = new Community();				
				community.setCommunityNo(rs.getInt("c_no"));
				community.setCommunityContent(rs.getString("c_content"));
				community.setCommunitySubject(rs.getString("c_subject"));
				community.setCommunityDate(rs.getDate("c_date"));
				community.setNickName(rs.getString("m_nickname"));
			}
			
			
			DBManager.commit(conn);
			
		} catch(Exception e) {
			
			DBManager.rollback(conn);
			
			System.out.println("CommunityDao - getCommunity(no, state)");
			e.printStackTrace();			
		} finally {			
			
			DBManager.close(conn, pstmt, rs);
		}		
		
		return community;
	}
	
	/* 한 페이지에 보여 질 게시 글 리스트 요청시 호출되는 메소드
	 * 요청한 페이지에 해당하는 게시 글 리스트를 DB에서 읽어와 반환하는 메소드
	 **/
	public ArrayList<Community> CommunityList(int startRow, int endRow) {
		
		String sqlCommunityList =
				"SELECT * FROM "
				+ " (SELECT ROWNUM num, sc.* FROM "
				+ "  (SELECT * FROM community_tb ORDER BY c_no DESC) sc) "
			+ "WHERE num >= ? AND num <= ?";
 
		ArrayList<Community> CommunityList = null;
		
		try {	
			
			System.out.println("test" + startRow);
			System.out.println("test2" + endRow);
			System.out.println("test3" + sqlCommunityList);
		
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlCommunityList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				CommunityList = new ArrayList<Community>();
				
				do {
					Community community = new Community();
					community.setCommunityNo(rs.getInt("c_no"));
					community.setCommunitySubject(rs.getString("c_subject"));
					community.setCommunityContent(rs.getString("c_content"));
					community.setCommunityDate(rs.getDate("c_date"));
					community.setCommunityImage(rs.getString("c_image"));
					community.setNickName(rs.getString("m_nickname"));
					
					CommunityList.add(community);
					
					
					
					
				} while(rs.next());
			}
		} catch(Exception e) {
			System.out.println("에러");
			e.printStackTrace();
			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
		return CommunityList;
	}
	
	/* 게시 글쓰기 요청시 호출되는 메서드
	 * 게시 글을 작성하고 등록하기 버튼을 클릭하면 게시 글을 DB에 추가하는 메서드 
	 **/
	public void insertCommunity(Community community) {
		
		String sqlInsert = "INSERT INTO community_tb(c_no, c_subject, c_content, c_image,"
				+ " c_date, m_nickname) "
				+ " VALUES(COMMUNITY_TB_C_NO.nextval, ?, ?, ?, SYSDATE, ?)";
		
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, community.getCommunitySubject());
			pstmt.setString(2, community.getCommunityContent());			
			pstmt.setString(3, community.getCommunityImage());
			pstmt.setString(4, community.getNickName());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
}
