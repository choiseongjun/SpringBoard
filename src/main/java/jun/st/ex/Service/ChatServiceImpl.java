package jun.st.ex.Service;

import javax.inject.Inject;

import jun.st.ex.Persistence.DAO.ChatDAO;

public class ChatServiceImpl implements ChatService {

	@Inject
	ChatDAO chatDao;
	
	@Override
	public int submit(String fromID, String toID, String chatContent) {
		return chatDao.submit(fromID, toID, chatContent);
	}

}
