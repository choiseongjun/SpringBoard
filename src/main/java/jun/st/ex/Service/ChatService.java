package jun.st.ex.Service;

import java.util.List;
import java.util.Map;

import jun.st.ex.Persistence.DTO.ChatDTO;

public interface ChatService {

	public void insertMessage(ChatDTO chatDto);
	public List<ChatDTO> getMessageList(Map<String, String> data);
	public void updateChatRead(Map<String, String> data);//읽음처리
}
