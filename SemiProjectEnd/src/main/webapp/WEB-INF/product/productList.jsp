<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="listForm">
 <form name="searchForm" id="psearchForm" method="post">
      <span ><select name="type" id="type" >
         <option value="p_nickname">작성자</option>
         <option value="p_detail">내용</option>
         <option value="p_title">제목</option>
   </select ></span>
         <span><input type="text" name="keyword" id="keyword"></span>
         <span><input type="submit"  value="검색"></span>
   </form>
   
   <br/><br/>
     <!-- 검색 -->
   <c:if test="${searchOption }">
         <span id="kSpan">"${keyword}" 검색결과</span>
         <span id="pList1"><a href="productList.mvc" id="pList1_click">전체보기</a></span>   
   <span id="writeForm" ><a href="writeForm.mvc" id="writeForm_click"  >상품등록</a></span>
   </c:if>
   <c:if test="${not searchOption }">
         <span><a href="writeForm.mvc"></a></span>
         <span id="pList1"><a href="productList.mvc" id="pList1_click">전체보기</a></span>   
   <span id="writeForm" ><a href="writeForm.mvc" id="writeForm_click"  >상품등록</a></span>
   </c:if>   
  
   <c:if test="${searchOption and not empty pList}">            
         <c:forEach var="p" items="${pList }" varStatus="status">
            <ul class="product1">
               <li>no  ${p.no }</li>
               <li><a href="productDetail.mvc?no=${p.no }&pageNum=${currentPage}"><img id="fileImg" src="product_img/${p.file1}"></a></li>
               <li >제목 : ${p.title }</li>
               <li>작성자 : ${p.nickname }</li>
               <li>제품분류: ${p.part=="1" ? "수유*위생*스킨" : p.part=="2" ? "패션의류잡화" : p.part =="3" ?  "아동도서*장난감" : "유모차*관련용품"}</li>
             <li>거래방식: ${p.way=="0" ? "직거래" : p.way=="00" ? "택배"  : "둘 다 가능"}</li>
               <li>거래상태: ${p.staus=="N" ? "거래완료" : "거래가능"}</li>
               <li>등록일: ${p.date }</li>
            </ul>
         </c:forEach>
         <!-- 페이지 네비게이션 -->
         <div class="pageNav">
         <c:if test="${startPage>pageGroup }">
            <a href="productList.mvc?pageNum=${startPage-pageGroup }
            &type=${type}&keyword=${keyword}">[ 이전 ]</a>
            </c:if>
            <c:forEach var="i" begin="${startPage }" end="${endPage }">
                  <c:if test="${i==currentPage }">
                  [ ${i } ]
                  </c:if>
                  <c:if test="${i != currentPage }">
                  <a href="productList.mvc?pageNum=${i }&type=${type}
                  &keyword=${keyword}">[ ${i } ]</a>
                  </c:if>
            </c:forEach>   
            <c:if test="${endPage<pageCount }">
               <a href="productList.mvc?pageNum=${startPage +pageGroup }
               &type=${type}&keyword=${keyword}">[ 다음 ]</a>
            </c:if>
      </div>            
   </c:if>
     <div> 
   <c:if test="${ not searchOption and not empty pList }">   
         <c:forEach var="p" items="${pList }" varStatus="status">
            <ul class="product1">
               <li>no  ${p.no }</li>
               <li><a href="productDetail.mvc?no=${p.no }&pageNum=${currentPage}"><img id="fileImg" src="product_img/${p.file1}"></a></li>
               <li >제목 : ${p.title }</li>
               <li>작성자 : ${p.nickname }</li>
               <li>제품분류: ${p.part=="1" ? "수유*위생*스킨" : p.part=="2" ? "패션의류잡화" : p.part =="3" ?  "아동도서*장난감" : "유모차*관련용품"}</li>
             <li>거래방식: ${p.way=="0" ? "직거래" : p.way=="00" ? "택배"  : "둘 다 가능"}</li>
               <li>거래상태: ${p.staus=="N" ? "거래완료" : "거래가능"}</li>
               <li>등록일: ${p.date }</li>
            </ul>
       </c:forEach>
       <div class="pageNav">
          <c:if test="${startPage >pageGroup }">
             <a href="productList.mvc?pageNum=${startPage-pageGroup }">[ 이전 ]</a>
          </c:if>     
        
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
             <c:if test="${i eq currentPage }">
                [ ${i } ]
             </c:if>
             
             <c:if test="${i !=currentPage }">
                <a href="productList.mvc?pageNum=${i }">[ ${i } ]</a>
             </c:if>
          </c:forEach>
          <c:if test="${endPage <pageCount }">
             <a href="productList.mvc?pageNum=${startPage +pageGroup }">[ 다음 ]</a>
          </c:if>
      </div>
   </c:if>
   </div>
   
      <!-- 검색글리스트 and 게시글리스트 있음 -->     
   <c:if test="${ searchOption and empty pList}">
         <span>"${keyword }"가 포함된 게시글이 존재하지 않습니다.</span>
   </c:if>      
   <c:if test="${not searchOption and empty pList }">
         <span>게시글이 존재하지 않습니다.</span>
   </c:if>
   </div>  