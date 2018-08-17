package jun.st.ex.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jun.st.ex.Persistence.DAO.BoardDAO;
import jun.st.ex.Persistence.DTO.BoardDTO;


@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDao;
	
	@Override
	public void create(BoardDTO dto) throws Exception {
		String title=dto.getTitle();
		String content=dto.getContent();
		String writer=dto.getWriter();
		
		//replace(A,B)A를 b로 변경
		title=title.replace("<","&It");
		title=title.replace("<","&gt");
		writer=writer.replace("<","&It");
		writer=writer.replace("<","&gt");
		//공백 문자 처리
		title=title.replace(" ", "&nbsp&nbsp;");
		writer=writer.replace(" ", "&nbsp&nbsp;");
		
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		
		boardDao.create(dto);
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	@Override
	public void update(BoardDTO dto) throws Exception {
		boardDao.update(dto);
	}

	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}

	@Override
	public List<BoardDTO> listAll() {
		return boardDao.listAll();
	}

	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
