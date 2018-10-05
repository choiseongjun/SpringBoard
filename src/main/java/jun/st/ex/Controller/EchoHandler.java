package jun.st.ex.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import jun.st.ex.Persistence.DTO.ChatDTO;
import jun.st.ex.Service.ChatService;

@Component
public class EchoHandler extends TextWebSocketHandler {
	
	@Autowired
	private ChatService chatService;
	
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	
	private Map<String, WebSocketSession> sessionMap= new HashMap<>();
	
	public EchoHandler() {
	}
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String userid = (String)session.getAttributes().get("userid");
		String otherUserid = (String)session.getAttributes().get("otherUserid");
		sessionMap.put(userid, session);
		
		Map<String,String> data = new HashMap<>();
		data.put("fromid", otherUserid);
		data.put("toid", userid);
		
		chatService.updateChatRead(data);
		
		WebSocketSession sess = sessionMap.get(otherUserid);
		
		if(sess!=null){
			sess.sendMessage(new TextMessage("7777"));
		}
	}
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		String userid = (String)session.getAttributes().get("userid");
		String profileImage = (String)session.getAttributes().get("profileImage");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(profileImage+"*&^%$#$%&*()(*&^%$%^&*(222222222222222222222222222222222");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		String otherUserid = (String)session.getAttributes().get("otherUserid");
		
			ChatDTO chatDto = new ChatDTO();
			chatDto.setToid(otherUserid);
			chatDto.setFromid(userid);
			chatDto.setChatcontent(message.getPayload());
			
			if(sessionMap.get(otherUserid)!=null) {
				chatDto.setChatread(1);
			}else {
				chatDto.setChatread(0);
			}
			chatService.insertMessage(chatDto);
			
		JSONObject jsonObject = new JSONObject();
		long nano=System.currentTimeMillis();
		String dateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(nano);
		jsonObject.put("dateTime", dateTime);
		jsonObject.put("senderId",userid);
		jsonObject.put("profileImage",profileImage);
		jsonObject.put("message", message.getPayload());
		if(chatDto.getChatread()==0) {
			jsonObject.put("chatread", 1);
		}else {
			jsonObject.put("chatread", "");
		}
		
		String jsonString = jsonObject.toString();
		
		WebSocketSession sess1 = sessionMap.get(userid);
		WebSocketSession sess2 = sessionMap.get(otherUserid);
		
		if(sess1!=null){
			sess1.sendMessage(new TextMessage(jsonString));
		}
		if(sess2!=null){
			sess2.sendMessage(new TextMessage(jsonString));
		}
	}

	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String userid = (String)session.getAttributes().get("userid");
			WebSocketSession sess = sessionMap.get(userid);
			if(sess!=null) {
				sessionMap.remove(userid);
			}
		}
	}

