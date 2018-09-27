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
	
	private List<Map<String, WebSocketSession>> sessionMapList= new ArrayList<>();
	
	public EchoHandler() {
	}
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String userid = (String)session.getAttributes().get("userid");
		
		Map<String, WebSocketSession> sessionMap = new HashMap<>();
		sessionMap.put(userid, session);
		sessionMapList.add(sessionMap);
	}
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String userid = (String)session.getAttributes().get("userid");
		String otherUserid = (String)session.getAttributes().get("otherUserid");
		
			ChatDTO chatDto = new ChatDTO();
			chatDto.setToid(otherUserid);
			chatDto.setFromid(userid);
			chatDto.setChatcontent(message.getPayload());
			
			chatService.insertMessage(chatDto);
			
		JSONObject jsonObject = new JSONObject();
		long nano=System.currentTimeMillis();
		String dateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(nano);
		jsonObject.put("dateTime", dateTime);
		jsonObject.put("senderId",userid);
		jsonObject.put("message", message.getPayload());
		String jsonString = jsonObject.toString();
		for (Map<String, WebSocketSession> sessionMap : sessionMapList) {
			WebSocketSession sess  = null;
			WebSocketSession sess1 = sessionMap.get(userid);
			WebSocketSession sess2 = sessionMap.get(otherUserid);
			
			if(sess1!=null){sess=sess1;}
			if(sess2!=null){sess=sess2;}
			if(sess!=null) {
				
				sess.sendMessage(new TextMessage(jsonString));
			
			}
		}
	}

	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String userid = (String)session.getAttributes().get("userid");
		for (Map<String, WebSocketSession> sessionMap : sessionMapList) {
			WebSocketSession sess = sessionMap.get(userid);
			if(sess!=null) {
				sessionMapList.remove(sessionMap);
			}
		}
	}
}
