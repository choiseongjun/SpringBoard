<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp"%>
</head>
<body>
<script>
$(function(){
	$("#btnLogin").click(function(){
		var userid=$("#userid").val(); //태그의 value 속성값
		var passwd=$("#passwd").val();
		if(userid==""){
			alert("아이디를 입력하세요.");
			$("#userid").focus(); //입력 포커스 이동
			return; //함수 종료
		}
		if(passwd==""){
			alert("비밀번호를 입력하세요.");
			$("#passwd").focus();
			return;
		}
		//폼 데이터를 서버로 제출
		document.form1.action="${path}/member/login_check.do";
		document.form1.submit();
	});
});
</script>
<form name="form1" method="post">
  <div class="form-group">
    <input  class="form-control" id="userid" name="userid" aria-describedby="emailHelp" placeholder="Enter ID">
  </div>
  <div class="form-group">
    <input type="password" class="form-control" id="passwd" name="passwd" placeholder="Password">

  	<input type="button" id="btnLogin" class="btn btn-primary" value="로그인하기">
</form>
</body>
</html>