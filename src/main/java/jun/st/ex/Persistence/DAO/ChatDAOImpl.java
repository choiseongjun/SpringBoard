package jun.st.ex.Persistence.DAO;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

public class ChatDAOImpl implements ChatDAO {

	@Inject
	SqlSession sqlSession;
	@Override
	public int submit(String fromID, String toID, String chatContent) {
		Map<String,String> map=new HashMap<>();
		map.put("fromID", fromID);
		map.put("toID", toID);
		map.put("chatContent", chatContent);
		
		return sqlSession.insert("chat.submit",map);
	}

}
