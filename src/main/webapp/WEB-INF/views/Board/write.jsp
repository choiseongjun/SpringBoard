<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp" %>
<script type="text/javascript">
$(function(){
	$("#btnSave").click(function(){

		document.form1.submit();
	})
});
</script>
</head>
<body>
<h2>글쓰기</h2>
<form id="form1" name="form1" method="post"
action="${path}/board/insert.do">
	<div>제목 <input name="title" id="title" size="80"
					placeholder="제목을 입력하세요">
	</div>
	<div style="width:800px;">
		내용 <textarea id="content" name="content"
rows="3" cols="80" placeholder="내용을 입력하세요"></textarea>
</div>
<div style="width:700px; text-align:center;">
		<button type="button" id="btnSave">확인</button>
	</div>
</form>
</body>
</html>