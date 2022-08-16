<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.project.dao.*,com.project.vo.*" %>        
<%@ page import="java.sql.*, java.io.*, java.net.*" %>
<%
	/*
	
	String sNo = request.getParameter("no");
	String nickname = request.getParameter("nickname");
	int no = Integer.parseInt(sNo);
	
	ProductDao  dao = new ProductDao();
	boolean isNameCheck = dao.isNameCheck(sNo, nickname);
	
	if(! isNameCheck){
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append(" alert('*** 가 맞지 않습니다.');");
		sb.append(" history.back();");
		sb.append("</script>");
		
		out.println(sb.toString());
		System.out.println("*** 맞지 않음");
		return;
	}
 
	dao.deleteProduct(no);
	
	response.sendRedirect("productList.jsp");
	*/
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>