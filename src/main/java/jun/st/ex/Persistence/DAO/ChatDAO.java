package jun.st.ex.Persistence.DAO;

import java.util.List;
import java.util.Map;

import jun.st.ex.Persistence.DTO.ChatDTO;

public interface ChatDAO {
	public void insertMessage(ChatDTO chatDto);
	public List<ChatDTO> getMessageList(Map<String, String> data);
}
