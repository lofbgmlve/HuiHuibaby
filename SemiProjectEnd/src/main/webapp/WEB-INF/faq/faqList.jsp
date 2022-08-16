<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <head>
	<link href="faq.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.listTable{
			margin: 50px auto;
			margin-top: 150px;
			text-align: left; 
			padding-right: 15px;	
			border-bottom: 1px dotted #787878; 
			width: 700px;
		}
		
		.faqFaq_title searchForm{
			padding: 10px 20px 10px 20px;
		}
		
		td.listWrite{
			display: inline;
		}
		
		.faqFaq_title{
			font-size: 14px;
			color: rgb(90, 165, 130);
			height: 50px;
			margin-top: 100px;
			border-top: 1px solid #000;
			border-bottom: 1px dashed #ddd;
		}
		
		.listThFaq_title{
			width: 700px;	  
			background: #white; 
			text-align: center; 
			padding: 10px 20px 10px 20px;
		}
		
		.listThMg_id{
			width: 700px;	  
			background: #white; 
			text-align: right; 
			padding: 10px 20px 10px 20px;
		}
		
		.listThFaq_title, .listThMg_id{
			border-top: 1px solid #000;
			border-bottom: 1px dashed #ddd;
		}
		
		td.listTdMg_id{
			text-align: right;
		}
		
		td.listTdMg_id, td.listTdFaq_title{
			height: 45px;
		}
		.listPage{
		
		}
	</style>
</head> -->
<article>
<table class="listTable">
	<tr><td class="faqFaq_title" colspan="5"><h2>사용자들이 자주 찾는 질문을 확인하세요.</h2></td></tr>
	<tr>
		<td colspan="5">
			<form name="searchForm" id="searchForm"  >
				<select name="type">						
					<option value="faq_title">제목</option>
					<option value="mg_id">작성자</option>
					<option value="faq_content">내용</option>
				</select>
				<input type="text" name="keyword" size="40px" placeholder="검색어를 입력하세요."/>
				<input type="submit" value="검색" />
			</form>
		</td>
	</tr>
	<c:if test="${ searchOption }">
		<tr>
			<td colspan="3" class="listWrite"><a href="faqwriteForm.mvc">글쓰기</a></td>
		</tr>
	</c:if>
	<tr>
		<th class="listThFaq_title">제목</th>
		<th class="listThMg_id">작성자</th>
	</tr>
	<c:if test="${ searchOption and not empty fList }">
		<c:forEach var="f" items="${ fList }" varStatus="status">
		<tr class="listTr">
			<td class="listTdFaq_title">
				<a href="faqDetail.mvc?no=${ f.faq_no }&pageNum=${ currentPage }
					&type=${ type }&keyword=${ keyword }">${ f.faq_title }</a>
			</td>
			<td class="listTdMg_id">${ f.mg_id }</td>					
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5" class="listPage">
				<c:if test="${ startPage > pageGroup }">
					<a href="faqList.mvc?pageNum=${ startPage - pageGroup }
						&type=${ type }&keyword=${ keyword }">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
					<c:if test="${ i == currentPage }">
						[ ${ i } ]
					</c:if>
					<c:if test="${ i != currentPage }">
						<a href="faqList.mvc?pageNum=${ i }&type=${ type }
							&keyword=${ keyword }">[ ${ i } ]</a>	
					</c:if>
				</c:forEach>
				<c:if test= " ${ endPage < pageCount } " >
					<a href= "faqList.mvc ?pageNum= ${ startPage + pageGroup } 
						&type=${ type }&keyword=${ keyword }">[다음]</a>"
				</c:if>
			</td>	
		</tr>
	</c:if>
	<c:if test= " ${ not searchOption and not empty fList } " >
		<c:forEach var= "f" items= " ${ fList } " varStatus= "status" > 
			<tr class="listTr">
				<td class= "listTdFaq_no" >${ f.faq_no  }</td> 
				<td class= "listTdFaq_title">
					<a href="faqDetail.mvc?no=
						${ f.faq_no } &pageNum= ${ currentPage }">${ f.faq_title }</a>
				</td>
				<td class="listTdMg_id">${ f.mg_id }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" class="listPage">
				<c:if test="${ startPage > pageGroup }">
					<a href="faqList.mvc?pageNum=${ startPage - pageGroup }">
						[이전]</a>
				</c:if>
				<c:forEach var= "i" begin= " ${ startPage } " end= " ${ endPage } " > 
					<c:if test= " ${ i == currentPage } " > 
						[ ${ i } ] 
					</c:if> 
					<c:if test= " ${ i != currentPage } " > 
						<a href= "faqList.mvc?pageNum= ${ i } " >[ ${ i } ]</a> 
					</c:if> 
				</c:forEach>
				<c:if test= " ${ endPage < pageCount } " > 
					<a href= "faqList.mvc?pageNum= ${ startPage + pageGroup } " > 
						[다음]</a> 
				</c:if> 		
			</td>
		</tr>
	</c:if>  
	<c:if test= " ${ searchOption and empty fList } " > 
		<tr> 
			<td colspan= "5" class= "listTdSpan" > 
				"${ keyword }"가 포함된 게시 글이 존재하지 않습니다.</td> 
		</tr> 
	</c:if>
	 <c:if test= " ${ not searchOption and empty fList } " > 
	 	<tr> 
	 		<td colspan= "5" class= "listTdSpan" >게시 글이 존재하지 않습니다.</td> 
	 	</tr> 
	 </c:if>
</table>
</article>


