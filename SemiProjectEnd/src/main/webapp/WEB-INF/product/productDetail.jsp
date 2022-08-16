<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link type="text/css" href="css/product.css" rel="stylesheet">
<!-- 스크립트 reply.js 삭제 - index 에 있음 -->
<article>
<div id="pDiv">
	<div id="pForm">	
		<form name="checkForm" id="checkForm">
			<input type="hidden" name="no" value="${product.no }" id="sNo" /> 
			<input type="hidden" name="pageNum" value="${pageNum }" />
			
		<c:if test="${searchOption }">
				<input type="hidden" name="type" value="${type }">
				<input type="hidden" name="keyword" value="${keyword }">
		</c:if>
		</form>

			<h2 id="pH2">상품 상세 페이지</h2>
		<table id="datailT">
			
				<tr>
					<td class="top_photo"><a href="product_img/${product.file1 }"><img id="main1" src="product_img/${product.file1 }"></a> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="product_img/${product.file2 }"><img id="photo1" src="product_img/${product.file2} " onerror="this.style.display='none'"></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="product_img/${product.file3 }"><img id="photo2" src="product_img/${product.file3} " onerror="this.style.display='none'"></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						
					</td>
					<td class="top_detail">작성자 :${product.nickname }<br />
					<br /> 제목 :${product.title } <br />
					<br /> 제품명 : ${product.name }<br />
					<br /> 제조사 : ${product.pro }<br />
					<br /> 상품분류: ${product.part == "1"  ?  "수유*위생*스킨" : product.part=="2"  ?  "패션의류잡화" : product.part=="3" ? "도서*장난감" : "유모차*관련용품"}

						<br />
					<br /> 제품상태 : ${product.condition == "A" ? "새상품*미개봉" : product.condition == "B" ? "사용감 조금 있음" : "사용감 있음"}
						<br />
					<br /> 거래방법: ${product.way=="0" ? "직거래" : "00"  ? "택배" :"둘 다 가능"} <br />
					<br /> 판매가 : ${product.price}원 <br />
					<br />
					</td>
				</tr>
				<tr>
					<td colspan="2" class="bottom">${product.detail }</td>
					<td></td>
				</tr>

			</table>
			<span></span>
			<span id="btnSpan"> 
			<c:if test="${sessionScope.mNickname == product.nickname}">
				<span><input type="button" id="detailUpdate1" data-no="${product.no }" value="수정하기"></span>
			</c:if>	
			<c:if test="${sessionScope.mNickname == product.nickname}">
				<span><input id="detailDelete" type="button" value="삭제하기"></span>
			</c:if>
						<c:if test="${ ! searchOption }">
							<input type="button" value="목록보기" onclick="javascript:window.location.href='productList.mvc?pageNum=${pageNum}'" />
						</c:if>
						<c:if test="${searchOption }">
							<input type="button" value="목록보기" onclick="javascript:window.location.href='productList.mvc?pageNum=${pageNum}
							&type=${type }&keyword=${keyword }'">
						</c:if>
			</span>
			<p></p>
			<br/>
			<br/>
			<div id="replyForm">
				<form name="replyWriterForm" id="replyWriteForm">
					<input type="hidden" name="pNo" value="${product.no}">
					
					<%--
						nickname은 로그인 정보에서 읽어와 설정해야 - 현재는 오류가나서 product의 닉네임으로 했음
					 --%>
					<input type="hidden" name="nickname" value="${sessionScope.mNickname }">					
					<table id="replyWriteTable">
						<tr>
							<td id="replyWriteTitle" colspan="5">
							악의적인 댓글은 예고없이 삭제될 수 있으며, 글쓰기 제한과 아이디 삭제처리될 수 있습니다.</td>
						</tr>
						<tr>
							<td id="replyWriteContent">
								<textarea name="content" id="replyContent" rows="4" cols="143"></textarea>
							</td>
							<td id="replyWriteImage">
								<input type="image" src="product_img/write.png" id="replyWriteButton" alt="댓글입력">
							</td>
						</tr>
					</table>
				</form>
			</div>
			
			<table id="replyTable">
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="5"  id="replyTitle">이 게시글에 대한 댓글</td>
				</tr>
				
				<c:if test="${empty replyList }">
					<tr id="replyList">
						<td colspan="4" id="noReply">이 글에 대한 댓글이 존재하지 않습니다.</td>
						
					</tr>
				</c:if>
				
				<c:if test="${not empty replyList }">
				<tr id="replyList" >
					<td colspan="5">
						<table id="replyTable">
							<c:forEach var="reply" items="${replyList }">
								<tr id="reply_${reply.no }">
									<td>
									<div class="replyUser">
										<span class="member">${reply.nickname }</span>
									</div>
									<div>
										<span class="reply_date">
											<fmt:formatDate value="${reply.date }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
										<a href="#" class="modifyReply" data-na="${reply.no }">
										<img id="replyPt"src="product_img/updatepen.png" alt="수정하기"></a>
										<a href="#" class="deleteReply" data-no=${reply.no }>
										<img id="replyPt" src="product_img/delete.png" alt="삭제하기"></a>
									</div>
									<div class="replyContent" id="div_${reply.no }"><pre><span>${reply.content }</span></pre></div>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				
				</c:if>
			</table >
			<br/>
			<br/>
	</div>
	</div>
</article>