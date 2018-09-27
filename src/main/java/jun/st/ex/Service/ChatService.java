package jun.st.ex.Service;

import java.util.List;
import java.util.Map;

import jun.st.ex.Persistence.DTO.ChatDTO;

public interface ChatService {

	public void insertMessage(ChatDTO chatDto);
	public List<ChatDTO> getMessageList(Map<String, String> data);
	public int CheckreadCount(String userid);//채팅 읽기전
	public int updatereadCount(String userid); //채팅 읽은후
}
