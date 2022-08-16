package com.project.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.vo.Community;
import com.project.vo.Product;
import com.project.vo.Review;
import com.project.vo.User;



public class UserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int checkUser(String mId, String mPw) { // 유저 로그인 기능을 위한 메소드
		
		String loginSql = "SELECT m_Pw FROM member_tb WHERE m_Id = ?";
	
		int result = -1;
		String password = "";
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(loginSql);
			pstmt.setString(1, mId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				password = rs.getString("m_Pw");
			} else {
				return result;
			}
			
			if(password.equals(mPw)) {
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
	} //end checkuser
	
	public User getUser(String mId) { //회원정보 수정폼 구현
		String selectMember = "SELECT * FROM member_tb WHERE m_Id = ?";
		User user = null;		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(selectMember);
			pstmt.setString(1, mId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				user = new User();				
				user.setmId(rs.getString("m_Id"));
				user.setmNickname(rs.getString("m_Nickname"));
				user.setmPw(rs.getString("m_Pw"));
				user.setmEmail(rs.getString("m_Email"));
				user.setmName(rs.getString("m_Name"));
				user.setmPhone(rs.getString("m_Phone"));
				user.setmGender(rs.getString("m_Gender"));
				user.setmBirth(rs.getString("m_Birth"));
				user.setmRegistdate(rs.getDate("m_Registdate"));
				
			}
		} catch(Exception e) {				
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}		
		return user;
	}//end user getUser	
	
		
	
	public void joinUser(User user) {
		
		String joinSql = "INSERT INTO member_tb VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, 'USER')";
			
		try {
			
			conn = DBManager.getConnection();				
			
			pstmt = conn.prepareStatement(joinSql);
				
			// 데이터 설정
			pstmt.setString(1, user.getmId());
			pstmt.setString(2, user.getmPw());
			pstmt.setString(3, user.getmNickname());
			pstmt.setString(4, user.getmName());
			pstmt.setString(5, user.getmPhone());
			pstmt.setString(6, user.getmGender());
			pstmt.setString(7, user.getmEmail());
			pstmt.setString(8, user.getmBirth());			
				
			// 쿼리 전송
			pstmt.executeUpdate();
			
			System.out.println("회원가입 완료");
				
		} catch(Exception e) {			
			e.printStackTrace();
				
		} finally {
			DBManager.close(conn, pstmt);
		}
	} //joinUser end
	
	// 중복 회원을 체크하는 메소드
	public boolean overlapIdCheck(String mId) {
		
		String overlapSql = "SELECT m_id FROM member_tb WHERE m_id=?";	
		boolean result = false;
		
		try{
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(overlapSql);
			
			pstmt.setString(1, mId);
			
			rs = pstmt.executeQuery();
			
			/* 회원 가입 폼에서 입력된 id를 회원 테이블에서 SELECT 하여 ResultSet에
			 * 데이터가 존재하면 이미 가입된 회원 아이디이므로 true를 반환 한다.
			 **/
			if(rs.next()) {
				result = true;
			}			
			System.out.println("overlapIdCheck(String mId) : " + mId);
			
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally{
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}// overlapIdCheck 아이디 체크 end
	
	// 닉네임 체크
	public boolean overlapNicknameCheck(String mNickname) {
		
		String overlapSql = "SELECT m_nickname FROM member_tb WHERE m_nickname=?";	
		boolean result = false;
		
		try{
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(overlapSql);

			pstmt.setString(1, mNickname);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				result = true;
			}			
			System.out.println("overlapNicknameCheck(String mNickname) : " + mNickname);
			
		} catch(Exception e) {			
			e.printStackTrace();
			
		} finally{
			
			DBManager.close(conn, pstmt, rs);
		}
		
		return result;
	} // overlapNicknameCheck 닉네임체크
	
	// 회원정보 수정시
	public User getMember(String mId) {
		String selectMember = "SELECT * FROM member_tb WHERE m_id = ?";
		User user = null;		
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(selectMember);
			pstmt.setString(1, mId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				user = new User();
				user.setmId(rs.getString("m_id"));
				user.setmNickname(rs.getString("m_nickname"));
				user.setmPw(rs.getString("m_pw"));
				user.setmEmail(rs.getString("m_email"));
				user.setmName(rs.getString("m_name"));
				user.setmPhone(rs.getString("m_phone"));
				user.setmGender(rs.getString("m_gender"));
				user.setmBirth(rs.getString("m_birth"));
				user.setmRegistdate(rs.getDate("m_registdate"));
			}
		} catch(Exception e) {				
			e.printStackTrace();
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}		
		return user;
	}//end user getmember
	
		// 회원 정보 수정을 처리하는 메소드
		public void updateUser(User user) {
			
			String joinSql = "UPDATE member_tb SET "
					+ "m_pw=?, m_nickname=?, m_name=?, m_phone=?, "
					+ "m_gender=?, m_email=?, m_birth=?, m_registdate=SYSDATE, m_role='USER' WHERE m_id=?";
			
			try {
				// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
				conn = DBManager.getConnection();
				
				pstmt = conn.prepareStatement(joinSql);
				
				// joinSql 쿼리의 플레이스홀더(?)에 대응하는 데이터를 설정한다.			
				pstmt.setString(1, user.getmPw());
				pstmt.setString(2, user.getmNickname());
				pstmt.setString(3, user.getmName());
				pstmt.setString(4, user.getmPhone());
				pstmt.setString(5, user.getmGender());
				pstmt.setString(6, user.getmEmail());
				pstmt.setString(7, user.getmBirth());
				pstmt.setString(8, user.getmId());
				
				// DB에 쿼리를 전송하여 회원 가입을 완료한다.
				pstmt.executeUpdate();
				
			} catch(Exception e) {			
				e.printStackTrace();
				
			} finally {
				
				// DBManager를 이용해 DB 작업에 사용된 자원을 해제 한다.
				DBManager.close(conn, pstmt);
			}
		}// updateUser	-정민
	
	// 마이페이지에서 해당 회원이 올린 상품게시글 리스트 뽑기
	public ArrayList<Product> myProductList(String mNickname){
		String sqlProductList ="SELECT * FROM product WHERE p_nickname=?";
		ArrayList<Product> productList = null; 
		
		try {
			
			conn= DBManager.getConnection();
			pstmt=conn.prepareStatement(sqlProductList);
			pstmt.setString(1, mNickname);
			rs = pstmt.executeQuery();
			
			productList = new ArrayList<Product>();
			
			while(rs.next()) {
				
				Product p = new Product();
				p.setNo(rs.getInt("p_no"));
				p.setTitle(rs.getString("p_title"));
				p.setDate(rs.getDate("p_date"));
				p.setNickname(rs.getString("p_nickname"));
				
				productList.add(p);
				
			}
			
		}catch(SQLException e) {
			System.out.println("ProductDao - productList -SQLException");
			e.printStackTrace();
		
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return productList;
		
	}// myProductList 
	
		// 마이페이지에서 해당 회원의 커뮤니티 게시글 보기
		public ArrayList<Community> myCommunityList(String mNickname){
		String sqlcommunityList = "SELECT * FROM community_tb WHERE m_nickname=?";
		ArrayList<Community> communityList = null; 
		
		try {
			
			conn= DBManager.getConnection();
			pstmt=conn.prepareStatement(sqlcommunityList);
			pstmt.setString(1, mNickname);
			rs = pstmt.executeQuery();
			
			communityList = new ArrayList<Community>();
			
			while(rs.next()) {
				
				Community c = new Community();
				c.setCommunityNo(rs.getInt("c_no"));
				c.setCommunitySubject(rs.getString("c_subject"));
				c.setCommunityDate(rs.getDate("c_date"));
				c.setNickName(rs.getString("m_nickname"));
				
				communityList.add(c);
				
			}
			
		}catch(SQLException e) {
			System.out.println("CommunityDao - communitytList -SQLException");
			e.printStackTrace();
		
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return communityList;
	}
	
		//마이페이지에서 해당 회원의 리뷰 보기
		public ArrayList<Review> myReviewList(String mNickname){
			String sqlreviewList = "SELECT * FROM review WHERE r_nickname=?";
			ArrayList<Review> reviewList = null; 
			
			try {
				
				conn= DBManager.getConnection();
				pstmt=conn.prepareStatement(sqlreviewList);
				pstmt.setString(1, mNickname);
				rs = pstmt.executeQuery();
				
				reviewList = new ArrayList<Review>();
				
				while(rs.next()) {
					
					Review r = new Review();
					r.setrNo(rs.getInt("r_no"));
					r.setrSubject(rs.getString("r_subject"));
					r.setrDate(rs.getDate("r_date"));
					r.setrNickname(rs.getString("r_nickname"));
					
					reviewList.add(r);
					
				}
				
			}catch(SQLException e) {
				System.out.println("CommunityDao - communitytList -SQLException");
				e.printStackTrace();
			
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			return reviewList;
		}
		
		private User populateUser(User user) {		//checkuser에서 메소드 코드 사용할 때 중복되는 코드 줄이려고 만든 메소드
			try {
				user.setmId(rs.getString("m_Id"));
				user.setmNickname(rs.getString("m_Nickname"));
				user.setmPw(rs.getString("m_Pw"));
				user.setmEmail(rs.getString("m_Email"));
				user.setmName(rs.getString("m_Name"));
				user.setmPhone(rs.getString("m_Phone"));
				user.setmGender(rs.getString("m_Gender"));
				user.setmBirth(rs.getString("m_Birth"));
				user.setmRegistdate(rs.getDate("m_Registdate"));
				user.setmRole(rs.getString("m_role"));
			} catch (SQLException e) {
				user = null;
			}
			
			return user;
		}
}
