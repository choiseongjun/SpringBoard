<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>

$(function(){
	$("#btnSave").click(function(){
		var str="";
		// uploadedList 내부의 .file 태그 각각 반복
		$("#uploadedList .file").each(function(i){
			console.log(i);
			//hidden 태그 구성
			str += 
"<input type='hidden' name='files["+i+"]'	value='"
	+ $(this).val()+"'>";
		});
		//폼에 hidden 태그들을 붙임
		$("#form1").append(str);
		document.form1.submit();
	});
	$(".fileDrop").on("dragenter dragover",function(e){
		//기본 효과 막음
		e.preventDefault();
	});
	$(".fileDrop").on("drop",function(e){
		e.preventDefault();
		//첫번째 첨부파일
		var files=e.originalEvent.dataTransfer.files;
		var file=files[0];
		//폼 데이터에 첨부파일 추가
		var formData=new FormData();
		formData.append("file",file);
		$.ajax({
			url: "${path}/upload/uploadAjax",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false,
			type: "post",
			success: function(data){
				//console.log(data);
				//data : 업로드한 파일 정보와 Http 상태 코드
				var fileInfo=getFileInfo(data);
				//console.log(fileInfo);
				var html="<a href='"+fileInfo.getLink+"'>"+
					fileInfo.fileName+"</a><br>";
				html += "<input type='hidden' class='file' value='"
					+fileInfo.fullName+"'>";
				$("#uploadedList").append(html);
			}
		});
	});
});
</script>

<style>
.fileDrop {
	width: 600px;
	height: 100px;
	border: 1px dotted white;
	background-color: white;
}
</style>
</head>
<body>
<%@ include file="../Header.jsp" %>
<script src="${path}/ckeditor/ckeditor.js"></script>
<script src="${path}/resources/common.js"></script>
<div align="center">
     <h1>답글쓰기</h1>
 <form id="form1" name="form1" method="post"
action="${path}/board/replyInsert.b?ref=${ref}&ref_group=${ref_group}">

	<div>제목 <input name="title" id="title" size="80"
					placeholder="제목을 입력하세요">
	</div>
	<div style="width:800px;">
		내용 <textarea id="content" name="content"
rows="3" cols="80" placeholder="내용을 입력하세요"></textarea>
<script>
// ckeditor 적용
CKEDITOR.replace("content",{
	filebrowserUploadUrl: "${path}/imageUpload.do"
});
</script>
	</div>
	<div> 첨부파일을 등록하세요
		<div class="fileDrop"></div>
		<div id="uploadedList"></div>
	</div>
	<div style="width:700px; text-align:center;">
		<button type="button" id="btnSave">확인</button>
	</div>
</form>
</div>
</body>
</html>