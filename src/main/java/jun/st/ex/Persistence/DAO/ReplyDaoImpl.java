package jun.st.ex.Persistence.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jun.st.ex.Persistence.DTO.ReplyDTO;
@Repository
public class ReplyDaoImpl implements ReplyDao{
	
	@Inject
	SqlSession sqlSession;
	

	@Override
	public List<ReplyDTO> list(int bno) {
		return sqlSession.selectList("reply.listReply",bno);
	}

	@Override
	public void create(ReplyDTO dto) {
		sqlSession.insert("reply.insertReply",dto);
	}

	@Override
	public int count(int bno) {
		return 0;
	}

	@Override
	public void delete(Integer rno) {
		sqlSession.delete("reply.deleteReply",rno);
	}

	@Override
	public ReplyDTO detail(Integer rno) {
		return sqlSession.selectOne("reply.detailReply",rno);
	}

}
