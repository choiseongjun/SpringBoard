package jun.st.ex.Persistence.DAO;

import java.util.List;

import javax.servlet.http.HttpSession;

import jun.st.ex.Persistence.DTO.BoardDTO;


public interface BoardDAO {
	public void create(BoardDTO dto) throws Exception;//생성
	public BoardDTO read(int bno) throws Exception;//상세보기
	public void update(BoardDTO dto) throws Exception;//수정
	public void delete(int bno) throws Exception;//삭제
	public List<BoardDTO> listAll();//게시판 리스트 
	public void increaseViewcnt(int bno, HttpSession session) throws Exception;//조회 
}
