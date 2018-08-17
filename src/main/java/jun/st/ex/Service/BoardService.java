package jun.st.ex.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import jun.st.ex.Persistence.DTO.BoardDTO;

public interface BoardService {
	
	public void create(BoardDTO dto) throws Exception;
	public BoardDTO read(int bno) throws Exception;
	public void update(BoardDTO dto) throws Exception;
	public void delete(int bno) throws Exception;
	public List<BoardDTO> listAll();
	public void increaseViewcnt(int bno, HttpSession session) throws Exception;
}
