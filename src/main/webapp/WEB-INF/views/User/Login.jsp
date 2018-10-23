<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp"%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<script>
		function findid() {
			location.href = "${path}/member/findid.do";
		}
		function findPw() {
			/* 	var win = window.open("${path}/member/findpw.do", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=400,height=400");
			 */location.href = "${path}/member/findpw.do";
		}
		$(function() {
			$("#btnLogin").click(function() {
				var userid = $("#userid").val(); //태그의 value 속성값
				var passwd = $("#passwd").val();
				if (userid == "") {
					alert("아이디를 입력하세요.");
					$("#userid").focus(); //입력 포커스 이동
					return; //함수 종료
				}
				if (passwd == "") {
					alert("비밀번호를 입력하세요.");
					$("#passwd").focus();
					return;
				}
				//폼 데이터를 서버로 제출
				document.form1.action = "${path}/member/login_check.do";
				document.form1.submit();
			});
		});
	</script>
	<form name="form1" method="post">
		<table width="400" class="table table-bordered" border="0"
			bordercolor="gray" align="center">
			<tr height="40">
				<td width="150"><font face="궁서체">아이디</font> <input
					type="hidden" name="idDuplication" value="IdUncheck"></td>
				<td width="250"><input class="form-control" type="text"
					name="userid" id="userid"></td>
			</tr>
			<tr height="40">
				<td width="150"><font face="궁서체">패스워드</font></td>
				<td width="250"><input class="form-control" type="password"
					name="passwd" id="passwd"></td>
			</tr>
			<tr height="40">
			<c:if test="${message == 'error' }">
				<td width="400" colspan="2" style="color: red;">아이디 또는 비밀번호가 일치하지 않습니다.</td>
			</c:if>
			</tr>
			<tr height="40">
				<td colspan="2"><input type="button" id="btnLogin"
					class="btn btn-primary" value="로그인하기"> <input type="button"
					class="btn btn-primary" onclick="findid()" value="id찾기"> <input
					type="button" class="btn btn-primary" onclick="findPw()"
					value="비번찾기">
					<!-- 구글로그인 -->
					    <a href="${google_url}"><button id="btnJoinGoogle" class="btn btn-primary btn-round"
                                style="width: 100%">
                                <i class="fa fa-google" aria-hidden="true"></i>Google Login
                            </button></a> 
					<div id="naver_id_login" style="text-align:center"><button id="btnJoinNaver" class="btn btn-primary btn-round"
                                style="width: 100%">Naver Login<a href="${url}"></a></div>
<br>
					</td>
			</tr>
		</table>
	</form>
</body>
</html>