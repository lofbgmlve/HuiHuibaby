package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.project.vo.Product;
import com.project.vo.Reply;

public class ProductDao {

	
	
		private Connection conn; 
		
		private PreparedStatement pstmt;
		
		private ResultSet rs; 
		
		private static DataSource ds;
		
	
		
		
		public ProductDao() {
			
			
			try {
				
				Context initContext = new InitialContext();
				Context envContext = (Context)initContext.lookup("java:comp/env");
				ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
				
			}catch(NamingException e) {
				System.out.println("ProductDao - NamingException-----");
				e.printStackTrace();
			}
			
		}  // ProductDao
		
		
		
		
		
		
		
		// DB 등록된 게시글 전체 읽어오기. 
		public ArrayList<Product> productList(){
			
			
			String sqlProductList ="SELECT * FROM product ORDER BY p_no DESC";
			ArrayList<Product> productList = null; 
			
			
			try {
				
				
				conn= ds.getConnection();
				pstmt=conn.prepareStatement(sqlProductList);
				rs = pstmt.executeQuery();
				
				productList = new ArrayList<Product>();
				
				while(rs.next()) {
					
					Product p = new Product();
					p.setNo(rs.getInt("p_no"));
					p.setTitle(rs.getString("p_title"));
					p.setName(rs.getString("p_name"));
					p.setPart(rs.getString("p_part"));
					p.setStaus(rs.getString("p_staus"));
					p.setWay(rs.getString("p_way"));
					p.setDate(rs.getDate("p_date"));
					p.setPrice(rs.getInt("p_price"));
					p.setPro(rs.getString("p_pro"));
					p.setCondition(rs.getString("p_condition"));
					p.setFile1(rs.getString("p_file1"));
					p.setFile2(rs.getString("p_file2"));
					p.setFile3(rs.getString("p_file3"));
					p.setDetail(rs.getString("p_detail"));
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
			
		}// productList 
		
		
		
		
		
		
		
		
		
		//게시글 디테일내용 가져오기 
		public Product getProduct(int no) {
			
			
			String ProductSql = "SELECT * FROM product WHERE p_no= ?";
			Product product=null;
			
			
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(ProductSql);
				
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
			
				
				if(rs.next()) {
					
					product = new Product();
					product.setNo(rs.getInt("p_no"));
					product.setTitle(rs.getString("p_title"));
					product.setName(rs.getString("p_name"));
					product.setPart(rs.getString("p_part"));
					product.setStaus(rs.getString("p_staus"));
					product.setWay(rs.getString("p_way"));
					product.setDate(rs.getDate("p_date"));
					product.setPrice(rs.getInt("p_price"));
					product.setPro(rs.getString("p_pro"));
					product.setCondition(rs.getString("p_condition"));
					product.setFile1(rs.getString("p_file1"));
					product.setFile2(rs.getString("p_file2"));
					product.setFile3(rs.getString("p_file3"));
					product.setDetail(rs.getString("p_detail"));
					product.setNickname(rs.getString("p_nickname"));
					
					
				}
			}catch(SQLException e ) {
				e.printStackTrace();
				System.out.println("ProductDao - getProduct");
			
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
			
			
			return product;
		}// getProduct 
		
		
		
		
		
		
		
		
		
			// 상품등록을 위한 메서드 
			public void insertProduct(Product product) {
			
			String sqlInsert ="INSERT INTO product(p_no, p_title, p_name, p_part, p_staus, p_way, p_date, p_price, p_pro, p_condition, p_file1, p_file2, p_file3, p_detail, p_nickname)"
					+ "        VALUES(product_seq.NEXTVAL,?,?, ?, ?, ?, SYSDATE, ?, ? , ?, ?, ?, ? ,?, ? ) ";
		
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sqlInsert);
				
				
				pstmt.setString(1, product.getTitle());
				pstmt.setString(2, product.getName());
				pstmt.setString(3, product.getPart());
				
				pstmt.setString(4,product.getStaus());
				pstmt.setString(5,product.getWay());
				
				pstmt.setInt(6,product.getPrice());
				pstmt.setString(7, product.getPro());
				pstmt.setString(8,product.getCondition());
				pstmt.setString(9, product.getFile1());
				pstmt.setString(10, product.getFile2());
				pstmt.setString(11, product.getFile3());
				pstmt.setString(12,product.getDetail());
				pstmt.setString(13,product.getNickname());
				
					
				pstmt.executeUpdate();
				
			} catch(Exception e) {
				System.out.println("ProductDao - insertProduct");
				e.printStackTrace();
			
			} finally{
				
				DBManager.close(conn, pstmt);
			}
			
			
			
		}// insertProduct
		
		
			
			
			
			
			
			
			
			//  상품 등록 - 수정하기 update ! 
			public void updateProduct(Product product) {
				String sqlUpdate ="UPDATE product set p_title=?, p_name=?, p_pro=?,  "
						+" p_part=?, p_condition=?, p_way=?, p_price=?, p_file1=? ,p_file2=?, p_file3 =?, p_detail=?, p_nickname=? WHERE p_no = ? ";
				
				try {
					conn =ds.getConnection();
					pstmt = conn.prepareStatement(sqlUpdate);
					
					pstmt.setString(1, product.getTitle());
					pstmt.setString(2, product.getName());
					pstmt.setString(3, product.getPro());
					pstmt.setString(4, product.getPart());
					pstmt.setString(5, product.getCondition());
					pstmt.setString(6, product.getWay());
					pstmt.setInt(7, product.getPrice());
					pstmt.setString(8, product.getFile1());
					pstmt.setString(9, product.getFile2());
					pstmt.setString(10, product.getFile3());
					pstmt.setString(11, product.getDetail());
					pstmt.setString(12, product.getNickname());
					pstmt.setInt(13, product.getNo());
					
					pstmt.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("ProductDao ---- updateForm------");
					
				}finally {
					DBManager.close(conn, pstmt);
				}
				
			}// update
			
			
			
			
			
			
			// 삭제하기 ! 
			public void deleteProduct(int no) {
				
				String sqlDelete="DELETE FROM product WHERE p_no=?";
				
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sqlDelete);
					pstmt.setInt(1, no);
					
					pstmt.executeUpdate();
					
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("ProductDao ------- deleteProduct");
				
				}finally {
					DBManager.close(conn, pstmt);
				}
				
				
			}  // delete
			
			
			// 0315 추가 
			// part 별로 조회하기 
			public ArrayList<Product>productList(String part){
				String partSql = "SELECT * FROM product WHERE p_part = ?";
				ArrayList<Product>pList = null; 
				
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(partSql);
					pstmt.setString(1,part);
					rs= pstmt.executeQuery();
					
					pList = new ArrayList<Product>();
					while(rs.next()) {
					
						Product product = new Product();
						product.setNo(rs.getInt("p_no"));
						product.setTitle(rs.getString("p_title"));
						product.setName(rs.getString("p_name"));
						product.setPart(rs.getString("p_part"));
						product.setStaus(rs.getString("p_staus"));
						product.setWay(rs.getString("p_way"));
						product.setDate(rs.getDate("p_date"));
						product.setPrice(rs.getInt("p_price"));
						product.setPro(rs.getString("p_pro"));
						product.setCondition(rs.getString("p_condition"));
						product.setFile1(rs.getString("p_file1"));
						product.setFile2(rs.getString("p_file2"));
						product.setFile3(rs.getString("p_file3"));
						product.setDetail(rs.getString("p_detail"));
						product.setNickname(rs.getString("p_nickname"));
						pList.add(product);
					}
					return pList;
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("ProductDao - ---- productList - part");
				}finally {
					DBManager.close(conn, pstmt, rs);
				}
				return pList;
			}

	
	
	
	
	
	
	
	// part별 글 카운팅 
	public int getProductCount(String part) {
		System.out.println("part  -- " + part );
		String partCountsql = "SELECT COUNT(*) FROM product WHERE p_part=?";
		
		int count = 0 ; 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(partCountsql);
			pstmt.setString(1, part);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ProductDao -- partCount -- ");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count ;
	}
	
	
	// 파트별  페이징 ! 
	public ArrayList<Product>productList( String part, int startRow , int endRow){
		String partListsql = "SELECT * FROM (SELECT ROWNUM num, sub_pd.* FROM"
				+ "(SELECT *FROM product WHERE p_part = ? ORDER BY p_no DESC ) sub_pd)"
				+ "WHERE num >= ?  AND  num <=? ";
		
		ArrayList <Product>productList = null; 
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(partListsql);
			pstmt.setString(1, part);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productList = new ArrayList<Product>();
				
				do {
					Product product = new Product();
					product.setNo(rs.getInt("p_no"));

					product.setTitle(rs.getString("p_title"));
					product.setName(rs.getString("p_name"));
					product.setPart(rs.getString("p_part"));
					product.setStaus(rs.getString("p_staus"));
					product.setWay(rs.getString("p_way"));
					product.setDate(rs.getDate("p_date"));
					product.setPrice(rs.getInt("p_price"));
					product.setPro(rs.getString("p_pro"));
					product.setCondition(rs.getString("p_condition"));
					product.setFile1(rs.getString("p_file1"));
					product.setFile2(rs.getString("p_file2"));
					product.setFile3(rs.getString("p_file3"));

					product.setDetail(rs.getString("p_detail"));
					product.setNickname(rs.getString("p_nickname"));

					productList.add(product);
					
				}while(rs.next());
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ProductDao - - ---- part -- startRow , endRoew ");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return productList;
	}
	/////////////////////////// 			
			
			
			
			
		
		// 페이징네비게이션을 위한 -  검색 글 카운트 
		public int getProductCount(String type,String keyword) {
		System.out.println(type+" - " +keyword);
		
		
		String sqlCount = "SELECT COUNT(*) FROM product WHERE "
				+ type + " LIKE '%' || ? || '%' ";
		
		int count = 0; 
		
		try {
			
			conn= ds.getConnection();
			pstmt=conn.prepareStatement(sqlCount);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ProductDao ----getProductCount = type , keyword");
	
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}

	
	
	
	
	
	 // 전체게시글 카운트  
	public int getProductCount() {
		String sqlCount="SELECT COUNT (*) FROM product";
		int count = 0;
		
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sqlCount);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("getProductCount   - ListCount");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	
	
	
	
	// 페이징 처리 - 리스트 뽑기 
	public ArrayList<Product>productList(int startRow, int endRow){
		String slqProductList = "SELECT * FROM "
				+ "(SELECT ROWNUM num, sub_pd.* FROM "
				+ "	(SELECT * FROM product ORDER BY p_no DESC) sub_pd) "
				+ "WHERE num >= ? AND num <= ?";
		
		ArrayList<Product>productList = null; 
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(slqProductList);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productList = new ArrayList<Product>();
				
				do {
					Product product = new Product();
					product.setNo(rs.getInt("p_no"));
					product.setTitle(rs.getString("p_title"));
					product.setName(rs.getString("p_name"));
					product.setPart(rs.getString("p_part"));
					product.setStaus(rs.getString("p_staus"));
					product.setWay(rs.getString("p_way"));
					product.setDate(rs.getDate("p_date"));
					product.setPrice(rs.getInt("p_price"));
					product.setPro(rs.getString("p_pro"));
					product.setCondition(rs.getString("p_condition"));
					product.setFile1(rs.getString("p_file1"));
					product.setFile2(rs.getString("p_file2"));
					product.setFile3(rs.getString("p_file3"));
					
					product.setDetail(rs.getString("p_detail"));
					product.setNickname(rs.getString("p_nickname"));
					
					productList.add(product);
				
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(" productList - startRow , endRow");
		
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return productList;
		
	}
	
	
	
	
	
	
	// 검색 결과물 리스트 뽑기 
	public ArrayList<Product>searchList(
			String type, String keyword, int startRow, int endRow){
		String sqlSearchList = "SELECT * FROM "
				+ "    (SELECT ROWNUM num, sub_pd.* FROM "
				+ "        (SELECT * FROM product WHERE "+ type + " LIKE ?  ORDER BY p_no DESC) sub_pd) "
				+ "WHERE num >= ? AND num <= ?";
				
		ArrayList<Product>productList = null;  
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sqlSearchList);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				
				productList=new ArrayList<Product>();
				
				do {
					Product product = new Product();
					product.setNo(rs.getInt("p_no"));
					
					product.setTitle(rs.getString("p_title"));
					product.setName(rs.getString("p_name"));
					product.setPart(rs.getString("p_part"));
					product.setStaus(rs.getString("p_staus"));
					product.setWay(rs.getString("p_way"));
					product.setDate(rs.getDate("p_date"));
					product.setPrice(rs.getInt("p_price"));
					product.setPro(rs.getString("p_pro"));
					product.setCondition(rs.getString("p_condition"));
					product.setFile1(rs.getString("p_file1"));
					product.setFile2(rs.getString("p_file2"));
					product.setFile3(rs.getString("p_file3"));
					
					product.setDetail(rs.getString("p_detail"));
					product.setNickname(rs.getString("p_nickname"));
					
					productList.add(product);
				}while(rs.next());
			
			}
			
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("searchList - type, keyword, startRow, endRow");
			
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return productList;
		
	}//searchList 
	
	
	
	
	
	// 댓글리스트 읽어오기
	public ArrayList<Reply> getReplyList(int pNo){
		String replySql = "SELECT * FROM product_reply WHERE p_no=? ORDER BY reply_no DESC";
		Reply reply = null; 
		ArrayList<Reply>replyList = null; 
		
		try {
			conn = ds.getConnection();
			pstmt =conn.prepareStatement(replySql);
			pstmt.setInt(1, pNo);
			
			rs= pstmt.executeQuery();
			replyList= new ArrayList<Reply>();
			
			while(rs.next()) {
				reply =new Reply();
				reply.setNo(rs.getInt("reply_no"));
				reply.setContent(rs.getString("reply_content"));
				reply.setDate(rs.getTimestamp("reply_date"));
				reply.setNickname(rs.getString("reply_nickname"));
				reply.setpNo(rs.getInt("p_no"));
				
				replyList.add(reply);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ProductDao ------ getReplyList");
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return replyList;
	
	}// getReplyList 
	
	
	
	// 댓글 쓰기 ! 
	public void addReply(Reply reply) {
		
		String replyInsertSql ="INSERT INTO product_reply VALUES (product_reply_reply_no.nextval, ? ,current_timestamp , ?, ?)";
		
		try {
			conn=ds.getConnection();
			pstmt =conn.prepareStatement(replyInsertSql);
			
			pstmt.setString(1,reply.getContent());
			pstmt.setString(2, reply.getNickname());
			pstmt.setInt(3,reply.getpNo());
			
			pstmt.executeUpdate();
			
		}catch(Exception e ) {
			e.printStackTrace();
			System.out.println("ProductDao  ------ addReply");
		
		}finally {
			DBManager.close(conn, pstmt);
		}
		
	}

	
}// end
