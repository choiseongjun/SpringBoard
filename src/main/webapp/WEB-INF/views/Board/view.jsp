<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- views/board/view.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp" %>
<script src="${path}/include/js/common.js"></script>
<!-- ckeditor의 라이브러리 -->
<script src="${path}/ckeditor/ckeditor.js"></script>
<script>
$(function(){ //자동으로 실행되는 코드

	//목록 버튼
	$("#btnList").click(function(){
		location.href="${path}/board/list.do";
	});
	//수정 버튼
	$("#btnUpdate").click(function(){
		
		/* var title=$("#title").val();
		var content=$("#content").val();
		var writer=$("#writer").val();
		if(title=""){
			alert("제목을 입력하세요");
			document.form1.title.focus();
			return;
		} */
		/* $("#form1").append(str); */
		document.form1.action="${path}/board/update.do";
		document.form1.submit();
	});
	//삭제 버튼
	$("#btnDelete").click(function(){
		if(confirm("삭제하시겠습니까?")){
			document.form1.action="${path}/board/delete.do";
			document.form1.submit();
		}
	});
});
	
	


</script>

<style>
.fileDrop {
	width: 600px;
	height: 100px;
	border: 1px dotted gray;
	background-color: gray;
}
</style>

</head>
<body>
<h2>게시물 보기</h2>
<form id="form1" name="form1" method="post"
action="${path}/board/insert.do">
	<div>제목 <input name="title" id="title" size="80"
					value="${dto.title}"
					placeholder="제목을 입력하세요">
	</div>
	<div>조회수 : ${dto.viewcnt}	</div>
	<div style="width:800px;">
		내용 <textarea id="content" name="content"
rows="3" cols="80" 
placeholder="내용을 입력하세요">${dto.content}</textarea>
<!-- <script>
// ckeditor 적용
CKEDITOR.replace("content",{
	filebrowserUploadUrl: "${path}/imageUpload.do",
	height: "300px"
});
</script> -->
	</div>

	<div style="width:700px; text-align:center;">
<!-- 수정,삭제에 필요한 글번호를 hidden 태그에 저장 -->	
		<input type="hidden" name="bno" value="${dto.bno}">
		
		<!-- 본인만 수정,삭제 버튼 표시 -->
		<c:if test="${sessionScope.userid == dto.writer}">
			<button type="button" id="btnUpdate">수정</button>
			<button type="button" id="btnDelete">삭제</button>
		</c:if>
		
		<button type="button" id="btnList">목록</button>
	</div>
</form>

</body>
</html>



















