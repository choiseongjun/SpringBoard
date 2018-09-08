<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- views/board/view.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
   <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

        <!-- Web socket CDN -->

        <script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script>
<%@ include file="../Header.jsp" %>
</head>
<body>
	<input type="text" id="roomId"/>
	<input type="button" id="enterRoom" value="입장" />
    <input type="text" id="message" />
    <input type="button" id="sendBtn" value="전송" />
    <div id="data"></div>
</body>



<script type="text/javascript">
        $(document).ready(function() {
        		$("#enterRoom").on("click",function(){
        			$.ajax({
        				url:'${path}/setRoomId.do',
        				type:'post',
        				ContentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        		        dataType: 'json',
        				data:{'roomId':$("#roomId").val()},
        				success:function(data){
        					if(data==1){//if시작
        						$("#sendBtn").click(function() {
        				            sendMessage();
        				            $('#message').val('')
        				    	});
        					    $("#message").keydown(function(key) {
        					            if (key.keyCode == 13) {// 엔터
        					                   sendMessage();
        					                   $('#message').val('')
        					            }
        					    });
        				        // 웹소켓을 지정한 url로 연결한다.
        				        let sock = new SockJS("/ex/echo");
        				        sock.onmessage = onMessage;
        				        sock.onclose = onClose;
        				        // 메시지 전송
        				        function sendMessage() {
        				               sock.send($("#message").val());
        				        }
        				        // 서버로부터 메시지를 받았을 때
        				        function onMessage(msg) {
        				               var data = msg.data;
        				               $("#data").append(data + "<br/>");
        				        }
        				        // 서버와 연결을 끊었을 때
        				        function onClose(evt) {
        				               $("#data").append("연결 끊김");
        				        }
        					}//if끝
        				}
        			});
        		});
        });
</script>

</html>