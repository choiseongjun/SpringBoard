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

	@Inject //�������� ����(Dependency Injection, DI)
	SqlSession sqlSession;

	@Override
	public void create(BoardDTO dto) throws Exception {
		sqlSession.insert("board.insert", dto); 
		
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return sqlSession.selectOne("board.read", bno); 
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.delete", bno); 
		
	}

	@Override
	public List<BoardDTO> listAll() throws Exception{
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.listAll");
	}

	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}
	


}
