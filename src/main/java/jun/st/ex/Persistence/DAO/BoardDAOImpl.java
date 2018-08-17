package jun.st.ex.Persistence.DAO;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jun.st.ex.Persistence.DTO.BoardDTO;
@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject //의존관계 주입(Dependency Injection, DI)
	SqlSession sqlSession;

	@Override
	public void create(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BoardDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}
	


}
