package jun.st.ex.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jun.st.ex.Persistence.DAO.BoardDAO;
import jun.st.ex.Persistence.DTO.BoardDTO;


@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDao;
	
	
	@Override
	@Transactional
	public void create(BoardDTO dto) throws Exception {
		boardDao.create(dto);
		
		String[] files=dto.getFiles(); //첨부파일 이름 배열
		if(files==null) return;
		for(String name : files) {
			boardDao.addAttach(name);
		}
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	@Transactional
	@Override
	public void update(BoardDTO dto) throws Exception {
		 boardDao.update(dto);//board 테이블 수정
		 //attach 테이블 수정
		 String[] files=dto.getFiles();
		 if(files==null) return;
		 for(String name : files) {
			 boardDao.updateAttach(name, dto.getBno());
		 }
	}



	@Transactional
	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}

	@Override
	public List<BoardDTO> listAll(String search_option, String keyword,int start, int end) throws Exception{
		return boardDao.listAll(search_option, keyword, start, end);
	}

	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
			long update_time=0;
			if(session.getAttribute("update_time_"+bno)!=null) {
				//최근에 조회수를 올린 시간
				update_time=
						(long)session.getAttribute("update_time_"+bno);
			}
			long current_time=System.currentTimeMillis();
			//일정 시간이 경과한 후 조회수 증가 처리
			if(current_time - update_time > 5*1000) {
				//조회수 증가 처리
				boardDao.increaseViewcnt(bno, session);
				//조회수를 올린 시간 저장
				session.setAttribute("update_time_"+bno, current_time);
			}
	}

	@Override
	public int countArticle(String search_option, String keyword) throws Exception {
		return boardDao.countArticle(search_option,keyword);
	}

	@Override
	public void deleteFile(String fullName) {
		boardDao.deleteFile(fullName);
	}

	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno);
	}


	

	
}
