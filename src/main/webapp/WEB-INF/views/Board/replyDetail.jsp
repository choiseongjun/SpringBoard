<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery-3.2.1.min.js" />
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$("#btnReplyClose").click(function(){
	$("#modifyReply").css("visibility","hidden");
});
//댓글 수정
$("#btnReplyUpdate").click(function(){
	var detailReplytext=$("#detailReplytext").val();
	$.ajax({
		type:"put",
		url:"${path}/reply/update/${vo.rno}",
		headers:{
			"Content-Type":"application/json"
		},
		data:JSON.stringify({
			replytext:detailReplytext
		}),
		dataType:"text",
		success:function(result){
		if(result=="success"){
			$("#modifyReply").css("visibility","hidden");
			//Renew List reply
			listReply("1");
		}
	}
	});
});
</script>
</head>
<body>
댓글 번호:${vo.rno }<br>
<textarea id="detailReplytext" rows="5" cols="62">${vo.replytext }</textarea>
 <c:if test="${sessionScope.userid == vo.replyer }">
	<button type="button" id="btnReplyUpdate">수정</button>
</c:if>
<button type="button" id="btnReplyClose">닫기</button>
</body>
</html>