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
        	if(useridlist=null){
       	 		alert("아이디가 존재하지 않습니다")
        	}else{
        		 $("#useridlist").append("<h1>"+"회원님의 정보로 등록된 아이디는 : "+data+" 입니다.</h1>")

        	}
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
<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
				<h3>아이디 찾기</h3>
			</div>
			<div>
				<p>
					<label>이름</label>
					<input class="w3-input" type="text" id="name" name="name" required>
				</p>
				<p>
					<label>Email</label>
					<input class="w3-input" type="text" id="email" name="email" required>
				</p>
			</div>
		</div>
	</div>
			<button id="findId">아이디 찾기</button></td>
	
	<td>${udto.userid }</td>
	<span id="useridlist"></span>
		</table>
		</center>
		</div>
	</div>
		</div>
	
</body>

</html>