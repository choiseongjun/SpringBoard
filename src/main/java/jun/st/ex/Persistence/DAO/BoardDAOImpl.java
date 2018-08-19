package jun.st.ex.Persistence.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jun.st.ex.Persistence.DTO.BoardDTO;
@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
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
		sqlSession.update("board.update",dto);
	}

	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("board.delete", bno); 
		
	}



	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		sqlSession.update("board.increaseViewcnt", bno);
	}

	@Override
	public List<BoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", "%"+keyword+"%");
		map.put("start", start); //맵에 자료 저장
		map.put("end", end);
// mapper에는 2개 이상의 값을 전달할 수 없음(dto 또는 map 사용)		
		return sqlSession.selectList("board.listAll",map); 
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		Map<String,String> map=new HashMap<>();
		map.put("search_option", search_option);
		map.put("keyword", "%"+keyword+"%");
		return sqlSession.selectOne("board.countArticle",map);
	}

	@Override
	public void deleteFile(String fullName) {
		sqlSession.delete("board.deleteFile",fullName);
		
	}

	@Override
	public List<String> getAttach(int bno) {

		return sqlSession.selectList("board.getAttach",bno);
	}

	@Override
	public void addAttach(String fullName) {
		sqlSession.insert("board.addAttach",fullName);
	}

	@Override
	public void updateAttach(String fullName, int bno) {
		Map<String, Object> map=new HashMap<>();
		map.put("fullname", fullName);//첨부파일 이름
		map.put("bno", bno);//게시물 번호
		sqlSession.insert("board.updateAttach",map);
	}
	


}
