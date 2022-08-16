<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link type="text/css" href="css/product.css" rel="stylesheet">
	<link type="text/css" href="css/user.css" rel="stylesheet">
	<link type="text/css" href="css/header.css" rel="stylesheet">
	<link type="text/css" href="css/notice.css" rel="stylesheet">
	<link type="text/css" href="css/faq.css" rel="stylesheet">

	<script src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/formcheck.js"></script>
	<script type="text/javascript" src="js/user.js"></script>
	<script src="js/reply.js"></script>
	<script src="js/header.js"></script>
	<script type="text/javascript" src="js/communityFormcheck.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<%@ include file="pages/header.jsp" %>
		<c:if test="${ not empty param.body }">
			<jsp:include page="${ param.body }" />
		</c:if>
	</div>
</body>
</html>