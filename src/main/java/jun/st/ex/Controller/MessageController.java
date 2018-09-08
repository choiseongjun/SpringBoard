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
		return "Message/echo-ws"; 
	}
	
	@ResponseBody
	@RequestMapping("setRoomId.do")
	public int setRoomId(String roomId, HttpSession session) {
		session.setAttribute("roomId", roomId);
		return 1;
	}
}
