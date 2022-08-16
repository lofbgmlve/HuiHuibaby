<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article>
   <div id="userJoinForm">
   
   <form action="joinResult.mvc" name="joinForm" method="post" id="joinForm">
      
      <%-- 회원 아이디 중복 검사를 했는지의 정보를 hidden 필드로 저장 --%>
      <input type="hidden" name="isIdCheck" id="isIdCheck" value="false"/>
      <input type="hidden" name="isNickameCheck" id="isNicknameCheck" value="false"/>
      <div id="userInputDefault">   
      <h2 id="joinFormTitle">회원가입</h2>   
      <br/>
         <!-- 아이디 -->      
         <div class="userInputText">
            <span class="userSpan"> 아이디 : </span>
            <input type="text" name="mId" size="15"  id="mId"               
               value="${ sessionScope.user.mId }"/>
            <input type="button" value="중복확인" id="btnOverlapId"/>
         </div>
         <br/>
         
         <!-- 비밀번호 -->
         <div class="userInputText">
            <span class="userSpan"> 비밀번호 : </span>
            <input type="password" name="mPw" id="mPw" size="15"/>
         </div>
         <br/>
         
         <!-- 비밀번호 확인  -->
         <div class="userInputText">
            <span class="userSpan"> 비밀번호 확인 : </span>
            <input type="password" name="mPw2" id="mPw2" size="15"/>
         </div>
         <br/>
         
         <!-- 닉네임  -->
         <div class="userInputText">
            <span class="userSpan"> 닉네임 : </span>
            <input type="text" name="mNickname" size="15"  id="mNickname"
             value="${ sessionScope.user.mNickname }"/>
            <input type="button" value="중복확인" id="btnOverlapNickname"/>
         </div>
         <br/>
         
         <!-- 이름 -->
         <div class="userInputText">
            <span class="userSpan"> 이&nbsp;&nbsp;름 : </span>            
            <input type="text" name="mName" size="15"
               id="mName" value="${ sessionScope.user.mName }" />
         </div>
         <br/>
         
         <!-- 성별 -->                     
         <div>
            <span class="userSpan"> 성별 : </span>
            <input type="radio" name="mGender" value="남"/> 남 
            <input type="radio" name="mGender" value="여"/> 여
         </div>
         <br/>
         
         <!-- 휴대폰 번호  -->
         <div class="userInputText">
            <span class="userSpan"> 휴 대 폰 : </span>
            <select name="mPhone1" id="mPhone1">
               <option ${ user.mPhone.split('-')[0] == 010 ? "selected" : "" }>
                  010</option>
               <option ${ user.mPhone.split('-')[0] == 011 ? "selected" : "" }>
                  011</option>
               <option ${ user.mPhone.split('-')[0] == 016 ? "selected" : "" }>
                  016</option>
               <option ${ user.mPhone.split('-')[0] == 017 ? "selected" : "" }>
                  017</option>
               <option ${ user.mPhone.split('-')[0] == 018 ? "selected" : "" }>
                  018</option>
               <option ${ user.mPhone.split('-')[0] == 019 ? "selected" : "" }>
                  019</option>
            </select>-               
            <input type="text" name="mPhone2" size="3" maxlength="4" id="mPhone2"
               value="${ sessionScope.user.mPhone.split('-')[1] }"/>-
            <input type="text" name="mPhone3" size="3" maxlength="4" id="mPhone3"
               value="${ sessionScope.user.mPhone.split('-')[2] }"/>
         </div>
         <br/>
         <!-- 이메일 -->
         <div class="userInputText">
            <span class="userSpan"> 이 메 일 : </span>
            <input type="text" name="mEmailId" size="10" id="mEmailId"
               value="${ sessionScope.user.mEmail.split('@')[0] }"/>
            @ <input type="text" name="mEmailDomain" size="10" id="mEmailDomain"                
               value="${ sessionScope.user.mEmail.split('@')[1] }"/>
            <select name="selectDomain" id="selectDomain">
               <option>직접입력</option>
               <option>네이버</option>
               <option>다음</option>
               <option>한메일</option>
               <option>구글</option>                              
            </select>
         </div>
         <br/>
         
         <!-- 생년월일 -->
         <div>
            <span class="userSpan"> 생년월일 : </span>
            <input type="text" name="mBirth" size="2" id="mBirth1" maxlength="4"
               value="${ sessionScope.uesr.mBirth.split('-')[0]}"/>년
            <select name="mBirth2" id="mBirth2">
               <option${ user.mBirth.split('-')[1] == 01 ? "selected" : "" }>
                  01
               </option>
               <option${ user.mBirth.split('-')[1] == 02 ? "selected" : "" }>
                  02
               </option>
               <option${ user.mBirth.split('-')[1] == 03 ? "selected" : "" }>
                  03
               </option>
               <option${ user.mBirth.split('-')[1] == 04 ? "selected" : "" }>
                  04
               </option>
               <option${ user.mBirth.split('-')[1] == 05 ? "selected" : "" }>
                  05
               </option>
               <option${ user.mBirth.split('-')[1] == 06 ? "selected" : "" }>
                  06
               </option>
               <option${ user.mBirth.split('-')[1] == 07 ? "selected" : "" }>
                  07
               </option>
               <option${ user.mBirth.split('-')[1] == 08 ? "selected" : "" }>
                  08
               </option>
               <option${ user.mBirth.split('-')[1] == 09 ? "selected" : "" }>
                  09
               </option>
               <option${ user.mBirth.split('-')[1] == 10 ? "selected" : "" }>
                  10
               </option>
               <option${ user.mBirth.split('-')[1] == 11 ? "selected" : "" }>
                  11
               </option>
               <option${ user.mBirth.split('-')[1] == 12 ? "selected" : "" }>
                  12
               </option>
            </select>
            <input type="text" name="mBirth" size="2" id="mBirth3" maxlength="2"
               value="${ sessionScope.uesr.mBirth.split('-')[2]}"/>일
            
         </div>
         <br/>
      </div>
      <br/>
      <div class="formButton">
         <input type="reset" value="다시쓰기"/>
          &nbsp;&nbsp;
         <input type="submit" value="가입하기" />
      </div>         
   </form>   
   </div>
</article>