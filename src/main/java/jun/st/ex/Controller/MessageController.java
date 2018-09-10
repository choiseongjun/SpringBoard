package jun.st.ex.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

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
}
