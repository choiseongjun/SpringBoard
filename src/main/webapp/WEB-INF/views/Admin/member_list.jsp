<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp"%>
</head>
<body>

	<table class="table table-dark">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>가입일자</th>
			</tr>
			<c:forEach var="row" items="${list}">
				<tr>
	<c:if test="${sessionScope.userid !=null}">
					<td class="userid" data-userid="${row.userid}">${row.userid}</td>
					</c:if>
					<td >${row.name}</td>
					<td>${row.email}</td>
					<td><fmt:formatDate value="${row.join_date}"
							pattern="yyyy-MM-dd" /></td>
				
				</tr>
			</c:forEach>
	</table>

<script>
	$('.userid').on('click',function(){//사용자 아이디를 클릭하면..
		var otherUserid = $(this).attr("data-userid");
		$.ajax({
	        url:'${path}/setOtherUserid.do',
	        type:'POST',
	       	data:{'otherUserid':otherUserid},
	        success:function(data){
	        	if(data==1){
	        		location.href="${path}/message.do";
	        	}
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown){
	        }
		});
	});
</script>
</body>
</html>



















