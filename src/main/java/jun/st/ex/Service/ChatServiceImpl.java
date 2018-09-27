package jun.st.ex.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jun.st.ex.Persistence.DAO.ChatDAO;
import jun.st.ex.Persistence.DTO.ChatDTO;
@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;

	@Override
	public void insertMessage(ChatDTO chatDto) {
		
		chatDAO.insertMessage(chatDto);
	}

	@Override
	public List<ChatDTO> getMessageList(Map<String, String> data) {
		return chatDAO.getMessageList(data);
	}

	@Override
	public void updateChatRead(Map<String, String> data) {
		chatDAO.updateChatRead(data);
	}

	

	

	
	
	
	
}
