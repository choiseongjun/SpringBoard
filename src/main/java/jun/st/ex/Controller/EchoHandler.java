package jun.st.ex.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);

	private Map<String, List<WebSocketSession>> roomSessionListMap = new HashMap<String, List<WebSocketSession>>();
	//private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	// 클라이언트와 연결 이후에 실행되는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//1.접속한 방이 rooms 맵에 있는지 부터 확인
		String roomId = (String)session.getAttributes().get("roomId");
		List<WebSocketSession> sessionList = roomSessionListMap.get(roomId);
		if(sessionList!=null) {//채팅방이이미존재한다면 내 세션 추가
			sessionList.add(session);
		}else {//채팅방이 없다면 채팅방 생성 후 내 새션 추가
			List<WebSocketSession> newSessionList = new ArrayList<WebSocketSession>();
			newSessionList.add(session);
			roomSessionListMap.put(roomId, newSessionList);
		}
		
		logger.info("{} 연결됨", session.getId());
	}

	// 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
		String roomId = (String)session.getAttributes().get("roomId");//내가 현재 접속한 방 ID 가져오기
		List<WebSocketSession> webSocketSessionList = roomSessionListMap.get(roomId);
		for (WebSocketSession sess : webSocketSessionList) {
			sess.sendMessage(new TextMessage(session.getId() + " : " + message.getPayload()));
		}
	}

	// 클라이언트와 연결을 끊었을 때 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String roomId = (String)session.getAttributes().get("roomId");
		List<WebSocketSession> sessionList = roomSessionListMap.get(roomId);
		sessionList.remove(session);
		logger.info("{} 연결 끊김", session.getId());
		
	}
}
