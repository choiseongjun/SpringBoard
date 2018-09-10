<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../Header.jsp"%>
</head>
<style>
label {
  background-color: pink;
  color: black;
  font-weight: bold;
  padding: 4px;
  text-transform: uppercase;
  font-family: Verdana, Arial, Helvetica, sans-serif;
  font-size: xx-small;
}

</style>
<script>
function passwordCheckFunction(){
		var userPassword1=$('#passwd').val();
	    var userPassword2=$('#passwd1').val();
	    if(userPassword1 !=userPassword2){
	    	$('#passwordCheckMessage').html('비밀번호가 서로 일치하지 않습니다');
	    }else{
	    	$('#passwordCheckMessage').html('비밀번호가 일치합니다');

	    }	
	   }
$(document).ready(function(){
    $('#checkbtn').on('click', function(){
        $.ajax({
            type: 'POST',
            url: '${path}/member/checkId.do',
            data: {
                "userid" : $('#userid').val()
            },
            success: function(data){
            	alert(data);
                if(data == 'true'){
                    $('#passwordCheckMessage').html('<p>이미 아이디가 존재합니다</p>');
                }
                else{
                    $('#passwordCheckMessage').html('<p>사용 가능</p>');
                }
            }
        });    //end ajax    
    });    //end on    

});

function emailCheck() {		

	var email = document.getElementById("email").value;

	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

	if(exptext.test(email)==false){

	//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐 경우
	alert("이메일 형식이 올바르지 않습니다.");
return false; 
	}
	document.form1.action="${path}/member/insert.do";
	document.form1.submit();
	alert("회원 가입 완료하였습니다");
}
document.duplicate=function(e){
	var act=document.duplicate.idDuplication;
	if(!idDuplication.onclick){
		alert("버튼을 눌러주세요");
		act.focus();
		return false;
	}
}
</script>
<body>
<form name="form1" method="post" onsubmit="emailCheck();" name="duplicate" enctype="multipart/form-data"
	>
  <table width="400" border="0" class="table table-bordered" bordercolor="gray" align="center" >
					<tr height="40">
						<td width="150"><font face="궁서체">아이디</font><button type="button"  class="btn btn-default" id="checkbtn"  >중복확인</button>
						</td>
						<td width="250">
								<input class="form-control" type="text" name="userid" id="userid" >	
						</td><input type="hidden" name="idDuplication" value="IdUncheck">
					</tr>
						<tr height="40">
						<td width="150"><font face="궁서체">패스워드</font></td>
						<td width="250"><input class="form-control" type="password" name="passwd"  id="passwd"  onkeyup="passwordCheckFunction();" ></td>
					</tr>
						<tr height="40">
						<td width="150"><font face="궁서체">패스워드확인</font></td>
						<td width="250"><input class="form-control" type="password" name="passwd1" id="passwd1"  onkeyup="passwordCheckFunction();" ></td>
					</tr>
						<tr height="40">
						<td width="150"><font face="궁서체">이름</font></td>
						<td width="250"><input class="form-control" type="text" name="name" id="name"></td>
					</tr>
						<tr height="40">
						<td width="150"><font face="궁서체">이메일</font></td>
						<td width="250"><input class="form-control" type="text" name="email" id="email"><p><font face="궁서체" color="blue">정확한 이메일을 작성하세요</font></p></td>
					</tr>
						</tr>
						<tr height="40">
						<td width="150"><font face="궁서체">사진</font></td>
						<td width="250"><input class="form-control" type="file" name="file1" id="file1"></td>
					</tr>
						<tr height="40">
						<td colspan="2">	
						<input type="submit" class="btn btn-primary" id="checkbtn" value="가입하기">
					</td>
					</tr>
					<tr>
				<div id = "showdata" style = "text-align: center"></div>
			</tr>
						<td style="text-align:left" colspan="3"><h5 style="color:red;" id="passwordCheckMessage"></h5></td>
				</table>
  
</form>
</body>
</html> 