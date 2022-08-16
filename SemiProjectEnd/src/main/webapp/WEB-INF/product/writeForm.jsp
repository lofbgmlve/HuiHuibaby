<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<article>
<div id="pDiv">
<div id="body">

<form name="writeForm" action="writeProcess.mvc"  id="writeForm" method="post" enctype="multipart/form-data">
<h2 id="pH2">상품 등록하기</h2>
<table id="detailT">
    <tr>
       <td class = "top_photo">
       <img id="main1" src="product_img/photo.PNG">
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <img id="photo1" alt="사진" src="product_img/photo.PNG" onerror="this.style.display='none'">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <img id="photo2" alt="사진" src="product_img/photo.PNG" onerror="this.style.display='none'">
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       </td>
       <td class ="top_detail">
          작성자:${sessionScope.mNickname }<br/><br/>
          제목 : <input type="text" id="ptitle" name="title"><br/><br/>
          제품명 : <input type="text" id="pname"name="name"><br/><br/>
          제조사 : <input type="text" id="ppro" name="pro"><br/><br/>
          상품분류: <select id="ppart" name="part" >
         <option value="none">-선택해주세요-</option>
         <option value="1">수유*위생*스킨</option>
         <option value="2">의류패션잡화</option>
         <option value="3">어린이도서*장난감</option>
         <option value="4">유모차*관련용품</option>
         </select>
         <br/><br/>
          제품상태 :
          <br/> <input type="radio" class="condition" name="condition"  value="A">새상품&미개봉
         <input type="radio" class="condition" name="condition" value="B">사용감 조금 있음
         <input type="radio" class="condition" name="condition" value="C">사용감 있음
         <br/><br/> 
          거래방법: <input type="radio" id="pway" name="way" value="직거래" >직거래 
         <input type="radio" id="pway" name="way" value="택배">택배 
         <input type="radio" id="pway" name="way" value="둘다가능">둘 다 가능
          <br/><br/>
          거래상태: <select name="staus" id="pstaus" >
          <option value="거래가능" >거래가능</option>
          <option value="거래완료">거래완료</option>
          </select >
           <br/><br/>
          판매가 : <input type="text" id="pprice" name="price">원
          <br/><br/>
            이미지 첨부: <input id="pfile" name="file1" type="file"> 
            <br/><small>(*파일1개 필수/파일최대3개)</small>
            <br/><br/>
            이미지 첨부: <input id="pfile2" name="file2" type="file" > 
            <br/><br/>
            이미지 첨부: <input id="pfile3" name="file3" type="file"> 
       </td>
       
    </tr>
    <tr>
       <td colspan = "2" class ="bottom"  > 상세정보<br/>
       <textarea  name="detail" id="pdetail"  cols="140" rows="12"  ></textarea></td>
    </tr>
    
 </table>
    <span id="btnSpan">
    <input id="writeReset" type="reset" value="다시쓰기">
    <input  id="writeForm" type="submit" value="등록하기" >
    <input id="btnList" type="button" value="목록보기" onclick="javascript:window.location.href='productList.mvc'">
    </span>
 </form>
 </div>
 </div>
</article>