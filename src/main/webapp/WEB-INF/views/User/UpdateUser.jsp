<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<tr height="500" align="center">
		<td align="center" width="1000">
		<%@ include file="../Header.jsp" %></td>
		</tr>
 <script src="Register.js"></script>
 <script type="text/javascript">
 
 	
 	$(function(){
 	 $(document).ready(function(){
 		 $("#btnUpdate").click(function(){
 				document.form1.action="${path}/member/update.do";
 				document.form1.submit();
 			});
 		 $("#btnDelete").click(function(){
 			 if(confirm("탈퇴하시겠습니까?")){
 				 document.form1.action="${path}/member/delete.do";
 				 document.form1.submit();
 			 }
 		 })
 		 });
 	});


 </script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div id="container">
	<center>
		<h2 style="margin-top: 200px">회원 가입 </h2>
		<form  method="post" name="form1">
			<table width="400" border="1" bordercolor="gray">
					<tr height="40">
						<td width="150">아이디
						<input type="hidden" name="idDuplication" value="IdUncheck" ></td>
						<td width="250">
						<input class="form-control" type="text" name="userid" id="userid" readonly onkeydown="checkId()" value="${dto.userid }" >
						</td>
					</tr>
						<tr height="40">
						<td width="150">패스워드</td>
						<td width="250"><input class="form-control" type="password" name="passwd"  id="passwd"  onkeyup="passwordCheckFunction();" ></td>
					</tr>
						<tr height="40">
						<td width="150">이름</td>
						<td width="250"><input class="form-control" type="text" name="name" id="name" value="${dto.name }"></td>
					</tr>
						<tr height="40">
						<td width="150">이메일</td>
						<td width="250"><input class="form-control" type="text" name="email" value="${dto.email}"></td>
					</tr>
						<tr height="40">
						<td colspan="2"><input type="button" value="수정하기" id="btnUpdate"><input type="button" value="탈퇴하기" id="btnDelete">
					<%-- 	<div style="color:red">${message}</div> --%></td>
					</tr>
						<td style="text-align:left" colspan="3"><h5 style="color:black;" id="passwordCheckMessage">${message}</h5></td>
				</table>
		</form>
	</center>
	</div>
</body>
</html>