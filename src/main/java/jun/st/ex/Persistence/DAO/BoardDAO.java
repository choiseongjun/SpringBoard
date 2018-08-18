package jun.st.ex.Persistence.DAO;

import java.util.List;

import javax.servlet.http.HttpSession;

import jun.st.ex.Persistence.DTO.BoardDTO;


public interface BoardDAO {
	public void create(BoardDTO dto) throws Exception;
	public BoardDTO read(int bno) throws Exception;
	public void update(BoardDTO dto) throws Exception;
	public void delete(int bno) throws Exception;
	public List<BoardDTO> listAll(String search_option, String keyword,int start, int end)
			throws Exception;
	public void increaseViewcnt(int bno, HttpSession session) throws Exception;
	public int countArticle(
			String search_option, String keyword) throws Exception;
}
