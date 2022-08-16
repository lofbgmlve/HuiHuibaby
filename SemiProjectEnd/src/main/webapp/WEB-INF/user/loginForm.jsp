<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<article>
   <form id="loginForm" action="login.mvc" method="post">
      <fieldset>   
         <div id="login">
            <p>
               <label for="userId" class="labelStyle">아이디</label>
               <input type="text" id="userId" name="mId"/>
            </p>
            <p>
               <label for="userPass" class="labelStyle">비밀번호</label>
               <input type="password" id="userPass" name="mPw"/>
            </p>
         </div>
            <input type="submit" value="로그인" id="btnLogin" />
         <p id="btn1">
            <input type="checkbox" id="saveId" value="savedIdYes" />
            <label for="saveId">아이디저장</label>   
            <input type="checkbox" id="secure" value="secureYes" />
            <label for="secure">보안접속</label>   
         </p>
         <p id="btn2">
            <input type="button" value="회원가입" id="btnJoin" />
            <input type="button" value="아이디/비밀번호 찾기" id="btnSearch" />
         </p>
      </fieldset>
   </form>
</article>