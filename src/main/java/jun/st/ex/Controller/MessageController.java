package jun.st.ex.Controller;

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
		return "Message/Chatroom"; 
	}
	@RequestMapping("ChatList.do")
	public String ChatList() {
		//채팅페이지로
		return "Message/MemberList"; 
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
	public List<ChatDTO> getMessageList(HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		String otherUserid = (String)session.getAttribute("otherUserid");
		
		Map<String,String> data = new HashMap<>();
		data.put("userid", userid);
		data.put("otherUserid", otherUserid);
		
		return chatService.getMessageList(data); 
	}
}
