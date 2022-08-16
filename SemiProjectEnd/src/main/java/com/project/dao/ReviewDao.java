package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.vo.Community;
import com.project.vo.CommunityReply;
import com.project.vo.Review;
import com.project.vo.ReviewReply;

public class ReviewDao {

	private Connection conn;
	
	private PreparedStatement pstmt;
	
	private ResultSet rs;
	
	/* 전체 게시 글 수를 계산하기 위해 호출되는 메서드 - paging 처리에 사용
	* DB 테이블에 등록된 모든 게시 글의 수를 반환하는 메서드
	**/
	public int getReviewCount() {
		
		String sqlCount = "SELECT COUNT(*) FROM review";
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
	public int getReviewCount(String type, String keyword) {
		System.out.println(type + " - " + keyword);
		
		String sqlCount = "SELECT COUNT(*) FROM review WHERE "
				+ type + " LIKE '%' || ? || '%'";
		
		int count = 0;
		
		try{
			
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
	
	/* 제목, 작성자, 내용에서 검색어가 포함된 게시 글 검색 시 호출되는 메서드
	* 요청한 페이지에 해당하는 검색 결과를 DB에서 읽어와 반환하는 메서드
	**/
	public ArrayList<Review> sqlReviewList(
			String type, String keyword, int startRow, int endRow) {
		
		String sqlReviewList = "SELECT * FROM (SELECT r_no, r_subject,"
				+ " r_content, r_date, r_score, p_no, r_nickname, r_image FROM"
				+ " (SELECT * FROM review WHERE " + type + " LIKE ?"
				+ " ORDER BY r_no DESC)) WHERE r_no >= ? AND r_no <= ?";
		
		ArrayList<Review> ReviewList = null;
		try{
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlReviewList);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
					
				ReviewList = new ArrayList<Review>();
					
				do {
					Review review = new Review();
					review.setrNo(rs.getInt("r_no"));
					review.setrSubject(rs.getString("r_subject"));
					review.setrContent(rs.getString("r_content"));
					review.setrDate(rs.getDate("r_date"));
					review.setrScore(rs.getString("r_score"));
					review.setrNickname(rs.getString("r_nickname"));
					review.setrImage(rs.getString("r_image"));
					review.setpNo(rs.getInt("p_no"));
						
					ReviewList.add(review);
						
				} while(rs.next());
			}
		} catch(Exception e) {
			e.printStackTrace();
				
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		System.out.println("reviewList" + ReviewList);
		return ReviewList;
		}
	
	/* 게시 글 상세 보기 요청 시 호출되는 메서드*/
	public Review getReview(int no) {
		String reviewSql = "SELECT * FROM reivew WHERE r_no=?";
		Review review = null;
		
		try{
				
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(reviewSql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				review = new Review();				
				review.setrNo(rs.getInt("r_no"));
				review.setrContent(rs.getString("r_content"));
				review.setrSubject(rs.getString("r_subject"));
				review.setrDate(rs.getDate("r_date"));
				review.setrScore(rs.getString("r_score"));
				review.setrNickname(rs.getString("r_nickname"));
			}
			
			
			DBManager.commit(conn);
			
		} catch(Exception e) {
			
			DBManager.rollback(conn);
			
			System.out.println("ReviewDao - getReivew(no, state)");
			e.printStackTrace();			
		} finally {			
			
			DBManager.close(conn, pstmt, rs);
		}		
		
		return review;
	}
	
	/* 한 페이지에 보여 질 게시 글 리스트 요청시 호출되는 메소드
	 * 요청한 페이지에 해당하는 게시 글 리스트를 DB에서 읽어와 반환하는 메소드
	 **/
	public ArrayList<Review> ReviewList(int startRow, int endRow) {
		
		String sqlReviewList ="SELECT * FROM (SELECT r_no, r_subject,"
				+ " r_content, r_date, r_score, p_no, r_nickname, r_image FROM"
				+ " (SELECT * FROM review ORDER BY no DESC))"
				+ " WHERE r_no >= ? AND r_no <= ?";
		
 
		ArrayList<Review> ReviewList = null;
		
		try {	
			
			System.out.println("test" + startRow);
			System.out.println("test2" + endRow);
			System.out.println("test3" + sqlReviewList);
		
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlReviewList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				ReviewList = new ArrayList<Review>();
				
				do {
					Review review = new Review();
					review.setrNo(rs.getInt("r_no"));
					review.setrSubject(rs.getString("r_subject"));
					review.setrContent(rs.getString("r_content"));
					review.setrDate(rs.getDate("r_date"));
					review.setrScore(rs.getString("r_score"));
					review.setrImage(rs.getString("r_image"));
					review.setrNickname(rs.getString("r_nickname"));
					
					ReviewList.add(review);
					
					
					
					
				} while(rs.next());
			}
		} catch(Exception e) {
			System.out.println("에러");
			e.printStackTrace();
			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
		return ReviewList;
	}
	
	/* 게시 글쓰기 요청시 호출되는 메서드
	 * 게시 글을 작성하고 등록하기 버튼을 클릭하면 게시 글을 DB에 추가하는 메서드 
	 **/
	public void insertReview(Review review) {
		
		String sqlInsert = "INSERT INTO review(r_no, r_subject, r_content,"
				+ " r_date, r_score, r_nickname ,p_no, r_image) "
				+ " VALUES(REVIEW_r_no.nextval, ?, ?, SYSDATE, ?, ?, ?)";
		
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, review.getrSubject());
			pstmt.setString(2, review.getrContent());			
			pstmt.setString(3, review.getrImage());
			pstmt.setString(4, review.getrNickname());
			pstmt.setInt(5, review.getpNo());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
	
	/* 게시 글 삭제 요청 시 호출되는 메서드
	* no에 해당 하는 게시 글을 DB에서 삭제하는 메서드
	**/
	public void deleteReview(int no) {
		
		String sqlDelete = "DELETE FROM review WHERE r_no=?";
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	/* 게시 글 수정 요청시 호출되는 메서드
	* 게시 글을 수정하고 수정하기 버튼을 클릭하면 게시 글을 DB에 수정하는 메서드
	**/
	public void updateReview(Review review) {
		
		String sqlNoImageUpdate = "UPDATE review set r_subject=?, r_content=?, "
				+ " r_date=SYSDATE, r_score, r_nickname, p_no, r_image=? WHERE r_no=?";
		String sqlImageUpdate = "UPDATE review set r_subject=?, r_content=?, "
		+ " r_date=SYSDATE, r_score, r_nickname, p_no, r_image=? WHERE r_no=?";
		
		try {
			conn = DBManager.getConnection();
			
			// 이미지 업로드일 경우와 그렇지 않은 경우를 구분해서 처리
				if(review.getrImage() == null) {
					pstmt = conn.prepareStatement(sqlNoImageUpdate);
					pstmt.setString(1, review.getrSubject());
					pstmt.setString(2, review.getrContent());			
					pstmt.setDate(3, review.getrDate());
					pstmt.setInt(4, review.getrNo());
					pstmt.setInt(5, review.getpNo());
							
				} else {
					pstmt = conn.prepareStatement(sqlImageUpdate);
					pstmt.setString(1, review.getrSubject());
					pstmt.setString(2, review.getrContent());			
					pstmt.setDate(3, review.getrDate());
					pstmt.setString(4, review.getrImage());
					pstmt.setInt(5, review.getrNo());
					pstmt.setInt(6, review.getpNo());
				}
				pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	} 
	
	// 특정 게시 글에 해당하는 댓글 리스트를 반환하는 메소드
	public ArrayList<ReviewReply> ReviewReplyList(int rrNo) {
				
		String replyListSql = "SELECT * FROM rieview_reply WHERE review_reply_no = ?"
					+ "ORDER BY r_no DESC";
				
		ReviewReply reply = null;
		ArrayList<ReviewReply>ReviewreplyList = null;
				
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(replyListSql);			
			pstmt.setInt(1, rrNo);								
			rs = pstmt.executeQuery();
					
			ReviewreplyList = new ArrayList<ReviewReply>();
					
			while(rs.next()) {
						
				reply = new ReviewReply();
				reply.setRrNo(rs.getInt("review_reply_no"));
				reply.setRrContent(rs.getString("review_reply_content"));
				reply.setRrDate(rs.getTimestamp("review_reply_date"));
				reply.setRrNickname(rs.getString("review_reply_nickname"));
				ReviewreplyList.add(reply);
			}
		} catch(Exception e) {
			System.out.println("ReviewDao - ReviewreplyList(review_reply_no)");
			e.printStackTrace();				
		} finally {				
			DBManager.close(conn, pstmt, rs);
		}			
		return ReviewreplyList;
			}
			
	
	/* 특정 게시 글에 대한 댓글 쓰기 요청시 호출되는 메소드
	 * 댓글 쓰기 요청이 들어오면 ReplyWriteAction 클래스에서 
	 * 호출되어 사용자가 입력한 댓글을 DB에 추가하는 메소드 
	 **/
	public void addReviewReply(ReviewReply reply) {
		
		String replyInsertSql = "INSERT INTO review_reply"
				+ "VALUES(review_reply_review_no.nextval, ?, SYSDATE, ?)";
			
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(replyInsertSql);
			pstmt.setInt(1, reply.getRrNo());
			pstmt.setString(2, reply.getRrContent());
			pstmt.setString(3, reply.getRrNickname());			
				
			// DB에 쿼리를 전송하여 댓글 추가 작업을 완료한다.
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("ReviewDao - addReviewReply(review_reply)");
			e.printStackTrace();	
		} finally {			
			// DBManager를 이용해 DB 작업에 사용된 자원을 해제 한다.
			DBManager.close(conn, pstmt, rs);
		}
	}	
		
	/* 특정 게시 글에 대한 댓글 수정 요청시 호출되는 메소드
	 * 댓글 수정 요청이 들어오면 ReplyUpdateAction 클래스에서 
	 * 호출되어 사용자가 입력한 댓글을 DB에 추가하는 메소드 
	 **/
	public void updateReviewReply(ReviewReply reply) {
		
		String replyUpdateSql = "UPDATE review_reply SET review_reply_content=?,"
				+ " review_reply_date=TIMESTAMP WHERE review_reply_no=? ";
			
		try {			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(replyUpdateSql);
			pstmt.setString(1, reply.getRrContent());
			pstmt.setInt(2, reply.getRrNo());			
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("ReviewDao - updateReviewReply(reply)");
			e.printStackTrace();			
		} finally {			
			DBManager.close(conn, pstmt, rs);
		}
	}
		
		/* 특정 게시 글에 대한 댓글 삭제 요청시 호출되는 메소드
		 * 댓글 삭제 요청이 들어오면 ReplyDeleteAction 클래스에서 
		 * 호출되어 사용자가 입력한 댓글을 DB에 추가하는 메소드 
		 **/
		public void deleteReviewReply(ReviewReply reply) {
			
			String replyDeleteSql = "DELETE FROM review_reply WHERE review_reply_no = ?";
			
			try {			
				// DBManager을 이용해 DBCP로 부터 Connection을 대여한다.
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(replyDeleteSql);
				pstmt.setInt(1, reply.getRrNo());					
				pstmt.executeUpdate();
			} catch(Exception e) {
				System.out.println("ReviewDao - deleteReviewReply(review_reply)");
				e.printStackTrace();			
			} finally {			
				DBManager.close(conn, pstmt);
			}
		}		
}
	
	

