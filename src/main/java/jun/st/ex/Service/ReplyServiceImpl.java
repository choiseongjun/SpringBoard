package jun.st.ex.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jun.st.ex.Persistence.DAO.ReplyDao;
import jun.st.ex.Persistence.DTO.ReplyDTO;
@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	ReplyDao replyDao;
	
	@Override
	public List<ReplyDTO> list(int bno) {
		return replyDao.list(bno);
	}

	@Override
	public int count(int bno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create(ReplyDTO dto) {
		replyDao.create(dto);
	}

	@Override
	public void delete(Integer rno) {
		replyDao.delete(rno);
	}

}
