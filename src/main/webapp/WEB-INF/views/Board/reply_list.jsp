<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- views/board/reply_list.jsp -->
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %>
</head>

<body>
<script>
$(".btnReplyDelete").click(function(){
	var rno = $(this).attr('data-rno')
	if(confirm("삭제하시겠습니까?")){
		$.ajax({
			type:"post",
			url:"${path}/reply/delete/"+rno,
			success:function(result){
				if(result=="success"){
					alert("삭제되었습니다");
					$("#modifyReply").css("visibility","hidden");
					listReply();
				}
			}
		});
	}
});
</script>
<% pageContext.setAttribute("newLineChar", "\n"); %>
 <table class="table table-dark">
<c:forEach var="row" items="${list}">   
	<c:set var="str"
value="${fn:replace(row.replytext,'<','&lt;') }" />
	<c:set var="str"
value="${fn:replace(str,'>','&gt;') }" />	
	<c:set var="str"  
value="${fn:replace(str,'  ','&nbsp;&nbsp;')}" />
	<c:set var="str"
value="${fn:replace(str,newLineChar,'<br>') }" />	
	
	<tr>
		<td>
		
			${row.name}
			( <fmt:formatDate value="${row.regdate}"
				 pattern="yyyy-MM-dd a HH:mm:ss" /> )<br>
		
	${str }
		</td>
		<c:if test="${sessionScope.userid == row.replyer}">
		<td>
			<button type="button" class="btnReplyDelete" data-rno="${row.rno}">삭제</button>
		</td>
		</c:if>
	</tr>
</c:forEach>	
</table>

</body>
</html>



















