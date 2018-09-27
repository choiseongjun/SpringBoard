//package jun.st.ex.Controller;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//public class WebSocketHandler extends TextWebSocketHandler{
//
//	 @Autowired
//	 SqlSession sqlSession;
//
//		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//
//			NoteDao dao = sqlsession.getMapper(NoteDao.class);
//			this.logger.info(message.getPayload());
//			session.sendMessage(new TextMessage(dao.count_receive_note(message.getPayload()))); 
//		}
//
//
//
//
//}
