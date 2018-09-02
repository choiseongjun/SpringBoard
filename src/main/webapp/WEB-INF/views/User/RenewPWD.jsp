<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../Header.jsp" %>
<style>
	@media screen and(min-width:300px ) and (max-width:500px){
		#container {width:100%;
					height:100%; }
	}
</style>
<script>
function passwordCheckFunction(){
	var userPassword1=$('#passwd1').val();
    var userPassword2=$('#passwd2').val();
    if(userPassword1 !=userPassword2){
    	$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다');
    }else{
    	$('#passwordCheckMessage').html('비밀번호가 일치합니다');

    }	
   }

	 $(document).ready(function(){
		 $("#btnUpdate").click(function(){
			 var beforePW = $('#passwd').val();
			 var newPW = $('#passwd1').val();
			 
			 $.ajax({
				 url:'${path}/member/update.do',//날릴주소
				 type:'post',//post get 등등 타입
				 data:{'beforePW':beforePW,'newPW':newPW},//서버로 날려줄 데이터
				 success:function(data){//서버에서 요청 잘 처리되면 return되는 것이 data에 꽃힘
					 if(data==1){
						 //alert(data.message);
						 window.close();//수정하기
					 }else{
						 alert(data.message);
					 }
				 },
				 error:function(e){

				 }
			 });
				/* document.form1.action="${path}/member/update.do";
				document.form1.submit(); */
			})
		 });

</script>
 <div id="container">

		<h2 style="margin-top: 200px">회원 정보 수정 </h2>
		<form  method="post" name="form1">
			<table width="400" border="1" bordercolor="gray">
					<tr height="40">
						<td width="150">기존패스워드</td>
						<td width="250"><input class="form-control" type="password" name="passwd"  id="passwd" ></td>
					</tr>
					<tr height="40">
						<td width="150">새로운패스워드</td>
						<td width="250"><input class="form-control" type="password" name="passwd"  id="passwd1"  onkeyup="passwordCheckFunction();" ></td>
					</tr>
					<tr height="40">
						<td width="150">새로운패스워드확인</td>
						<td width="250"><input class="form-control" type="password" name="passwd1"  id="passwd2"  onkeyup="passwordCheckFunction();" ></td>
					<tr height="40">
						<td colspan="2"><input type="button" value="수정하기" id="btnUpdate">
						</td>
					</tr>
					<tr>
						<td style="text-align:left" colspan="3"><h5 style="color:black;" id="passwordCheckMessage">${message}</h5></td>
					</tr>
				</table>
		</form>

	</div>
</body>
</html>