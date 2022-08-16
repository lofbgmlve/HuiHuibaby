<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<article>
<div id="pDiv">
   <div id="body">
      <h2 id="pH2">상품 수정하기</h2>
      <form name="updateForm" id="updateForm" action="updateProcess.mvc" method="post" enctype='multipart/form-data'>
      
         <input type="hidden" name="no" id="sNo"  value="${product.no }"/> 
         <input type="hidden" name="pageNum" value="${pageNum }" />
      
      <c:if test="${searchOption }">
            <input type="hidden" name="type" value="${type }"/>
            <input type="hidden" name="keyword" value="${keyword }">
      </c:if>
      <table id="detailT">
    <tr>
       <td class = "top_photo">
       <img id="main1" src="product_img/${product.file1 }">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <img id="photo1" alt="사진" src="product_img/${product.file2 }" onerror="this.style.display='none'" >
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <img id="photo2" alt="사진" src="product_img/${product.file3 }" onerror="this.style.display='none'">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       
       <td class = "top_detail">
            작성자: <input type="text" name="nickname" id="pnickname" value="${product.nickname }"><br/><br/>
          제목 : <input type="text" id="ptitle" name="title" value="${product.title }"><br/><br/>
          제품명 : <input type="text" id="pname"name="name" value="${product.name }"><br/><br/>
          제조사 : <input type="text" id="ppro" name="pro" value="${product.pro }"><br/><br/>
          상품분류: <select id="ppart" name="part" > 
         <option >-선택해주세요-</option>
         <option ${product.part =="1" ? "selected" : "" } value="1"> 수유*위생*스킨-</option>
         <option ${product.part =="2" ? "selected" : "" } value="2">의류패션잡화-</option>
         <option ${product.part =="3" ? "selected" : "" } value="3">어린이도서*장난감-</option>
         <option ${product.part =="4" ? "selected" : "" } value="4">유모차*관련용품-</option>
         </select>
         <br/><br/>
          제품상태 :<br/>
          <input type="radio" id="pcondition" name="condition"  value="A" ${product.condition=="A" ? "checked" : "" }>새상품*미개봉
         <input type="radio" name="condition" value="B" ${product.condition=="B" ? "checked" : "" }>사용감 조금 있음
         <input type="radio" name="condition" value="C" ${product.condition=="C" ? "checked" : "" }>사용감 있음
         
         <br/><br/> 
         
       
          거래방법: 
          <input type="radio" id="pway" name="way" value="0"  ${product.way == "0" ? "checked" : "" } />직거래 
         <input type="radio" id="pway" name="way" value="00" ${product.way == "00" ? "checked" : "" } />택배 
         <input type="radio" id="pway" name="way" value="000" ${product.way == "000" ? "checked" : "" } />둘 다 가능
          <br/><br/>
          거래상태: <select name="staus" id="pstaus" >
          <option ${product.staus == "Y" ? "selected" : ""} value="Y" >거래가능</option>
          <option ${product.staus == "N" ? "selected" : ""} value="N">거래완료</option>
          </select >
           <br/><br/>
          판매가 : <input type="text" id="pprice" name="price" value="${product.price }">원
          <br/><br/>
            이미지 첨부: <input id="pfile" name="file1" type="file" value="${product.file1 }"> 
            <br/><small>(*파일1개 필수/파일최대3개)</small>
            <br/><br/>
            이미지 첨부: <input id="pfile2" name="file2" type="file" value="${product.file2 }"> 
            <br/><br/>
            이미지 첨부: <input id="pfile3" name="file3" type="file" value="${product.file3 }"> 
       </td>
       
    </tr>
    <tr>
       <td colspan = "2" class ="bottom" ><textarea  name="detail" id="pdetail"  cols="140" rows="12">${product.detail }</textarea><br/>
    </tr>
    
 </table>
    <span id="btnSpan">
    <input id="writeReset" type="reset" value="다시쓰기">
    <input  id="update1"  type="submit" value="수정하기" >
    <c:if test="${!searchOption }">
    <input id="btnList"  type="button" value="목록보기" onclick="location.href='productList.mvc?pageNum=${pageNum}'">
    </c:if>
    <c:if test="${searchOption }">
       <input id="btnList"  type="button" value="목록보기" onclick="location.href='productList.mvc?pageNum=${pageNum }&type=${type }&keyword=${keyword }'">
    </c:if>
    </span>
      </form>
      </div>
      </div>
</article>