package jun.st.ex.Persistence.DAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jun.st.ex.Persistence.DTO.ChatDTO;


@Repository
public class ChatDAOImpl implements ChatDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void insertMessage(ChatDTO chatDto) {
		sqlSession.insert("chat.insertMessage",chatDto);
	}

	@Override
	public List<ChatDTO> getMessageList(Map<String, String> data) {
		return sqlSession.selectList("chat.GetMessageList",data);
	}

	@Override
	public void updateChatRead(Map<String, String> data) {
			sqlSession.update("chat.UpdateReadChat",data);
	}



}
