<%-- 마이페이지 뷰 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article>	
<br/><br/><br/><br/><br/>
	<h1 id="helloUser">${ sessionScope.mNickname } 님의 마이페이지</h1>	
	<br/>
	<!--  회윈이 등록한 상품-->
	<div class="myPageBox">
		<h2 id="myPageTitle">${sessionScope.mNickname} 님이 등록한 상품 목록</h2>
		<div>
		 <table id="myPageTale">
		 	<tr>
		 		<th id="Cententhead">no</th>
		 		<th id="Cententhead">상품명</th>
		 		<th id="Cententhead">등록일</th>
		 	</tr>
		 	<c:if test="${ not empty myProductList}">
		 	<c:forEach var="product" items="${myProductList }" varStatus="status">
		 	<tr>
				<td id="ContentTd">${status.count }</td>
		 		<td id="ContentTd"><a href="productDetail.mvc?no=${product.no }&pageNum=${startPage-pageGroup }">${product.title }</a></td>
		 		<td id="ContentTd">${product.date }</td>
		 	</tr>
		 	</c:forEach>
		 	</c:if>
		 	
		 	<c:if test="${ empty myProductList}">
		 	<tr>
		 		<td colspan="3">등록하신 상품이 없습니다.</td>
		 	</tr>
		 	</c:if>
		 	
		 </table>
		</div>
	</div>
	
		<br/>	<br/>
	
	<!--  회윈이 작성한 리뷰 -->
	<div class="myPageBox">
		<h2 id="myPageTitle">${ sessionScope.mNickname } 님이 작성한 리뷰 목록</h2>
		<div>
		 <table id="myPageTale">
		 	<tr>
		 		<th id="Cententhead">no</th>
		 		<th id="Cententhead">리뷰</th>
		 		<th id="Cententhead">등록일</th>
		 	</tr>
		 	<c:if test="${ not empty myReviewList}">
		 	<c:forEach var="review" items="${myReviewList }" varStatus="status">
		 	<tr>
		 		<td id="ContentTd">${status.count }</td>
		 		 
		 		<td><a href="reviewDetail.mvc?no=${review.rNo }">${review.rSubject }</a></td>
		 		
		 		<td id="ContentTd">${review.rDate }</td>
		 	</tr>
		 	</c:forEach>
		 	</c:if>
		 	<c:if test="${ empty myReviewList}">
		 	<tr>
		 		<td colspan="3" id="ContentTd">등록하신 리뷰가 없습니다.</td>
		 	</tr>
		 	</c:if>
		 </table>
		</div>
	</div>
	
	<br/>	<br/>
	
	<!--  회윈이 작성한 커뮤니티 게시 글 리스트 -->
	<div class="myPageBox">
		<h2 id="myPageTitle">${ sessionScope.mNickname } 님이 등록한 후기 목록</h2>
		<div>
		 <table id="myPageTale">
		 	<tr>
		 		<th id="Cententhead">no</th>
		 		<th id="Cententhead">후기</th>
		 		<th id="Cententhead">등록일</th>
		 	</tr>
		 	<c:if test="${ not empty myCommunityList}">
		 	<c:forEach var="community" items="${myCommunityList }" varStatus="status">
		 	<tr>
		 		<td id="ContentTd">${status.count }</td>
		 		
		 		<!-- 마이페이지에서 클릭하면 리뷰디테일로 이동 텍스트 복붙 부분 -->
		 		<td><a href="communityDetail.mvc?no=${community.communityNo }">${community.communitySubject }</a></td>
		 		
		 		<td id="ContentTd">${community.communityDate }</td>
		 	</tr>
		 	</c:forEach>
		 	</c:if>
		 	<c:if test="${ empty myCommunityList}">
		 	<tr>
		 		<td colspan="3" id="ContentTd">등록하신 후기가 없습니다.</td>
		 	</tr>
		 	</c:if>
		 </table>
		</div>
	</div>
	
</article>