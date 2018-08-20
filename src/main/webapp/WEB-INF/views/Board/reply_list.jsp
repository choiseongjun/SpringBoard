<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- views/board/reply_list.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp" %>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>
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
			${str}
		</td>
		<td>
			<button type="button" id="btnReplyDelete">삭제</button>
		</td>
	</tr>
</c:forEach>	
</table>

</body>
</html>



















