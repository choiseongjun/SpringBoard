<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script>
</head>
<style>
                 

 .portlet {
    margin-bottom: 15px;
}

.btn-white {
    border-color: #cccccc;
    color: #333333;
    background-color: #ffffff;
}

.portlet {
    border: 1px solid;
}

.portlet .portlet-heading {
    padding: 0 15px;
}

.portlet .portlet-heading h4 {
    padding: 1px 0;
    font-size: 16px;
}

.portlet .portlet-heading a {
    color: #fff;
}

.portlet .portlet-heading a:hover,
.portlet .portlet-heading a:active,
.portlet .portlet-heading a:focus {
    outline: none;
}

.portlet .portlet-widgets .dropdown-menu a {
    color: #333;
}

.portlet .portlet-widgets ul.dropdown-menu {
    min-width: 0;
}

.portlet .portlet-heading .portlet-title {
    float: left;
}

.portlet .portlet-heading .portlet-title h4 {
    margin: 10px 0;
}

.portlet .portlet-heading .portlet-widgets {
    float: right;
    margin: 8px 0;
}

.portlet .portlet-heading .portlet-widgets .tabbed-portlets {
    display: inline;
}

.portlet .portlet-heading .portlet-widgets .divider {
    margin: 0 5px;
}

.portlet .portlet-body {
    padding: 15px;
    background: #fff;
}

.portlet .portlet-footer {
    padding: 10px 15px;
    background: #e0e7e8;
}

.portlet .portlet-footer ul {
    margin: 0;
}

.portlet-green,
.portlet-green>.portlet-heading {
    border-color: #16a085;
}

.portlet-green>.portlet-heading {
    color: #fff;
    background-color: #16a085;
}

.portlet-orange,
.portlet-orange>.portlet-heading {
    border-color: #f39c12;
}

.portlet-orange>.portlet-heading {
    color: #fff;
    background-color: #f39c12;
}

.portlet-blue,
.portlet-blue>.portlet-heading {
    border-color: #2980b9;
}

.portlet-blue>.portlet-heading {
    color: #fff;
    background-color: #2980b9;
}

.portlet-red,
.portlet-red>.portlet-heading {
    border-color: #e74c3c;
}

.portlet-red>.portlet-heading {
    color: #fff;
    background-color: #e74c3c;
}

.portlet-purple,
.portlet-purple>.portlet-heading {
    border-color: #8e44ad;
}

.portlet-purple>.portlet-heading {
    color: #fff;
    background-color: #8e44ad;
}

.portlet-default,
.portlet-dark-blue,
.portlet-default>.portlet-heading,
.portlet-dark-blue>.portlet-heading {
    border-color: #34495e;
}

.portlet-default>.portlet-heading,
.portlet-dark-blue>.portlet-heading {
    color: #fff;
    background-color: #34495e;
}

.portlet-basic,
.portlet-basic>.portlet-heading {
    border-color: #333;
}

.portlet-basic>.portlet-heading {
    border-bottom: 1px solid #333;
    color: #333;
    background-color: #fff;
}

@media(min-width:768px) {
    .portlet {
        margin-bottom: 30px;
    }
}

.text-green {
    color: #16a085;
}

.text-orange {
    color: #f39c12;
}

.text-red {
    color: #e74c3c;
}                
</style>

<body>
<%@ include file="../Header.jsp"%>
<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-xs-12">
				<div class="portlet portlet-default">
					<div class="portlet-heading">
						<div class="portlet-title">
							<h4><i class="fa fa-circle text-green"></i>채팅</h4>
						</div>
						<div class="clearfix"></div>
					</div>
					<div id="chat" class="panel-collapse collapse in">
						<div id="chatList" class="portlet-body chat-widget" style="overflow-y:auto;width:auto;height:600px"></div>
						<div class="portlet-footer">
						
							<div class="row" style="height: 90px;">
								<div class="form-group col-xs-10">
									<textarea style="height: 80px;" id="chatContent" class="form-control" placeholder="메세지를 입력하세요." maxlength="100"></textarea>
								</div>
								<div class="form-group col-xs-2">
									<button type="button" id = "submitBtn" class="btn btn-default pull right">전송</button>
								<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 메세지 전송 결과 모달 팝업창 -->
	<div class="alert alert-success" id="successMessage" style="display: none;">
		<strong>메세지 전송에 성공했습니다.</strong>
	</div>
	<div class="alert alert-danger" id="dangerMessage" style="display: none;">
		<strong>이름과 내용을 모두 입력하세요.</strong>
	</div>
	<div class="alert alert-warning" id="warningMessage" style="display: none;">
		<strong>데이터베이스 오류가 발생했습니다.</strong>
	</div>
	
<script type="text/javascript">
	$(document).ready(function() {
     		
		function connectSocket(){
			// 웹소켓을 지정한 url로 연결한다.
			let sock = new SockJS("/ex/echo");
			sock.onmessage = onMessage;
			sock.onclose = onClose;
			// 메시지 전송
			function sendMessage() {
			       sock.send($("#chatContent").val());
			       $('#chatContent').val('');
			}
			// 서버로부터 메시지를 받았을 때
			function onMessage(msg) {
			       var data = msg.data;
			       $("#chatList").append(data + "<br/>");
			       $("#chatList").scrollTop($("#chatList")[0].scrollHeight);
			}
			// 서버와 연결을 끊었을 때
			function onClose(evt) {
			       $("#chatList").append("연결 끊김");
			}
			
			$("#submitBtn").on('click',function(){
				sendMessage();
			});
			
			$("#chatContent").keydown(function(key) {
				if (key.keyCode == 13) {// 엔터
					key.preventDefault();	
				       sendMessage();
				}
			});
		}     	
			$.ajax({
		        url:'${path}/getMessageList.do',
		        type:'POST',
		        success:function(data){
		        	var str = "";
		        	for(var i = 0;i<data.length;i++){
		        		str += data[i].fromid+": "+data[i].chatcontent +"  ☆  " + data[i].chattime + "<br/>"
		        	}
		        	$("#chatList").append(str);
		        	$("#chatList").scrollTop($("#chatList")[0].scrollHeight);
		        	
		        	connectSocket();//마지막에성공하면 실행
		        },
		        error: function (XMLHttpRequest, textStatus, errorThrown){
		        }
			});
			
     });
</script>
</body>
</html>