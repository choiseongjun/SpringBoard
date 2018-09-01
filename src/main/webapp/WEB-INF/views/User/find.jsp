<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../Header.jsp" %>
<head>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath }" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
$(document).on('click','#findId',function(){
	var name= $('#name').val();
 	var email = $('#email').val();

 	var postData = {'name' : name , 'email' :email};
	$.ajax({
        url:'${path}/member/findidimpl.do',
        type:'POST',
       data:postData,
        success:function(data){
        	var useridlist = data.userid;
        	var useridfind = useridlist;
       	 		 $("#useridlist").append("<h1>"+"회원님의 정보로 등록된 아이디는 : "+data+" 입니다.</h1>")
				alert("회원님의 아이디는"+data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown){

        	alert('정보를 다시 입력해주시길 바랍니다.' );
        }
    });
});
</script>
</head>

<body>
<div id="content">
<center>

<table border="1" width="400px" style="margin-top: 100px">
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" id="name" ></td>
		</tr>
		<tr>
			<td>휴대폰번호</td>
			<td><input type="text" name="email" id="email"></td>
		</tr>
	<tr>
	<td colspan="2"><button id="findId">아이디 찾기</button></td>
	</tr>
	<td>${udto.userid }</td>
	<span id="useridlist"></span>
		</table>
		</center>
		</div>
</body>

</html>