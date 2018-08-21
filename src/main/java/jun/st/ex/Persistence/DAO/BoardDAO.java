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
	public void deleteFile(String fullname);//첨부파일 삭제
	public List<String> getAttach(int bno);//첨부파일 정보
	public void addAttach(String fullname);//첨부파일 저장
	public void updateAttach(String fullname,int bno);//첨부파일 수정
	
	public BoardDTO replyForm(int bno);
	public void replyUpdate(BoardDTO dto);
	public void replyInsert(BoardDTO dto); 
}
