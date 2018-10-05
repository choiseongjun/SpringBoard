<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script>
<title>Insert title here</title>
</head>
<style>
body {
    font-family: Roboto,sans-serif;
    font-size: 13px;
    line-height: 1.42857143;
    color: #767676;
    background-color: #edecec;
}

#messages-main {
    position: relative;
    margin: 0 auto;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.12);
}
#messages-main:after, #messages-main:before {
    content: " ";
    display: table;
}
#messages-main .ms-menu {
    position: absolute;
    left: 0;
    top: 0;
    border-right: 1px solid #eee;
    padding-bottom: 50px;
    height: 100%;
    width: 240px;
    background: #fff;
}
@media (min-width:768px) {
    #messages-main .ms-body {
    padding-left: 240px;
}
}@media (max-width:767px) {
    #messages-main .ms-menu {
    height: calc(100% - 58px);
    display: none;
    z-index: 1;
    top: 58px;
}
#messages-main .ms-menu.toggled {
    display: block;
}
#messages-main .ms-body {
    overflow: hidden;
}
}
#messages-main .ms-user {
    padding: 15px;
    background: #f8f8f8;
}
#messages-main .ms-user>div {
    overflow: hidden;
    padding: 3px 5px 0 15px;
    font-size: 11px;
}
#messages-main #ms-compose {
    position: fixed;
    bottom: 120px;
    z-index: 1;
    right: 30px;
    box-shadow: 0 0 4px rgba(0, 0, 0, .14), 0 4px 8px rgba(0, 0, 0, .28);
}
#ms-menu-trigger {
    user-select: none;
    position: absolute;
    left: 0;
    top: 0;
    width: 50px;
    height: 100%;
    padding-right: 10px;
    padding-top: 19px;
}
#ms-menu-trigger i {
    font-size: 21px;
}
#ms-menu-trigger.toggled i:before {
    content: '\f2ea'
}
.fc-toolbar:before, .login-content:after {
    content: ""
}
.message-feed {
    padding: 20px;
}
#footer, .fc-toolbar .ui-button, .fileinput .thumbnail, .four-zero, .four-zero footer>a, .ie-warning, .login-content, .login-navigation, .pt-inner, .pt-inner .pti-footer>a {
    text-align: center;
}
.message-feed.right>.pull-right {
    margin-left: 15px;
}
.message-feed:not(.right) .mf-content {
    background: #03a9f4;
    color: #fff;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.12);
}
.message-feed.right .mf-content {
    background: #eee;
}
.mf-content {
    padding: 12px 17px 13px;
    border-radius: 2px;
    display: inline-block;
    max-width: 80%
}
.mf-date {
    display: block;
    color: #B3B3B3;
    margin-top: 7px;
}
.mf-date>i {
    font-size: 14px;
    line-height: 100%;
    position: relative;
    top: 1px;
}
.msb-reply {
    box-shadow: 0 -20px 20px -5px #fff;
    position: relative;
    margin-top: 30px;
    border-top: 1px solid #eee;
    background: #f8f8f8;
}
.four-zero, .lc-block {
    box-shadow: 0 1px 11px rgba(0, 0, 0, .27);
}
.msb-reply textarea {
    width: 100%;
    font-size: 13px;
    border: 0;
    padding: 10px 15px;
    resize: none;
    height: 60px;
    background: 0 0;
}
.msb-reply button {
    position: absolute;
    top: 0;
    right: 0;
    border: 0;
    height: 100%;
    width: 60px;
    font-size: 25px;
    color: #2196f3;
    background: 0 0;
}
.msb-reply button:hover {
    background: #f2f2f2;
}
.img-avatar {
    height: 37px;
    border-radius: 2px;
    width: 37px;
}
.list-group.lg-alt .list-group-item {
    border: 0;
}
.p-15 {
    padding: 15px!important;
}
.btn:not(.btn-alt) {
    border: 0;
}
.action-header {
    position: relative;
    background: #f8f8f8;
    padding: 15px 13px 15px 17px;
}
.ah-actions {
    z-index: 3;
    float: right;
    margin-top: 7px;
    position: relative;
}
.actions {
    list-style: none;
    padding: 0;
    margin: 0;
}
.actions>li {
    display: inline-block;
}

.actions:not(.a-alt)>li>a>i {
    color: #939393;
}
.actions>li>a>i {
    font-size: 20px;
}
.actions>li>a {
    display: block;
    padding: 0 10px;
}
.ms-body{
    background:#fff;    
}
#ms-menu-trigger {
    user-select: none;
    position: absolute;
    left: 0;
    top: 0;
    width: 50px;
    height: 100%;
    padding-right: 10px;
    padding-top: 19px;
    cursor:pointer;
}
#ms-menu-trigger, .message-feed.right {
    text-align: right;
}
#ms-menu-trigger, .toggle-switch {
    -webkit-user-select: none;
    -moz-user-select: none;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var myid="";
		var imgPath="${path}/resources/assets/images/ProFilePicture/";	
		function connectSocket(){
			// 웹소켓을 지정한 url로 연결한다.
			let sock = new SockJS("/ex/echo");
			sock.onmessage = onMessage;
			sock.onclose = onClose;
			// 메시지 전송
			function sendMessage() {
			       sock.send($("#chatEnter").val());
			       $("#chatEnter").val('');
			}
			// 서버로부터 메시지를 받았을 때
			function onMessage(msg) {
			       var data = msg.data;
			       var imgPath="${path}/resources/assets/images/ProFilePicture/";
			       if(data=="7777"){
			    	   $('.readCount').text("");
			       }else{
			    	   
			       
			       
				       var jsonObject = JSON.parse(data);
				       
				       var str="";
				       if(myid==jsonObject.senderId){
		        			//오른쪽메시지박스
		        			str+="<div class='message-feed right'>";
				        	str+="	<div class='pull-right'>";
				        	str+="		<img src='"+imgPath+jsonObject.profileImage+"' alt='' class='img-avatar'>";
				        	str+="	</div>";
				        	str+="	<div>";
				        	str+=		jsonObject.senderId;
				        	str+="	</div>";
				        	str+="	<div class='media-body'>";
				        	str+="		<span class='readCount'>";
				        	str+=			jsonObject.chatread;
				        	str+="		</span>";
				        	str+="		<div class='mf-content'>";
				        	str+=			jsonObject.message;
				        	str+="		</div>";
				        	str+="		<small class='mf-date'><i class='fa fa-clock-o'></i> "+jsonObject.dateTime+"</small>";
				        	str+="	</div>";
				        	str+="</div>";
		        		}else{
		        			 //왼쪽메시지박스
				        	str+="<div class='message-feed media'>";
				        	str+="	<div class='pull-left'>";
				        	str+="		<img src='"+imgPath+jsonObject.profileImage+"' alt='' class='img-avatar'>";
				        	str+="	</div>";
				        	str+="	<div>";
				        	str+=		jsonObject.senderId;
				        	str+="	</div>";
				        	str+="	<div class='media-body'>";
				        	str+="		<div class='mf-content'>";
				        	str+=			jsonObject.message;
				        	str+="		</div>";
				        	str+="		<small class='mf-date'><i class='fa fa-clock-o'></i> "+jsonObject.dateTime+"</small>";
				        	str+="	</div>";
				        	str+="</div>";
		        		}
				       
				        $("#messageListBox").append(str);
			        	$("#messageListBox").scrollTop($("#messageListBox")[0].scrollHeight);
				        $(document).scrollTop($(document).height());
			       }
			}
			// 서버와 연결을 끊었을 때
			function onClose(evt) {
			       $("#chatList").append("연결 끊김");
			}
			
			$("#submitBtn").on('click',function(){
				sendMessage();
			});
			
			$("#chatEnter").keydown(function(key) {
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
		        	myid=data.userid;
		        	for(var i = 0;i<data.messageList.length;i++){
		        		
		        		if(myid==data.messageList[i].fromid){
		        			//오른쪽메시지박스
		        			str+="<div class='message-feed right'>";
				        	str+="	<div class='pull-right'>";
				        	str+="		<img src='"+imgPath+data.messageList[i].profileimage+"' alt='' class='img-avatar'>";
				        	str+="	</div>";
				        	str+="	<div>";
				        	str+=		data.messageList[i].fromid;
				        	str+="	</div>";
				        	str+="	<div class='media-body'>";
				        	str+="		<span class='readCount'>";
								        	if(data.messageList[i].chatread==0){
								        		str+="1";
								        	}
				        	str+="		</span>";
				        	str+="		<div class='mf-content'>";
				        	str+=			data.messageList[i].chatcontent;
				        	str+="		</div>";
				        	str+="		<small class='mf-date'><i class='fa fa-clock-o'></i> "+data.messageList[i].stringTime+"</small>";
				        	str+="	</div>";
				        	str+="</div>";
		        		}else{
		        			 //왼쪽메시지박스
				        	str+="<div class='message-feed media'>";
				        	str+="	<div class='pull-left'>";
				        	str+="		<img src='"+imgPath+data.messageList[i].profileimage+"' alt='' class='img-avatar'>";
				        	str+="	</div>";
				        	str+="	<div>";
				        	str+=		data.messageList[i].fromid;
				        	str+="	</div>";
				        	str+="	<div class='media-body'>";
				        	str+="		<div class='mf-content'>";
				        	str+=			data.messageList[i].chatcontent;
				        	str+="		</div>";
				        	str+="		<small class='mf-date'><i class='fa fa-clock-o'></i> "+data.messageList[i].stringTime+"</small>";
				        	str+="	</div>";
				        	str+="</div>";
		        		}
		        	}
		        	
		        	$("#messageListBox").append(str);
		        	$("#messageListBox").scrollTop($("#messageListBox")[0].scrollHeight);
		        	//$(document).scrollTop($(document).height());
		        	
		        	connectSocket();//마지막에성공하면 실행
		        },
		        error: function (XMLHttpRequest, textStatus, errorThrown){
		        }
			});
			
     });
</script>
<body>
<%@ include file="../Header.jsp" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container bootstrap snippet">
   
        <div class="ms-body">
            <div class="action-header clearfix">
                <div class="visible-xs" id="ms-menu-trigger">
                    <i class="fa fa-bars"></i>
                </div>
                
                <div class="pull-left hidden-xs">
                    <img src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="" class="img-avatar m-r-10">
                    <div class="lv-avatar pull-left">
                        
                    </div>
                    <span>David Parbell</span>
                </div>
                 
                <ul class="ah-actions actions">
                    <li>
                        <a href="">
                            <i class="fa fa-trash"></i>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-check"></i>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-clock-o"></i>
                        </a>
                    </li>
                    <li class="dropdown">
                        <a href="" data-toggle="dropdown" aria-expanded="true">
                            <i class="fa fa-sort"></i>
                        </a>
            
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li>
                                <a href="">Latest</a>
                            </li>
                            <li>
                                <a href="">Oldest</a>
                            </li>
                        </ul>
                    </li>                             
                    <li class="dropdown">
                        <a href="" data-toggle="dropdown" aria-expanded="true">
                            <i class="fa fa-bars"></i>
                        </a>
            
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li>
                                <a href="">Refresh</a>
                            </li>
                            <li>
                                <a href="">Message Settings</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
    		
    	
    		<!-- <!-- <div id="messageListBox"></div> --> 
<div id="messageListBox" class="portlet-body chat-widget" style="overflow-y:auto;width:auto;height:600px"></div>

            <div class="chatContent">
                <textarea id="chatEnter" placeholder="What's on your mind..."></textarea>
                <button><i class="fa fa-paper-plane-o" id = "submitBtn"></i></button>
            </div>
        </div>
    </div>
</body>
</html>    