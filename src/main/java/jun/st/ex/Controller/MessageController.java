package jun.st.ex.Controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jun.st.ex.Persistence.DTO.ChatDTO;
import jun.st.ex.Service.ChatService;

@Controller
public class MessageController {
	
	@Autowired
	ChatService chatService;

	@RequestMapping("message.do")
	public String write() {
		//채팅페이지로
		return "Message/Chatroom1"; 
	}
	@RequestMapping("ChatList.do")
	public String ChatList(HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		//채팅페이지로
		return "Message/MemberList"; 
	}
	@RequestMapping("message1.do")
	public String write1() {
		//채팅페이지로
		return "Message/Chatroom1"; 
	}
	@ResponseBody
	@RequestMapping("setRoomId.do")
	public int setRoomId(String roomId, HttpSession session) {
		session.setAttribute("roomId", roomId);
		return 1;
	}
	
	@ResponseBody
	@RequestMapping("setOtherUserid.do")
	public int setOtherUserid(String otherUserid, HttpSession session) {
		session.setAttribute("otherUserid", otherUserid);
		return 1;
	}
	@ResponseBody
	@RequestMapping("getMessageList.do")
	public Map<String,Object> getMessageList(HttpSession session) {
		
		Map<String,String> data = new HashMap<>();
		String userid = (String)session.getAttribute("userid");
		String otherUserid = (String)session.getAttribute("otherUserid");
		data.put("userid", userid);
		data.put("otherUserid", otherUserid);
		List<ChatDTO> messageList = chatService.getMessageList(data);
		
		for (ChatDTO chatDTO : messageList) {
			String stringTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(chatDTO.getChattime().getTime());
			chatDTO.setStringTime(stringTime);
		}
		
		Map<String,Object> returnData = new HashMap<>();
		returnData.put("messageList", messageList);
		returnData.put("userid", userid);
		
		return returnData; 
		
	}
	
	@ResponseBody
	public int readChat(String fromid,String toid,HttpSession session) {
		String userid=(String)session.getAttribute("userid");
	
		chatService.updatereadCount(userid);//채팅 읽은후
		return 0;
		
	}
}
